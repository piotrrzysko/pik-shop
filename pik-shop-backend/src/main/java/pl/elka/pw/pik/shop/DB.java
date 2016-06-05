package pl.elka.pw.pik.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.elka.pw.pik.shop.domain.model.Product;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.domain.repository.ProductRepository;
import pl.elka.pw.pik.shop.domain.repository.UserRepository;
import pl.elka.pw.pik.shop.security.domain.model.Identity;

import java.math.BigDecimal;
import java.util.stream.LongStream;

@Component
public class DB implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        insertTestUsers();
        insertTestProducts();
    }

    private void insertTestProducts() {
        LongStream
                .range(1, 10)
                .forEach(value -> productRepository.save(createTestProduct(value)));
    }

    private void insertTestUsers() {
        LongStream
                .range(1, 10)
                .forEach(value -> userRepository.save(createTestUser(value)));
    }

    private Product createTestProduct(Long id) {
        Product product = new Product();
        product.setName("Product " + id);
        product.setDescription("Product description" + id);
        product.setAvailableCount(id);
        product.setPrice(BigDecimal.valueOf(id));
        product.setProductState(Product.ProductState.PUBLISHED);
        return product;
    }

    private User createTestUser(Long no) {
        User user = new User();
        user.email = "test" + no + "@pik.pl";
        user.firstName = "First " + no;
        user.lastName = "Last " + no;
        user.phoneNumber = "62683412" + no;
        user.address = "Warszawa " + no;
        user.identities.add(new Identity(user, "password"));
        return user;
    }
}