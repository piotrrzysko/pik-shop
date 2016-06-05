package pl.elka.pw.pik.shop.security.dto;

import java.io.Serializable;

/**
 * Created by damian on 03.06.16.
 */
public class UserLoginDTO implements Serializable {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
