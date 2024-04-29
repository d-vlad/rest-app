package org.rest.app.controller;

import java.util.List;

import org.rest.app.exceptions.CantDeleteCustomer;
import org.rest.app.exceptions.CustomerNotExist;
import org.rest.app.model.Customer;
import org.rest.app.repos.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private CustomersRepository repo;

    @GetMapping("/test")
    public String test() {
        return "This is a test. Your app is working";
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return repo.save(customer);
    }

    @PutMapping("/update")
    public Customer updateCustomerOrInsert(@RequestBody Customer customer) {
        return repo.findById(customer.getId())
            .map(c -> {
                c.setName(customer.getName());
                return repo.save(c);
            })
            .orElseGet(() -> repo.save(customer));
    }

    @PostMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) throws CustomerNotExist {
        return repo.findById(customer.getId())
            .map(c -> {
                c.setName(customer.getName());
                return repo.save(c);
            })
            .orElseThrow(CustomerNotExist::new);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatusCode deleteCustomer(@PathVariable Long id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new CantDeleteCustomer(e);
        }
        return HttpStatus.OK;
    }

    @GetMapping("/all")
    public List<Customer> findAll() {
        return repo.findAll();
    }
}
