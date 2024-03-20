package com.fiap.tech.Cart.repositories;

import com.fiap.tech.Cart.entities.CartList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CartListRepository extends JpaRepository<CartList, UUID> {
    CartList findByIdCart(UUID id);

    CartList findByIdProduct(UUID id);
    boolean existsByIdCart(UUID id);

    boolean existsByIdProduct(UUID id);

    void deleteByIdCartAndIdProduct(UUID idCart, UUID idProduct);

    CartList findByIdCartAndIdProduct(UUID idCart, UUID idProduct);


    List<CartList> findAllByIdCart(UUID idCart);
}
