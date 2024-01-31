package com.sunbase.customerbackend.service;

import com.sunbase.customerbackend.model.Customer;
import com.sunbase.customerbackend.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestTemplate restTemplate;

    public void synchronizeCustomerList(String bearerToken) {
        String apiUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Customer[]> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                requestEntity,
                Customer[].class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            // Update or insert customers in the database
            List<Customer> remoteCustomers = Arrays.asList(responseEntity.getBody());
            for (Customer remoteCustomer : remoteCustomers) {
                Optional<Customer> existingCustomer = customerRepository.findById(Long.valueOf(remoteCustomer.getUuid()));
                if (existingCustomer.isPresent()) {
                    // Update existing customer
                    Customer updatedCustomer = existingCustomer.get();
                    updatedCustomer.updateFrom(remoteCustomer);
                    customerRepository.save(updatedCustomer);
                } else {
                    // Insert new customer
                    customerRepository.save(remoteCustomer);
                }
            }
        } else {
            // Handle synchronization failure
            throw new RuntimeException("Customer list synchronization failed");
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElse(null);
    }






    public ResponseEntity<Customer> createCustomer(Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    public ResponseEntity<Customer> updateCustomer(Long id, Customer customer) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.updateFrom(customer);
            customerRepository.save(existingCustomer);
            return new ResponseEntity<>(existingCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Page<Customer>> getCustomersWithPagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Customer> customersPage = customerRepository.findAll(pageRequest);
        return new ResponseEntity<>(customersPage, HttpStatus.OK);
    }

    public ResponseEntity<Customer> getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
