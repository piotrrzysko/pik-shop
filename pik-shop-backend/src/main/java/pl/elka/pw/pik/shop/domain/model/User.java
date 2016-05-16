package pl.elka.pw.pik.shop.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_pik") //tylko w tym miejscu poniewaz user jest slowem kluczowym w postgres SIC!!!
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String email;
    public String name;
    public UserType userType;

    public enum UserType {
        USER, ADMIN
    }
}
