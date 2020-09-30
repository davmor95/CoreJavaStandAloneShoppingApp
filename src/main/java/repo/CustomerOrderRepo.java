package repo;

import connection.ConnectionManager;
import model.CustomerOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
