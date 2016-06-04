package pl.elka.pw.pik.shop.domain.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.elka.pw.pik.shop.domain.model.Product;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.dto.ProductSearchParams;
import pl.elka.pw.pik.shop.dto.UsersSearchParams;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 04.06.2016.
 */

@Component
public class UserSpecification {
    public Specification<User> buildFrom(UsersSearchParams searchParams) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchParams.getName() != null)
                predicates.add(cb.like(root.<String>get("name"), "%" + searchParams.getName() + "%"));
            if (searchParams.getEmail() != null)
                predicates.add(cb.like(root.<String>get("email"), "%" + searchParams.getEmail() + "%"));
            if (searchParams.getId() != null)
                predicates.add(cb.equal(root.<Long>get("id"), searchParams.getId()));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
