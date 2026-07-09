package com.uca.pncparcialfinalrestaurante.config.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestBody String status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok("Pedido actualizado correctamente");
    }
}