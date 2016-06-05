package pl.elka.pw.pik.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.domain.model.orders.Order;
import pl.elka.pw.pik.shop.domain.repository.OrderRepository;
import pl.elka.pw.pik.shop.domain.repository.UserRepository;
import pl.elka.pw.pik.shop.domain.specification.OrderSpecification;
import pl.elka.pw.pik.shop.domain.specification.UserSpecification;
import pl.elka.pw.pik.shop.dto.OrderSearchParams;
import pl.elka.pw.pik.shop.dto.UsersSearchParams;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;
    UserSpecification userSpecification;
    OrderRepository orderRepository;
    OrderSpecification orderSpecification;

    @Autowired
    public UserService(UserRepository userRepository, UserSpecification userSpecification,
                       OrderRepository orderRepository, OrderSpecification orderSpecification) {
        this.userSpecification = userSpecification;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderSpecification = orderSpecification;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Page<User> findUsersPage(UsersSearchParams searchParams) {
        return userRepository.findAll(userSpecification.buildFrom(searchParams), searchParams.toPageRequest());
    }

    public Page<Order> findOrdersPage(OrderSearchParams searchParams, Long userID) {
        Specification<Order> orderSpecification = this.orderSpecification.buildFrom(searchParams, userID);
        PageRequest pageable = searchParams.toPageRequest();
        return orderRepository.findAllByUserId(userID, orderSpecification, pageable);
    }

    public User getUser(Long userId) {
        return userRepository.findOne(userId);
    }
}
