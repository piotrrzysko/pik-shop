package pl.elka.pw.pik.shop.dto;

import pl.elka.pw.pik.shop.domain.model.Product;
import pl.elka.pw.pik.shop.domain.model.orders.OrderItem;

import java.math.BigDecimal;

public class OrderItemData {
    private Long id;
    private Long productId;
    private String productName;
    private Integer amount;
    private String productImage;
    private BigDecimal price;

    public OrderItemData() {}

    public OrderItemData(OrderItem item) {
        this.id = item.getId();
        this.amount = item.getAmount();
        Product product = item.getProduct();
        this.productId = product.getId();
        this.productName = product.getName();
        this.productImage = product.getMainImageFileName();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotalValue() {
        return price.multiply(new BigDecimal(amount));
    }
}
