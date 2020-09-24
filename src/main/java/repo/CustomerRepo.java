package repo;

import connection.ConnectionManager;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepo implements CustomerRepoImpl{
    private Connection conn = ConnectionManager.getConnection();
    public boolean addCustomer(Customer customer) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("insert into customer (name, email, password) values(?, ?, ?)");

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPassword());

            int insert = preparedStatement.executeUpdate();

            if(insert > 0) {
                return true;
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
