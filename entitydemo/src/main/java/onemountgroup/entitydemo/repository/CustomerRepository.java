package onemountgroup.entitydemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import onemountgroup.entitydemo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByemail(String email);

    Optional<Customer> findByjob(String job);
}
