package com.sunbase.customerbackend.repo;

import com.sunbase.customerbackend.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);


    Page<Customer> findAll(Pageable pageable);

    List<Customer> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
