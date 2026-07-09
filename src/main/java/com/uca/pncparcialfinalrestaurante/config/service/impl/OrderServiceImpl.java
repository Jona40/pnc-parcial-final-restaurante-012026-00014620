package com.uca.pncparcialfinalrestaurante.config.service.impl;

import com.uca.pncparcialfinalrestaurante.config.domain.entity.Order;
import com.uca.pncparcialfinalrestaurante.config.domain.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public void updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow();

        // Obtenemos el usuario logueado
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Validación de atributo: ¿El encargado es de la misma sucursal?
        if (!currentUser.getRestaurant().getId().equals(order.getRestaurant().getId())) {
            throw new AccessDeniedException("No tienes permiso para gestionar pedidos de otra sucursal.");
        }

        order.setStatus(newStatus);
        orderRepository.save(order);
    }
}