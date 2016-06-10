package pl.elka.pw.pik.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.elka.pw.pik.shop.domain.model.Product;
import pl.elka.pw.pik.shop.dto.ProductData;
import pl.elka.pw.pik.shop.dto.ProductSearchParams;
import pl.elka.pw.pik.shop.services.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/public/products"})
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Product> getProducts(ProductSearchParams searchParams) {
        return productService.findProductsPage(searchParams);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void addProduct(@Valid @RequestPart("productData") ProductData productData,
                           @RequestParam("images") List<MultipartFile> images) {
        productService.addProduct(productData, images);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductData getProduct(@PathVariable Long productId) {
        return productService.findProduct(productId);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductData updateProduct(@Valid @RequestPart("productData") ProductData productData, @PathVariable Long productId) {
        return productService.updateProduct(productId, productData);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
