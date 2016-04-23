package pl.elka.pw.pik.shop.dto;

import pl.elka.pw.pik.shop.domain.model.Product;

import java.math.BigDecimal;

public class ProductSearchParams extends PagableSearchParams {
    private String name;
    private BigDecimal price;
    private Long availableCount;
    private Product.ProductState productState;

    public ProductSearchParams() {
        super(10, 0, null, null);
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
}
