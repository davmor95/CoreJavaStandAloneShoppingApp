package model;

import java.util.Date;

public class Invoice {
    private String invoiceNumber;
    private String email;
    private String invoiceDate;
    private Double totalCost;

    public Invoice() {
    }

    public Invoice(String invoiceNumber, String email, String invoiceDate, Double totalCost) {
        this.invoiceNumber = invoiceNumber;
        this.email = email;
        this.invoiceDate = invoiceDate;
        this.totalCost = totalCost;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", email='" + email + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }
}
