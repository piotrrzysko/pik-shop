package pl.elka.pw.pik.shop.domain.model;

import org.springframework.beans.factory.annotation.Value;
import pl.elka.pw.pik.shop.dto.ProductData;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

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
    @Column(length = 1023)
    private String description;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "product_image",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "file_id")})
    private Set<File> images;

    public Product() {
    }

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

    public Set<File> getImages() {
        return images;
    }

    public String getMainImageFileName() {
        if (images != null && !images.isEmpty()) {
            Optional<File> image = images.stream().findFirst();
            if (image.isPresent())
                return image.get().getFileName();
        }
        return "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setProductState(ProductState productState) {
        this.productState = productState;
    }

    public void setAvailableCount(Long availableCount) {
        this.availableCount = availableCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImages(Set<File> images) {
        this.images = images;
    }

    public enum ProductState {
        PUBLISHED,
        UNPUBLISHED,
        DELETED,
        DISABLED
    }
}
