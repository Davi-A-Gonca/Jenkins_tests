package org.example.services;

import org.example.objects.DTO.OrderDTO;
import org.example.objects.Order;
import org.example.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService service;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
        order.setId(1L);
        order.setNameOfCustomer("Felipe");
        order.setTotalProducts(5);
        order.setTotalPrice(new BigDecimal("150.00"));
    }

    @Test
    void testSave() {
        when(repository.save(any(Order.class))).thenReturn(order);

        OrderDTO dto = new OrderDTO("Felipe", 5, new BigDecimal("150.00"));
        Order savedOrder = service.save(dto);

        assertNotNull(savedOrder);
        assertEquals("Felipe", savedOrder.getNameOfCustomer());
        verify(repository, times(1)).save(any(Order.class));
    }

    @Test
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> found = service.findById(1L);

        assertTrue(found.isPresent());
        assertEquals(1L, found.get().getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(order));

        List<Order> list = service.findAll();

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        verify(repository, times(1)).findAll();
    }

}
