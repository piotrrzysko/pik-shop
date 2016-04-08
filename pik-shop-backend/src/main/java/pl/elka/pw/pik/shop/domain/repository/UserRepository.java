package pl.elka.pw.pik.shop.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.elka.pw.pik.shop.domain.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByEmail(String email);
}
