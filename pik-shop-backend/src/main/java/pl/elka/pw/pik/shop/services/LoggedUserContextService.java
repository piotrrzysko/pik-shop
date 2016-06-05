package pl.elka.pw.pik.shop.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.elka.pw.pik.shop.security.dto.UserDTO;

@Service
public class LoggedUserContextService {
    public Long getUserIdFromSecurityContext() {
        UserDTO details = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return details.getUserId();
    }
}
