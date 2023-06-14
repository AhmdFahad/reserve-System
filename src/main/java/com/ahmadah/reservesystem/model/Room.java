package com.ahmadah.reservesystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "room_id", nullable = false)
    private Integer id;

    @Column(name = "room_availability", nullable = false)
    private Boolean availability;

    @Column(name = "room_type")
    private String type;

    @Column(name = "room_floor")
    private Integer floor;

    @Column(name = "room_price", nullable = false)
    private Double price;
}

