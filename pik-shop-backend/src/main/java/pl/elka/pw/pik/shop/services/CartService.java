package pl.elka.pw.pik.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.elka.pw.pik.shop.domain.model.orders.Order;
import pl.elka.pw.pik.shop.dto.DeliveryFormTypeData;
import pl.elka.pw.pik.shop.dto.OrderData;
import pl.elka.pw.pik.shop.dto.OrderItemData;
import pl.elka.pw.pik.shop.dto.PaymentTypeData;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private OrderService orderService;
    private LoggedUserContextService loggedUserContextService;


    @Autowired
    public CartService(OrderService orderService, LoggedUserContextService loggedUserContextService) {
        this.orderService = orderService;
        this.loggedUserContextService = loggedUserContextService;
    }

    public OrderData findOrder(Long orderId) {
        Optional<Order> order = findCurrentOrder(orderId);
        if (order.isPresent() && order.get().isNotConfirmed())
            return new OrderData(order.get());
        return new OrderData();
    }

    public OrderData deleteItem(Long orderItemId, Long orderId) {
        Optional<Order> order = findCurrentOrder(orderId);
        if (order.isPresent()) {
            orderService.deleteItem(orderItemId, order.get());
        }
        return new OrderData(order.get());
    }

    public OrderData updateOrder(Long orderId, OrderData orderData) {
        if (orderData.getId().equals(orderId))
            return orderService.updateOrder(orderData);
        else
            throw new RuntimeException("Unable to modify order with id: " + orderData.getId());
    }

    public OrderData linkWithUser(Long orderId) {
        Long userId = getLoggedUser();
        Optional<Order> orderOpt = findCurrentOrder(orderId);
        if (orderOpt.isPresent() && orderOpt.get().isNotConfirmed()) {
            return orderService.linkWithUser(orderOpt.get(), userId);
        }
        throw new RuntimeException("Unable to find current order.");
    }

    public void setStatusConfirmed(Long orderId) {
        orderService.setStatusConfirmed(orderId);
    }

    public void addItem(OrderItemData orderItemData, Long orderId, HttpServletResponse response) {
        Long userId = loggedUserContextService.getUserIdFromSecurityContext();
        Optional<Order> orderOpt = findCurrentOrder(orderId);
        Order order = orderService.createNewOrder(userId);
        if (orderOpt.isPresent() && orderOpt.get().isNotConfirmed())
            order = orderOpt.get();
        order = orderService.addItem(order, orderItemData);
        saveOrderIdInCookies(order, response);
    }

    public List<DeliveryFormTypeData> getDeliveryFormTypes() {
        return orderService.getDeliveryFormTypes();
    }

    public List<PaymentTypeData> getPaymentTypes() {
        return orderService.getPaymentTypes();
    }

    private Optional<Order> findCurrentOrder(Long orderId) {
        Long userId = loggedUserContextService.getUserIdFromSecurityContext();
        if (userId != null) {
            Optional<Order> order = orderService.findUserCurrentOrder(userId);
            if (order.isPresent())
                return order;
        }
        return orderService.findOrder(orderId);
    }

    private void saveOrderIdInCookies(Order order, HttpServletResponse response) {
        response.addCookie(new Cookie("orderId", String.valueOf(order.getId())));
    }

    private Long getLoggedUser() {
        Long userId = loggedUserContextService.getUserIdFromSecurityContext();
        if (userId != null)
            return userId;
        throw new RuntimeException("Unable to find current user.");
    }
}
