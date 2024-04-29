package org.rest.app.controller;

import java.util.List;

import org.rest.app.exceptions.CustomerNotExist;
import org.rest.app.model.Customer;
import org.rest.app.service.CustomerRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomerRelationService relationService;

    @GetMapping("/test")
    public String test() {
        return "This is a test. Your app is working";
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return relationService.addCustomer(customer);
    }

    @PutMapping("/update")
    public Customer updateCustomerOrInsert(@RequestBody Customer customer) throws CustomerNotExist {
        return relationService.updateCustomerInfo(customer);
    }

    @PostMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) throws CustomerNotExist {
        return relationService.updateInfoOnly(customer);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatusCode deleteCustomer(@PathVariable Long id) {
        return relationService.deleteCustomerInfos(id);
    }

    @GetMapping("/all")
    public List<Customer> findAll() {
        return relationService.retrieveAllInfos();
    }
}
