package pl.elka.pw.pik.shop.security.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler {

    private final TokenAuthenticationService tokenAuthenticationService;

    public LogoutHandler(TokenAuthenticationService tokenAuthenticationService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if ("OPTIONS".equals(request.getMethod())) {
            try {
                response.getWriter().print("OK");
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        tokenAuthenticationService.removeAuthentication(request, response);
    }
}
