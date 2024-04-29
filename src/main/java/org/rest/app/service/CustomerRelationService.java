package org.rest.app.service;

import java.util.List;
import java.util.Optional;

import org.rest.app.exceptions.CantDeleteCustomer;
import org.rest.app.exceptions.CustomerNotExist;
import org.rest.app.model.Customer;
import org.rest.app.repos.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class CustomerRelationService {

    private final CustomersRepository repository;

    @Autowired
    public CustomerRelationService(CustomersRepository repository) {
        this.repository = repository;
    }

    public Customer addCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer updateCustomerInfo(Customer customer) throws CustomerNotExist {
        return upsertCustomer(customer, true);
    }

    public Customer updateInfoOnly(Customer customer) throws CustomerNotExist {
        return upsertCustomer(customer, false);
    }

    private Customer upsertCustomer(Customer customer, boolean upsert) throws CustomerNotExist {
        Optional<Customer> optCustomer = repository.findById(customer.getId())
            .map(c -> {
                c.setName(customer.getName());
                return repository.save(c);
            });

        if (upsert) {
            return optCustomer.orElseGet(() -> repository.save(customer));
        } else {
            return optCustomer.orElseThrow(CustomerNotExist::new);
        }
    }

    public HttpStatusCode deleteCustomerInfos(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new CantDeleteCustomer(e);
        }
        return HttpStatus.OK;
    }

    public List<Customer> retrieveAllInfos() {
        return repository.findAll();
    }
}
