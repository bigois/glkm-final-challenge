package com.fiap.tech.Cart.repositories;

import com.fiap.tech.Cart.entities.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Page<Cart> findAll(Pageable pageable);
}

