package pl.elka.pw.pik.shop.security.domain.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import pl.elka.pw.pik.shop.security.dto.UserDTO;

import java.util.Collection;

public class UserAuthentication implements Authentication {

    private final UserDTO user;
    private boolean authenticated = true;

    public UserAuthentication(UserDTO user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public UserDTO getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
