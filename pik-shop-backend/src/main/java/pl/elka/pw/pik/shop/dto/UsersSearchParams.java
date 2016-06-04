package pl.elka.pw.pik.shop.dto;

import pl.elka.pw.pik.shop.domain.model.User;

/**
 * Created by Igor on 04.06.2016.
 */
public class UsersSearchParams extends PagableSearchParams{
    private String name;
    private Long id;
    private String email;

    public UsersSearchParams() {
        super(10, 0, null, null);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
