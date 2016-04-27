package pl.elka.pw.pik.shop.dto;

import pl.elka.pw.pik.shop.domain.model.Product;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductData {
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Long availableCount;
    @NotNull
    private Product.ProductState productState;
    @NotNull
    private String description;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getAvailableCount() {
        return availableCount;
    }

    public Product.ProductState getProductState() {
        return productState;
    }

    public String getDescription() {
        return description;
    }
}