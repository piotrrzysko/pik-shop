package pl.elka.pw.pik.shop.dto;

import pl.elka.pw.pik.shop.domain.model.User;

/**
 * Created by Igor on 04.06.2016.
 */
public class UsersSearchParams extends PagableSearchParams{
    private String first_name;
    private String last_name;
    private Long id;
    private String email;

    public UsersSearchParams() {
        super(10, 0, null, null);
    }

    public void setFirst_name(String first_name) {
        this.first_name= first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name= last_name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
