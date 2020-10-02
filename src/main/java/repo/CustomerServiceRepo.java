package repo;

import connection.ConnectionManager;
import model.CustomerOrder;
import model.Invoice;
import model.ItemInvoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceRepo implements CustomerServiceRepoImpl {
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
    public boolean deleteAllOrdersByEmail(String email) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("delete from CustomerOrder where email = ?");
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

    @Override
    public boolean addInvoice(Invoice invoice) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("insert into invoice (invoiceNumber, email, invoiceDate) values (?, ?, ?)");
            preparedStatement.setString(1, invoice.getInvoiceNumber());
            preparedStatement.setString(2, invoice.getEmail());
            preparedStatement.setString(3, invoice.getInvoiceDate());

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
    public List<Invoice> getInvoiceByEmail(String email) {
        List<Invoice> invoiceList = new ArrayList<Invoice>();

        try (Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from invoice where email = ?")){
            while (resultSet.next()) {
                String invoiceNumber = resultSet.getString(1);
                String customerEmail = resultSet.getString(2);
                String date = resultSet.getString(3);
                Invoice invoice = new Invoice(invoiceNumber, customerEmail, date);
                invoiceList.add(invoice);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return invoiceList;
    }

    @Override
    public boolean addItemInvoice(ItemInvoice itemInvoice) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("insert into itemInvoice (itemCode, itemName, price, invoiceNumber) values (?, ?, ?, ?)");
            preparedStatement.setString(1, itemInvoice.getItemCode());
            preparedStatement.setString(2, itemInvoice.getItemName());
            preparedStatement.setString(3, Double.toString(itemInvoice.getPrice()));
            preparedStatement.setString(4, itemInvoice.getInvoiceNumber());

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
