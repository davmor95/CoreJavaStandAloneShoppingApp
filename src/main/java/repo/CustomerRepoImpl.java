package repo;

import model.Customer;

public interface CustomerRepoImpl {
    public boolean addCustomer(Customer customer);
    public Customer getCustomerByEmail(String email);
}
