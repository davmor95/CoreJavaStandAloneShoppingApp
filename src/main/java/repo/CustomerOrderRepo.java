package repo;

import connection.ConnectionManager;
import model.CustomerOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderRepo implements CustomerOrderRepoImpl{
    private Connection conn = ConnectionManager.getConnection();
    @Override
    public boolean addOrder(CustomerOrder order) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("insert into CustomerOrder (itemCode, itemName, price, email) values(?, ?, ?, ?)");
            preparedStatement.setString(1, order.getItemCode());
            preparedStatement.setString(2, order.getItemName());
            preparedStatement.setString(3, Double.toString(order.getPrice()));
            preparedStatement.setString(4, order.getEmail());

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

    @Override
    public List<CustomerOrder> getAllCustomerOrderByEmail(String email) {
        List<CustomerOrder> customerOrderList = new ArrayList<CustomerOrder>();

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from CustomerOrder where email = ?")) {
            while(resultSet.next()) {
                Integer orderId = Integer.parseInt(resultSet.getString(1));
                String itemCode = resultSet.getString(2);
                String itemName = resultSet.getString(3);
                Double price = Double.parseDouble(resultSet.getString(4));
                String userEmail = resultSet.getString(5);

                CustomerOrder customerOrder = new CustomerOrder(orderId, itemCode, itemName, price, userEmail);
                customerOrderList.add(customerOrder);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerOrderList;
    }
}
