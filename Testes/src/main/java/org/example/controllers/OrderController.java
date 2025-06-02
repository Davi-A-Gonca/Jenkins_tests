package org.example.controllers;

import org.example.Constants;
import org.example.objects.DTO.OrderDTO;
import org.example.objects.Order;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constants.API_ORDER)
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderDTO dto) {
        Order order = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") long id) {
        Optional<Order> order = service.findById(id);
        return order
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
