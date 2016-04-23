package pl.elka.pw.pik.shop.domain.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.elka.pw.pik.shop.domain.model.Product;
import pl.elka.pw.pik.shop.dto.ProductSearchParams;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSpecification {
    public Specification<Product> buildFrom(ProductSearchParams searchParams) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchParams.getName() != null)
                predicates.add(cb.like(root.<String>get("name"), "%" + searchParams.getName() + "%"));
            if (searchParams.getAvailableCount() != null)
                predicates.add(cb.equal(root.<Long>get("availableCount"), searchParams.getAvailableCount()));
            if (searchParams.getPrice() != null)
                predicates.add(cb.equal(root.<BigDecimal>get("price"), searchParams.getPrice()));
            if (searchParams.getProductState() != null)
                predicates.add(cb.equal(root.get("productState"), searchParams.getProductState()));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
