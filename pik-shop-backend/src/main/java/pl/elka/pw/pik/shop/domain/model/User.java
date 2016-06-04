package pl.elka.pw.pik.shop.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.elka.pw.pik.shop.security.domain.model.Identity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_pik") //tylko w tym miejscu poniewaz user jest slowem kluczowym w postgres SIC!!!
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String email;
    public String firstName;
    public String lastName;
    public UserType userType;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Identity> identities = new ArrayList<>();

    public enum UserType {
        USER, ADMIN
    }
}
