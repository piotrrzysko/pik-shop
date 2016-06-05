package pl.elka.pw.pik.shop.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LoginResponseDTO implements Serializable {
    private String accessToken;

    @JsonProperty("token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
