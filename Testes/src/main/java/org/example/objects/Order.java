package org.example.objects;

import jakarta.persistence.*;
import org.example.objects.DTO.OrderDTO;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 🔥 Garante que o banco gera o ID
    private Long id;

    @Column(nullable = false)
    private String nameOfCustomer;

    @Column(nullable = false)
    private int totalProducts;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    // 🔧 Construtor padrão é obrigatório para JPA
    public Order() {}

    public Order(OrderDTO dto) {
        this.nameOfCustomer = dto.getNameOfCustomer();
        this.totalProducts = dto.getTotalProducts();
        this.totalPrice = dto.getTotalPrice();
    }

    // 🔥 Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
