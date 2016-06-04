package pl.elka.pw.pik.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.domain.repository.UserRepository;
import pl.elka.pw.pik.shop.domain.specification.UserSpecification;
import pl.elka.pw.pik.shop.dto.UsersSearchParams;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;
    UserSpecification userSpecification;

    @Autowired
    public UserService(UserRepository userRepository, UserSpecification userSpecification) {
        this.userSpecification = userSpecification;
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Page<User> findUsersPage(UsersSearchParams searchParams) {
        return userRepository.findAll(userSpecification.buildFrom(searchParams), searchParams.toPageRequest());
    }

    public User getUser(Long userId) {
        return userRepository.findOne(userId);
    }
}
