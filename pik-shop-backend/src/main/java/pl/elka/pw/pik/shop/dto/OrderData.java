package pl.elka.pw.pik.shop.dto;

import pl.elka.pw.pik.shop.domain.model.orders.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderData {
    private Long id;
    private Date creationTime;
    private Set<OrderItemData> orderItems;
    private BigDecimal totalValueWithoutDelivery;
    private AddressData deliveryAddress;
    private PaymentTypeData paymentType;
    private DeliveryFormTypeData deliveryFormType;

    public OrderData() {
    }

    public OrderData(Order order) {
        this.id = order.getId();
        this.creationTime = order.getCreationTime();
        this.deliveryAddress = new AddressData(order);
        this.deliveryFormType = new DeliveryFormTypeData(order.getDeliveryFormType());
        this.paymentType = new PaymentTypeData(order.getPaymentType());
        this.orderItems = order.getOrderItems()
                .stream()
                .map(OrderItemData::new)
                .collect(Collectors.toSet());
        this.totalValueWithoutDelivery = orderItems
                .stream()
                .map(OrderItemData::getTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Set<OrderItemData> getOrderItems() {
        return orderItems;
    }

    public BigDecimal getTotalValueWithoutDelivery() {
        return totalValueWithoutDelivery;
    }

    public AddressData getDeliveryAddress() {
        return deliveryAddress;
    }

    public PaymentTypeData getPaymentType() {
        return paymentType;
    }

    public DeliveryFormTypeData getDeliveryFormType() {
        return deliveryFormType;
    }
}
