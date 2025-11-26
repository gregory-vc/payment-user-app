package org.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class MainRestController {

    @GetMapping(value = "/hello")
    public String helloExample() {
        return "rest-hello";
    }

    @GetMapping(value = "/hello2")
    public String helloRp() {
        // Return the view name; resolved to resources/templates/hello.html
        return "hello.html";
    }
}
