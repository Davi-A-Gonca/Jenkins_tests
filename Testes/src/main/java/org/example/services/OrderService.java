package org.example.services;

import org.example.objects.DTO.OrderDTO;
import org.example.objects.Order;
import org.example.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order save(OrderDTO dto) {
        Order order = new Order(dto);
        return repository.save(order);
    }

    public Order save(Order entity) {
        return repository.save(entity);
    }

    public Optional<Order> findById(long id) {
        return repository.findById(id);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

}
