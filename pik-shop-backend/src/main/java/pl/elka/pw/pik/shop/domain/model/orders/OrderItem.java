package pl.elka.pw.pik.shop.domain.model.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.elka.pw.pik.shop.domain.model.Product;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_entity_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private BigDecimal price;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, Integer amount) {
        this.order = order;
        this.product = product;
        this.amount = amount;
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getAmount() {
        return amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void addToAmount(Integer amount) {
        this.amount += amount;
    }
}
