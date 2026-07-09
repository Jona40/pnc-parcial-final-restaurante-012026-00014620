package com.uca.pncparcialfinalrestaurante.config.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant; // Relación para filtrar por sucursal

    private String status; // Ejemplo: PENDING, CONFIRMED
}