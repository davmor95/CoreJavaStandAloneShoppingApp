package repo;

import connection.ConnectionManager;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Customer getCustomerByEmail(String email) {
        Customer customer = null;
        try(PreparedStatement preparedStatement = conn.prepareStatement("select * from customer where email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String cust_name = resultSet.getString(2);
                String cust_email = resultSet.getString(3);
                String cust_password = resultSet.getString(4);

                customer = new Customer(cust_name, cust_email, cust_password);
                System.out.println(customer);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }
}
