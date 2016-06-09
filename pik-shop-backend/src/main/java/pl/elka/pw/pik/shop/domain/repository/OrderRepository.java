package pl.elka.pw.pik.shop.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.elka.pw.pik.shop.domain.model.orders.Order;
import pl.elka.pw.pik.shop.domain.model.orders.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    Page<Order> findAll(Specification<Order> spec, Pageable pageable);

    List<Order> findAllByUserId(Long userID);

    Order findByUserIdAndStatus(Long userId, OrderStatus status);

//    Page<Order> findAllByUserId(Long userID, Specification<Order> orderSpecification, PageRequest pageable);
}
