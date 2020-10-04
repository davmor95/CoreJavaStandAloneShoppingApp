package model;

public class ItemInvoice {
    private Integer itemId;
    private String itemCode;
    private String itemName;
    private Double price;
    private String invoiceNumber;

    public ItemInvoice(Integer itemId, String itemCode, String itemName, Double price, String invoiceNumber) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.price = price;
        this.invoiceNumber = invoiceNumber;
    }

    public ItemInvoice(String itemCode, String itemName, Double price, String invoiceNumber) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.price = price;
        this.invoiceNumber = invoiceNumber;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String toString() {
        return "ItemInvoice{" +
                "itemId=" + itemId +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                '}';
    }
}
