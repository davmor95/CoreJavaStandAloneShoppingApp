package model;

public class CustomerOrder {
    private Integer orderID;
    private String itemCode;
    private String itemName;
    private Double price;
    private String email;

    public CustomerOrder() {
        this("N/A", "N/A", 0.0, "N/A");
    }



    public CustomerOrder(String itemCode, String itemName, Double price, String email) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.price = price;
        this.email = email;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderID='" + orderID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", email='" + email + '\'' +
                '}';
    }
}
