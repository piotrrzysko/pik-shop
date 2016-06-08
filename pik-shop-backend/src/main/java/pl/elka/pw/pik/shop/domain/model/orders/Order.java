package pl.elka.pw.pik.shop.domain.model.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.elka.pw.pik.shop.domain.model.Product;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.dto.AddressData;
import pl.elka.pw.pik.shop.dto.OrderData;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "order_entity")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private Date creationTime;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    private DeliveryFormType deliveryFormType;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String deliveryHouseNumber;

    private String deliveryPostalCode;

    private String deliveryStreet;

    private String deliveryCity;

    private String deliveryPhoneNumber;

    private String deliveryFirstName;

    private String deliveryLastName;

    public Order() {
        this.orderItems = new HashSet<>();
    }

    public Order(Date creationTime) {
        this();
        this.creationTime = creationTime;
        this.status = OrderStatus.CART;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public DeliveryFormType getDeliveryFormType() {
        return deliveryFormType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getDeliveryHouseNumber() {
        return deliveryHouseNumber;
    }

    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public String getDeliveryPhoneNumber() {
        return deliveryPhoneNumber;
    }

    public String getDeliveryFirstName() {
        return deliveryFirstName;
    }

    public String getDeliveryLastName() {
        return deliveryLastName;
    }

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public boolean isUpdatable() {
        return status == OrderStatus.CART;
    }

    public boolean isNotConfirmed() {
        return isUpdatable();
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void updateDeliveryData(OrderData orderData) {
        deliveryFormType = orderData.getDeliveryFormType().getId();
        AddressData deliveryAddress = orderData.getDeliveryAddress();
        deliveryCity = deliveryAddress.getCity();
        deliveryHouseNumber = deliveryAddress.getHouseNumber();
        deliveryFirstName = deliveryAddress.getFirstName();
        deliveryLastName = deliveryAddress.getLastName();
        deliveryStreet = deliveryAddress.getStreet();
        deliveryPhoneNumber = deliveryAddress.getPhoneNumber();
        deliveryPostalCode = deliveryAddress.getPostalCode();
    }

    public void updatePaymentData(OrderData orderData) {
        paymentType = orderData.getPaymentType().getId();
    }

    public void addItem(Product product, Integer amount) {
        Optional<OrderItem> item = orderItems
                .stream()
                .filter(o -> o.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (item.isPresent()) {
            item.get().addToAmount(amount);
        } else {
            OrderItem newItem = new OrderItem(this, product, amount);
            orderItems.add(newItem);
        }
    }

    public void removeItem(Long itemId) {
        if (!orderItems.removeIf(o -> o.getId().equals(itemId))) {
            throw new RuntimeException("Wrong orderItemId: " + itemId);
        }
    }
}
