package pl.elka.pw.pik.shop.security.domain.model;

import org.joda.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class UserActivity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long userId;
    public LocalDateTime lastLogin;
    public LocalDateTime lastActivity;

    public UserActivity() {
    }

    public UserActivity(Long userId) {
        this.userId = userId;
    }
}
