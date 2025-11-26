package org.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        // Return the view name; resolved to resources/templates/hello.html
        return "hello.html";
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String RedirectExample() {
        return "hello";
    }
}
