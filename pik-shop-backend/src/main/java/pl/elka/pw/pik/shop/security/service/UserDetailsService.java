package pl.elka.pw.pik.shop.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.elka.pw.pik.shop.security.UserDTOGetter;
import pl.elka.pw.pik.shop.security.dto.UserDTO;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Autowired
    UserDTOGetter userDTOGetter;

    @Override
    public UserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserDTO userDTO = userDTOGetter.obtainUserByEmail(username);

        if (userDTO == null) {
            throw new UsernameNotFoundException("user not found");
        }
        detailsChecker.check(userDTO);
        return userDTO;
    }


}
