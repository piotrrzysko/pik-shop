package pl.elka.pw.pik.shop.security.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.elka.pw.pik.shop.security.domain.model.UserToken;

public interface UserTokenRepository extends CrudRepository<UserToken, Long> {

    UserToken findByToken(String token);
    void deleteByUserId(Long userId);
}
