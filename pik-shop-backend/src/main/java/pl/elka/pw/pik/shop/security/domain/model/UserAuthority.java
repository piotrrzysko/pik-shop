package pl.elka.pw.pik.shop.security.domain.model;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "USER";
    }
}