package pl.elka.pw.pik.shop.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class UserDTO implements Serializable, UserDetails {

    private String username;
    private String password;
    private String email;
    private long expires;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled = true;
    private boolean credentialsExpired;
    private boolean accountLocked;
    private boolean accountExpired;
    private Long userId;

    public UserDTO() {
    }

    public UserDTO(String username, String email, long expires, Long userId) {
        this.username = username;
        this.email = email;
        this.expires = expires;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @JsonIgnore
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @JsonIgnore
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    @JsonIgnore
    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    @JsonIgnore
    public boolean isAccountLocked() {
        return accountLocked;
    }

    @JsonIgnore
    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    @JsonIgnore
    public boolean isAccountExpired() {
        return accountExpired;
    }

    @JsonIgnore
    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    @Override
    public String toString() {
        return "UserDTO{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", email='" + email + '\''
                + ", expires=" + expires
                + ", authorities=" + authorities
                + ", enabled=" + enabled
                + ", credentialsExpired=" + credentialsExpired
                + ", accountLocked=" + accountLocked
                + ", accountExpired=" + accountExpired
                + '}';
    }
}
