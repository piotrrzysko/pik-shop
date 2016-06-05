package pl.elka.pw.pik.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.domain.repository.UserRepository;
import pl.elka.pw.pik.shop.security.domain.model.Identity;
import pl.elka.pw.pik.shop.security.domain.model.UserAuthority;
import pl.elka.pw.pik.shop.security.domain.repository.IdentityRepository;
import pl.elka.pw.pik.shop.security.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDTOGetter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IdentityRepository identityRepository;

    public UserDTO obtainUserByEmail(String email) {
        Identity identity = identityRepository.findByUsername(email);
        UserDTO userDTO = null;
        if(identity  != null){
            userDTO = parse(identity);
        }
        return userDTO;
    }

    private UserDTO parse(Identity identity) {
        User user = identity.user;
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.id);
        userDTO.setUsername(user.email);
        userDTO.setPassword(identity.password);
        userDTO.setEmail(user.email);
        setAuthorities(userDTO);
        return userDTO;
    }

    private void setAuthorities(UserDTO userDTO) {
        List<UserAuthority> authorities = new ArrayList<>();
        authorities.add(new UserAuthority());
        userDTO.setAuthorities(authorities);
    }
}
