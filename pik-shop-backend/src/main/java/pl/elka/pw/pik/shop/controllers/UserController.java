package pl.elka.pw.pik.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.services.LoggedUserContextService;
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

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public User getLoggedUser() {
        return userService.getUser(loggedUserContextService.getUserIdFromSecurityContext());
    }
}
