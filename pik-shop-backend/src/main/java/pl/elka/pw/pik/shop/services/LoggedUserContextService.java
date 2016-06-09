package pl.elka.pw.pik.shop.services;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.elka.pw.pik.shop.security.dto.UserDTO;

@Service
public class LoggedUserContextService {
    public Long getUserIdFromSecurityContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDTO details = (UserDTO) auth.getDetails();
            return details.getUserId();
        }
        return null;
    }
}
