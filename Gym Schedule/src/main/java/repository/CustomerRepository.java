package repository;

import models.Customer;

import java.util.HashMap;
import java.util.List;

public class CustomerRepository {
    
    private static CustomerRepository customerRepository = null;

    public static CustomerRepository getInstance(){
        if(customerRepository == null)
            customerRepository = new CustomerRepository();

        return customerRepository;
    }

    private HashMap<Integer, Customer> customerMap;

    public CustomerRepository() {
        this.customerMap = new HashMap<>();
    }

    public Customer addCustomer(Customer customer){
        customerMap.put(customer.getId(), customer);
        return customer;
    }

    public Customer getCustomer(Integer id){
        return customerMap.getOrDefault(id, null);
    }

    public List<Customer> getAllCustomers(){
        return customerMap.values().stream().toList();
    }
}
