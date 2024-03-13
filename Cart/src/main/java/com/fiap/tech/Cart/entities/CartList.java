package com.fiap.tech.Cart.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "CartList")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID idCart;


    @Column(nullable = false, columnDefinition = "varchar(36)", unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID idProduct;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;
}
