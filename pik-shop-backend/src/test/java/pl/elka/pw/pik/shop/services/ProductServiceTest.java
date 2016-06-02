package pl.elka.pw.pik.shop.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import pl.elka.pw.pik.shop.domain.model.Product;
import pl.elka.pw.pik.shop.domain.repository.ProductRepository;
import pl.elka.pw.pik.shop.domain.specification.ProductSpecification;
import pl.elka.pw.pik.shop.dto.ProductData;
import pl.elka.pw.pik.shop.dto.ProductSearchParams;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductSpecification productSpecification;
    @InjectMocks
    private ProductService productService;
    @Mock
    private FileService fileService;

    @Test
    public void should_return_empty_page() throws Exception {
        ProductSearchParams params = new ProductSearchParams();
        PageImpl<Product> emptyPage = new PageImpl<Product>(Collections.emptyList());
        Specification<Product> spec = productSpecification.buildFrom(params);
        when(productRepository.findAll(spec, params.toPageRequest())).thenReturn(emptyPage);

        Page<Product> page = productService.findProductsPage(params);

        assertEquals(0, page.getTotalElements());
        assertEquals(1, page.getTotalPages());
    }

    @Test
    public void stores_product() throws Exception {
        ProductData productData = new ProductData("Test Product");

        productService.addProduct(productData, Collections.emptyList());

        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(argument.capture());
        assertEquals("Test Product", argument.getValue().getName());
    }
}
