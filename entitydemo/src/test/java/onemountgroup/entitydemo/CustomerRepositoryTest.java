package onemountgroup.entitydemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import onemountgroup.entitydemo.model.Customer;
import onemountgroup.entitydemo.repository.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@Sql({ "/customer.sql" })

public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepo;

    @Test
    @DisplayName(" Get customer count")
    public void getCustomerCount() {
        assertThat(customerRepo.count()).isGreaterThan(9);
    }

    @Test
    public void insertNewCustomer() {
        long countBeforeInsert = customerRepo.count();
        Customer cus = new Customer();
        cus.setFirstname("Nguyen");
        cus.setLastname("Nhat");
        cus.setEmail("nhat.nguyen@gmail.com");
        cus.setMobile("+84 362 181 770");
        cus.setJob("fresher");
        customerRepo.save(cus);
        long countAfterInsert = customerRepo.count();
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
    }

    @Test
    @DisplayName(" find id customer ")
    public void findIdCustomer() {
        Optional<Customer> customer = customerRepo.findById(5L);
        if (customer.isPresent()) {
            assertThat(customer.get()).extracting("firstName").isEqualTo("Asher");
        }
    }

    @Test
    @DisplayName(" find email customer ")
    public void findemailCustomer() {
        Optional<Customer> customer = customerRepo.findByemail("kscruby8@sourceforge.net");
        if (customer.isPresent()) {
            assertThat(customer.get()).extracting("firstName").isEqualTo("Katinka");
        }
    }

    @Test
    @DisplayName(" find job customer ")
    public void findjobCustomer() {
        Optional<Customer> customer = customerRepo.findByjob("fresher");
        if (customer.isPresent()) {
            assertThat(customer.get()).extracting("firstName").isEqualTo("Nguyen");
        }
    }

    @Test
    @DisplayName(" delete id customer ")
    public void deleteCustomer() {
        Optional<Customer> customer = customerRepo.findById(9L);
        if (customer.isPresent()) {
            customerRepo.delete(customer.get());
        }
    }

}
