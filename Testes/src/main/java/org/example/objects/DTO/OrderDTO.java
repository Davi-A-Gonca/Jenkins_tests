package org.example.objects.DTO;

import java.math.BigDecimal;

public class OrderDTO {

    private String nameOfCustomer;
    private int totalProducts;
    private BigDecimal totalPrice;

    public OrderDTO() {}

    public OrderDTO(String nameOfCustomer, int totalProducts, BigDecimal totalPrice) {
        this.nameOfCustomer = nameOfCustomer;
        this.totalProducts = totalProducts;
        this.totalPrice = totalPrice;
    }

    public String getNameOfCustomer() {
        return nameOfCustomer;
    }

    public void setNameOfCustomer(String nameOfCustomer) {
        this.nameOfCustomer = nameOfCustomer;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
