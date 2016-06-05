package pl.elka.pw.pik.shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/hello"})
public class Secured {

    @RequestMapping(method = RequestMethod.GET)
    public String helloSecured() {
        return "Hello Secured";
    }
}
