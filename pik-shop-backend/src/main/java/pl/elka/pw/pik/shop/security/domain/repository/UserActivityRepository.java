package pl.elka.pw.pik.shop.security.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.elka.pw.pik.shop.security.domain.model.UserActivity;

/**
 * Created by damian on 03.06.16.
 */
public interface UserActivityRepository extends CrudRepository<UserActivity, Long> {

    UserActivity findByUserId(Long userId);

    void deleteByUserId(Long userId);
}