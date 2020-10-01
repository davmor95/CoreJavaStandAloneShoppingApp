package repo;

import model.CustomerOrder;

import java.util.List;

public interface CustomerOrderRepoImpl {
    public boolean addOrder(CustomerOrder order);
    public List<CustomerOrder> getAllCustomerOrderByEmail(String email);

}
