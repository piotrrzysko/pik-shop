package pl.elka.pw.pik.shop.security;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import pl.elka.pw.pik.shop.security.domain.model.UserToken;
import pl.elka.pw.pik.shop.security.dto.UserDTO;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class TokenHandler {

    private static final String HMAC_ALGO = "HmacSHA256";

    private final Mac hmac;

    public TokenHandler(byte[] secretKey) {
        try {
            hmac = Mac.getInstance(HMAC_ALGO);
            hmac.init(new SecretKeySpec(secretKey, HMAC_ALGO));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalStateException("failed to initialize HMAC: " + e.getMessage(), e);
        }
    }

    public UserDTO createFromUserToken(UserToken userToken) {

        UserDTO userDto = new UserDTO(userToken.username, userToken.email, userToken.expires, userToken.userId);
        if (new Date().getTime() < userDto.getExpires()) {
            return userDto;
        }
        return null;
    }

    public String generateToken() {
        return RandomStringUtils.randomAlphanumeric(42);
    }
}
