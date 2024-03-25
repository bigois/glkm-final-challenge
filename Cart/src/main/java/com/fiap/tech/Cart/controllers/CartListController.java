package com.fiap.tech.Cart.controllers;

import com.fiap.tech.Cart.entities.CartList;
import com.fiap.tech.Cart.services.CartListService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/cartList", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartListController {

    @Autowired
    private CartListService cartListService;

    @GetMapping("/{id}")
    public ResponseEntity<CartList>getByCartListId(@PathVariable UUID id) {
        CartList cartList = cartListService.getByCartId(id);

        return ResponseEntity.status(HttpStatus.OK).body(cartList);
    }

    @GetMapping("/all-items/{id}")
    public ResponseEntity<List<CartList>>getAllCartListById(@PathVariable UUID id) {
        List<CartList> cartList = cartListService.getAllByCartId(id);

        return ResponseEntity.status(HttpStatus.OK).body(cartList);
    }

    @PostMapping("/{idCart}")
    public ResponseEntity<CartList> createCartList(@PathVariable UUID idCart, @RequestParam UUID idProduct, @RequestParam BigDecimal price, @RequestParam int quantity){
        CartList cartList = cartListService.createCartList(idCart,idProduct,price,quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartList);
    }

    @PostMapping("/add-cartList-item/{idCart}")
    public ResponseEntity<CartList> addItemCartList(@PathVariable UUID idCart, @RequestParam UUID idProduct, @RequestParam BigDecimal price, @RequestParam int quantity) throws JSONException {
        CartList cartList = cartListService.addItemCartList(idCart,idProduct, price, quantity);

        JSONObject responseBody = new JSONObject();
        responseBody.put("message", "Item successfully added");
        return ResponseEntity.status(HttpStatus.OK).body(cartList);
    }

    @PutMapping("/add-quantity-item/{idCart}")
    public ResponseEntity<CartList> addQuantityItem(@PathVariable UUID idCart, @RequestParam UUID idProduct, @RequestParam String quantity){
        CartList cartList = cartListService.addQuantityItem(idCart, idProduct, quantity);

        return ResponseEntity.status(HttpStatus.OK).body(cartList);
    }

    @PutMapping("/remove-quantity-item/{idCart}")
    public ResponseEntity<CartList> removeQuantityItem(@PathVariable UUID idCart, @RequestParam UUID idProduct, @RequestParam String quantity){
        CartList cartList = cartListService.removeQuantityItem(idCart, idProduct, quantity);

        return ResponseEntity.status(HttpStatus.OK).body(cartList);
    }

    @DeleteMapping("/remove-cartList-item/{idCart}")
    public ResponseEntity<String> removeItemCartList(@PathVariable UUID idCart, @RequestParam UUID idProduct) throws JSONException {
        cartListService.removeItemCartList(idCart, idProduct);

        JSONObject responseBody = new JSONObject();
        responseBody.put("message", "Item successfully removed");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody.toString());
    }

    @DeleteMapping("/remove-cartList-all-item/{idCart}")
    public ResponseEntity<String> removeAllItemsCartList(@PathVariable UUID idCart) throws JSONException {
        cartListService.removeAllItemsCartList(idCart);

        JSONObject responseBody = new JSONObject();
        responseBody.put("message", "Items successfully removed");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody.toString());
    }
}
