package pl.elka.pw.pik.shop.security.domain.model;

import pl.elka.pw.pik.shop.security.dto.UserDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class UserToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String username;
    public String email;
    public Long expires;
    public Long userId;
    public String token;

    public UserToken() {
    }

    public UserToken(UserDTO userDTO, String token) {
        this.username = userDTO.getUsername();
        this.email = userDTO.getEmail();
        this.expires = userDTO.getExpires();
        this.userId = userDTO.getUserId();
        this.token = token;
    }
}
