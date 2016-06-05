package pl.elka.pw.pik.shop.security.domain.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.elka.pw.pik.shop.domain.model.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Identity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String type;
    public String username;
    public String password;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    public User user;

    public Identity() {
    }

    public Identity(User user, String password) {
        this.user = user;
        this.username = user.email;
        setEncodedPassword(password);
    }

    public void setEncodedPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
