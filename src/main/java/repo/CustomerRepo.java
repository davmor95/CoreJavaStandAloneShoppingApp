package repo;

import connection.ConnectionManager;
import model.Customer;

import java.sql.Connection;

public class CustomerRepo implements CustomerRepoImpl{
    private Connection conn = ConnectionManager.getConnection();
    public boolean addCustomer(Customer customer) {
        return false;
    }
}
