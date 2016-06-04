package pl.elka.pw.pik.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.domain.repository.UserRepository;
import pl.elka.pw.pik.shop.security.domain.model.Identity;

import java.util.stream.LongStream;

@Component
public class DB implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        insertTestData();
    }

    private void insertTestData() {
        LongStream.range(1, 10).forEach(value ->
                userRepository.save(createTestUser(value))
        );
    }

    private User createTestUser(Long no) {
        User user = new User();
        user.email = "test" + no + "@pik.pl";
        user.firstName = "First" + no;
        user.lastName = "Last" + no;
        user.identities.add(new Identity(user, "password"));
        return user;
    }
}