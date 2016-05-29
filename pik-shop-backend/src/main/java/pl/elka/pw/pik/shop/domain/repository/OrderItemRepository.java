package pl.elka.pw.pik.shop.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.elka.pw.pik.shop.domain.model.orders.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

}
