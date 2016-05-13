package pl.elka.pw.pik.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.elka.pw.pik.shop.domain.model.Product;
import pl.elka.pw.pik.shop.domain.repository.ProductRepository;
import pl.elka.pw.pik.shop.domain.specification.ProductSpecification;
import pl.elka.pw.pik.shop.dto.ProductData;
import pl.elka.pw.pik.shop.dto.ProductSearchParams;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductSpecification productSpecification;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductSpecification productSpecification) {
        this.productRepository = productRepository;
        this.productSpecification = productSpecification;
    }

    public Page<Product> findProductsPage(ProductSearchParams searchParams) {
        return productRepository.findAll(productSpecification.buildFrom(searchParams), searchParams.toPageRequest());
    }

    public void addProduct(ProductData productData) {
        Product newProduct = new Product(productData);
        newProduct.setId(null);
        productRepository.save(newProduct);
    }

    public void deleteProduct(Long productId) {
        productRepository.delete(productId);
    }
}
