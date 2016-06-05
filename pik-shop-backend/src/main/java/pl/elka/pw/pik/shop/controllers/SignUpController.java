package pl.elka.pw.pik.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.dto.SignUpParams;
import pl.elka.pw.pik.shop.services.LoggedUserContextService;
import pl.elka.pw.pik.shop.domain.model.orders.Order;
import pl.elka.pw.pik.shop.dto.OrderSearchParams;
import pl.elka.pw.pik.shop.dto.UsersSearchParams;
import pl.elka.pw.pik.shop.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "public/signup")
public class SignUpController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User signUp(@RequestBody SignUpParams signUpParams){
        return userService.signUp(signUpParams);
    }
}
