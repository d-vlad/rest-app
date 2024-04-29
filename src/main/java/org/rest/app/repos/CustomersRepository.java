package org.rest.app.repos;

import org.rest.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customer, Long> {

}
