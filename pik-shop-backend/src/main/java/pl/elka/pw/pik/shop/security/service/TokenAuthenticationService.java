package pl.elka.pw.pik.shop.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.elka.pw.pik.shop.security.TokenHandler;
import pl.elka.pw.pik.shop.security.UserAuthentication;
import pl.elka.pw.pik.shop.security.domain.model.UserActivity;
import pl.elka.pw.pik.shop.security.domain.model.UserToken;
import pl.elka.pw.pik.shop.security.domain.repository.UserActivityRepository;
import pl.elka.pw.pik.shop.security.domain.repository.UserTokenRepository;
import pl.elka.pw.pik.shop.security.dto.LoginResponseDTO;
import pl.elka.pw.pik.shop.security.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;


@Service
@Transactional
public class TokenAuthenticationService {
    private static final String AUTH_HEADER_NAME = "X-Auth-Token";
    private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

    private final TokenHandler tokenHandler;

    @Autowired
    public TokenAuthenticationService(@Value("${token.secret:1111}") String secret) {
        tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
    }

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private UserActivityRepository userActivityRepository;


    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) throws IOException {
        final UserDTO user = authentication.getDetails();
        user.setExpires(System.currentTimeMillis() + TEN_DAYS);
        String tokenForUserDTO = tokenHandler.generateToken();
        userTokenRepository.save(new UserToken(user, tokenForUserDTO));
        createOrUpdateUserActivityLogin(user.getUserId());
        response.addHeader(AUTH_HEADER_NAME, tokenForUserDTO);
        response.setContentType("application/json");
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setAccessToken(tokenForUserDTO);
        new ObjectMapper().writeValue(response.getOutputStream(), loginResponseDTO);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            UserToken userToken = userTokenRepository.findByToken(token);
            if (userToken != null) {
                final UserDTO user = tokenHandler.createFromUserToken(userToken);
                if (user != null) {
                    createOrUpdateUserActivityInteraction(user.getUserId());
                    return new UserAuthentication(user);
                }
            }
        }
        return null;
    }

    private void createOrUpdateUserActivityLogin(Long userId) {
        UserActivity userActivity = getUserActivity(userId);
        userActivity.lastLogin = new LocalDateTime();
        userActivity.lastActivity = new LocalDateTime();
        userActivityRepository.save(userActivity);
    }

    private void createOrUpdateUserActivityInteraction(Long userId) {
        UserActivity userActivity = getUserActivity(userId);
        userActivity.lastActivity = new LocalDateTime();
        userActivityRepository.save(userActivity);
    }

    private UserActivity getUserActivity(Long userId) {
        UserActivity userActivity = userActivityRepository.findByUserId(userId);
        if (userActivity == null) {
            userActivity = new UserActivity(userId);
        }
        return userActivity;
    }

    public void removeAuthentication(HttpServletRequest request, HttpServletResponse response) {
        UserDTO userDTO = (UserDTO) getAuthentication(request).getDetails();
        if (userDTO != null) {
            userTokenRepository.deleteByUserId(userDTO.getUserId());
        }
        response.setHeader(AUTH_HEADER_NAME, "");
    }
}
