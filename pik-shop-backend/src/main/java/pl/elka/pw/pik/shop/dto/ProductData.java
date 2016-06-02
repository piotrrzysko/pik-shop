package pl.elka.pw.pik.shop.dto;

import pl.elka.pw.pik.shop.domain.model.Product;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductData {
    private Long id;
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
    private String mainImage;

    public ProductData() {}

    public ProductData(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.availableCount = product.getAvailableCount();
        this.description = product.getDescription();
        this.mainImage = product.getMainImageFileName();
    }

    public ProductData(String name) {
        this.name = name;
    }

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

    public Long getId() {
        return id;
    }

    public String getMainImage() {
        return mainImage;
    }
}
