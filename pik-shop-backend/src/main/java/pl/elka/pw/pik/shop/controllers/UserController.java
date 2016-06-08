package pl.elka.pw.pik.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.dto.SignUpParams;
import pl.elka.pw.pik.shop.services.LoggedUserContextService;
import pl.elka.pw.pik.shop.domain.model.orders.Order;
import pl.elka.pw.pik.shop.dto.OrderSearchParams;
import pl.elka.pw.pik.shop.dto.UsersSearchParams;
import pl.elka.pw.pik.shop.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LoggedUserContextService loggedUserContextService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<User> getUsers(UsersSearchParams searchParams) {
        return userService.findUsersPage(searchParams);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(SignUpParams signUpParams){
        return userService.createUser(signUpParams);
    }

    @RequestMapping(value = "/{userID}/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getUserOrders(@PathVariable("userID") Long userId, OrderSearchParams searchParams){
        return userService.findUserOrders(userId);
    }

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public User getLoggedUser() {
        return userService.getUser(loggedUserContextService.getUserIdFromSecurityContext());
    }
}
