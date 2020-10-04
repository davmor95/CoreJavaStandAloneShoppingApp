package repo;

import model.CustomerOrder;
import model.Invoice;
import model.ItemInvoice;

import java.util.List;

public interface CustomerServiceRepoImpl {
    public boolean addOrder(CustomerOrder order);
    public boolean deleteAllOrdersByEmail(String email);
    public List<CustomerOrder> getAllCustomerOrderByEmail(String email);
    public boolean addInvoice(Invoice invoice);
    public List<Invoice> getInvoiceByEmail(String email);
    public boolean addItemInvoice(ItemInvoice itemInvoice);
    public List<ItemInvoice> getAllItemsByInvoiceNumber(String invoiceNumber);
    public boolean updateItem(ItemInvoice item);
    public boolean updateInvoice(Invoice invoice);


}
