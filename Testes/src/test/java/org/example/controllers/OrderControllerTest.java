package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Constants;
import org.example.objects.DTO.OrderDTO;
import org.example.objects.Order;
import org.example.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService service;

    @InjectMocks
    private OrderController controller;

    private Order order;

    @BeforeEach
    void setUp() {
        // Inicializa o MockMvc standalone sem contexto Spring
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        // Objeto base para os testes
        order = new Order();
        order.setId(1L);
        order.setNameOfCustomer("Felipe");
        order.setTotalProducts(3);
        order.setTotalPrice(new BigDecimal("100.00"));
    }

    @Test
    void testCreate() throws Exception {
        when(service.save(any(OrderDTO.class))).thenReturn(order);

        OrderDTO dto = new OrderDTO("Felipe", 3, new BigDecimal("100.00"));

        mockMvc.perform(post(Constants.API_ORDER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nameOfCustomer").value("Felipe"));
    }

    @Test
    void testFindAll() throws Exception {
        when(service.findAll()).thenReturn(List.of(order));

        mockMvc.perform(get(Constants.API_ORDER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nameOfCustomer").value("Felipe"));
    }

    @Test
    void testFindById() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(order));

        mockMvc.perform(get(Constants.API_ORDER + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

}
