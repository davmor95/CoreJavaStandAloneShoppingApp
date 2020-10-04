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
            preparedStatement.setString(1, email);
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

        try( PreparedStatement preparedStatement = conn.prepareStatement("select * from CustomerOrder where email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = conn.prepareStatement("insert into invoice (invoiceNumber, email, invoiceDate, totalCost) values (?, ?, ?, ?)");
            preparedStatement.setString(1, invoice.getInvoiceNumber());
            preparedStatement.setString(2, invoice.getEmail());
            preparedStatement.setString(3, invoice.getInvoiceDate());
            preparedStatement.setDouble(4, invoice.getTotalCost());


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

//TODO redo this function
    @Override
    public List<Invoice> getInvoiceByEmail(String email) {
        List<Invoice> invoiceList = new ArrayList<Invoice>();

        try (PreparedStatement preparedStatement = conn.prepareStatement("select * from invoice where email = ?")){
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String invoiceNumber = resultSet.getString(1);
                String customerEmail = resultSet.getString(2);
                String date = resultSet.getString(3);
                Double totalCost = resultSet.getDouble(4);
                Invoice invoice = new Invoice(invoiceNumber, customerEmail, date, totalCost);
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
            preparedStatement.setDouble(3, itemInvoice.getPrice());
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

    @Override
    public List<ItemInvoice> getAllItemsByInvoiceNumber(String invoiceNumber) {
        List<ItemInvoice> itemInvoiceList = new ArrayList<ItemInvoice>();
        try (PreparedStatement preparedStatement = conn.prepareStatement("select * from itemInvoice where invoiceNumber = ?")){
            preparedStatement.setString(1, invoiceNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer itemId = resultSet.getInt(1);
                String itemCode = resultSet.getString(2);
                String itemName = resultSet.getString(3);
                Double price = resultSet.getDouble(4);
                String invoiceNum = resultSet.getString(5);

                ItemInvoice itemInvoice = new ItemInvoice(itemId, itemCode, itemName, price, invoiceNum);
                itemInvoiceList.add(itemInvoice);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemInvoiceList;
    }

    @Override
    public boolean updateItem(ItemInvoice item) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("update itemInvoice set itemCode = ?, itemName = ?, price = ?, invoiceNumber = ? where itemId = ?")){
            preparedStatement.setString(1, item.getItemCode());
            preparedStatement.setString(2, item.getItemName());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setString(4, item.getInvoiceNumber());
            preparedStatement.setInt(5, item.getItemId());

            int update = preparedStatement.executeUpdate();
            if(update > 0) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateInvoice(Invoice invoice) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("update invoice set invoiceDate = ?, totalCost = ? where invoiceNumber = ?")){
            preparedStatement.setString(1, invoice.getInvoiceDate());
            preparedStatement.setDouble(2, invoice.getTotalCost());
            preparedStatement.setString(3, invoice.getInvoiceNumber());

            int update = preparedStatement.executeUpdate();
            if(update > 0) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
