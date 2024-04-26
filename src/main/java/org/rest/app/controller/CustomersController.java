package org.rest.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @GetMapping("/test")
    public String test() {
        return "This is a test. Your app is working";
    }

}
