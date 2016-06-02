package pl.elka.pw.pik.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.elka.pw.pik.shop.domain.model.File;
import pl.elka.pw.pik.shop.domain.model.Product;
import pl.elka.pw.pik.shop.domain.repository.ProductRepository;
import pl.elka.pw.pik.shop.domain.specification.ProductSpecification;
import pl.elka.pw.pik.shop.dto.ProductData;
import pl.elka.pw.pik.shop.dto.ProductSearchParams;

import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductSpecification productSpecification;
    private FileService fileService;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductSpecification productSpecification, FileService fileService) {
        this.productRepository = productRepository;
        this.productSpecification = productSpecification;
        this.fileService = fileService;
    }

    public Page<Product> findProductsPage(ProductSearchParams searchParams) {
        return productRepository.findAll(productSpecification.buildFrom(searchParams), searchParams.toPageRequest());
    }

    public ProductData findProduct(Long productId) {
        Product product = productRepository.findOne(productId);
        if (product != null) {
            return new ProductData(product);
        }
        throw new RuntimeException("Unable to find product with id: " + productId);
    }

    public void addProduct(ProductData productData, List<MultipartFile> images) {
        Product newProduct = new Product(productData);
        Set<File> imagesUploaded = fileService.upload(images);
        newProduct.setImages(imagesUploaded);
        productRepository.save(newProduct);
    }

    public void deleteProduct(Long productId) {
        productRepository.delete(productId);
    }
}
