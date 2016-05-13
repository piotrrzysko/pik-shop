package pl.elka.pw.pik.shop.domain.model;

import pl.elka.pw.pik.shop.dto.ProductData;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductState productState;
    private Long availableCount;
    private String description;
    @OneToOne
    private User user;

    public Product() {}

    public Product(ProductData productData) {
        name = productData.getName();
        price = productData.getPrice();
        productState = productData.getProductState();
        availableCount = productData.getAvailableCount();
        description = productData.getDescription();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductState getProductState() {
        return productState;
    }

    public Long getAvailableCount() {
        return availableCount;
    }

    public String getDescription() {
        return description;
    }

    public enum ProductState {
        PUBLISHED,
        UNPUBLISHED,
        DELETED,
        DISABLED
    }
}
