package pl.elka.pw.pik.shop.domain.model;

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
