package pl.elka.pw.pik.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.elka.pw.pik.shop.domain.model.Product;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.domain.model.orders.DeliveryFormType;
import pl.elka.pw.pik.shop.domain.model.orders.Order;
import pl.elka.pw.pik.shop.domain.model.orders.OrderStatus;
import pl.elka.pw.pik.shop.domain.model.orders.PaymentType;
import pl.elka.pw.pik.shop.domain.repository.OrderItemRepository;
import pl.elka.pw.pik.shop.domain.repository.OrderRepository;
import pl.elka.pw.pik.shop.domain.repository.ProductRepository;
import pl.elka.pw.pik.shop.dto.DeliveryFormTypeData;
import pl.elka.pw.pik.shop.dto.OrderData;
import pl.elka.pw.pik.shop.dto.OrderItemData;
import pl.elka.pw.pik.shop.dto.PaymentTypeData;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderItemRepository orderItemRepository;
    private UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
                        OrderItemRepository orderItemRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.userService = userService;
    }

    public OrderData updateOrder(OrderData orderData) {
        Order order = findOrderWithCheck(orderData.getId());
        if (order.isUpdatable()) {
            order.updateDeliveryData(orderData);
            order.updatePaymentData(orderData);
            return new OrderData(orderRepository.save(order));
        } else {
            throw new RuntimeException("Unable to modify order with id: " + orderData.getId());
        }
    }

    public Order createNewOrder(Long userId) {
        if (userId != null) {
            User user = userService.getUser(userId);
            return new Order(new Date(), user);
        }
        return new Order(new Date());
    }

    public void setStatusConfirmed(Long orderId) {
        Order order = findOrderWithCheck(orderId);
        if (order.isNotConfirmed()) {
            order.setStatus(OrderStatus.CONFIRMED);
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Unable to confirm order with id: " + orderId);
        }
    }

    public OrderData linkWithUser(Order order, Long userId) {
        User user = userService.getUser(userId);
        order.setUser(user);
        return new OrderData(orderRepository.save(order));
    }

    public void deleteItem(Long orderItemId, Order order) {
        order.removeItem(orderItemId);
        orderItemRepository.delete(orderItemId);
    }

    public Order addItem(Order order, OrderItemData orderItemData) {
        Product product = productRepository.findOne(orderItemData.getProductId());
        if (product == null)
            throw new RuntimeException("Unable to find product with id: " + orderItemData.getProductId());

        order.addItem(product, orderItemData.getAmount());
        return orderRepository.save(order);
    }

    public Optional<Order> findOrder(Long orderId) {
        Order order = null;
        if (orderId != null) {
            order = orderRepository.findOne(orderId);
        }
        return Optional.ofNullable(order);
    }

    public Optional<Order> findUserCurrentOrder(Long userId) {
        Order order = orderRepository.findByUserIdAndStatus(userId, OrderStatus.CART);
        return Optional.ofNullable(order);
    }

    public List<DeliveryFormTypeData> getDeliveryFormTypes() {
        return Stream.of(DeliveryFormType.values()).map(DeliveryFormTypeData::new).collect(Collectors.toList());
    }

    public List<PaymentTypeData> getPaymentTypes() {
        return Stream.of(PaymentType.values()).map(PaymentTypeData::new).collect(Collectors.toList());
    }

    private Order findOrderWithCheck(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        if (order != null) {
            return order;
        } else {
            throw new RuntimeException("Unable to find order with id: " + orderId);
        }
    }
}
