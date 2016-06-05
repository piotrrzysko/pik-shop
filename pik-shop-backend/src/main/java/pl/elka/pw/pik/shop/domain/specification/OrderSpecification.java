package pl.elka.pw.pik.shop.domain.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.elka.pw.pik.shop.domain.model.orders.Order;
import pl.elka.pw.pik.shop.dto.OrderSearchParams;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 04.06.2016.
 */

@Component
public class OrderSpecification {
    public Specification<Order> buildFrom(OrderSearchParams searchParams, Long user_id) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchParams.getCreation_time() != null)
                predicates.add(cb.like(root.get("creationTime"), "%" + searchParams.getCreation_time() + "%"));
            if (searchParams.getDelivery_street() != null)
                predicates.add(cb.like(root.<String>get("deliveryStreet"), "%" + searchParams.getDelivery_street() + "%"));
            if (searchParams.getId() != null)
                predicates.add(cb.equal(root.<Long>get("id"), searchParams.getId()));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
