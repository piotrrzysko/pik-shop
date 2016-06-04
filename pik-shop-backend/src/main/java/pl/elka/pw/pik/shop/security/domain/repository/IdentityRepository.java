package pl.elka.pw.pik.shop.security.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.security.domain.model.Identity;

import java.util.List;

public interface IdentityRepository extends CrudRepository<Identity, Long> {
    List<Identity> findByUser(User user);
    Identity findByUsername(String username);
}
