package com.fiap.tech.Stock.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Stocks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(nullable = false, columnDefinition = "varchar(36)", unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID idProduct;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}

