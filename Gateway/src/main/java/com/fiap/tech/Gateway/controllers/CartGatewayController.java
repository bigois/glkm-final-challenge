package com.fiap.tech.Gateway.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fiap.tech.Gateway.dtos.*;
import com.fiap.tech.Gateway.services.CartGatewayService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
public class CartGatewayController {

    private final CartGatewayService cartGatewayService;

    public CartGatewayController(CartGatewayService cartGatewayService) {
        this.cartGatewayService = cartGatewayService;
    }

    @GetMapping("/all-items/{id}")
    public ResponseEntity<Mono<CartCompleteResponseDTO>> getAllItemsOnCart(@PathVariable UUID id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        return ResponseEntity.status(HttpStatus.OK).body(cartGatewayService.getAllItemsOnCart(id, token));
    }

    @PostMapping
    public ResponseEntity<Mono<CartCreateResponseDTO>> createCart(@RequestBody @Valid CartCreateRequestDTO cartCreateRequestDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        /*String jwtToken = request.getHeader("Authorization").replace("Token: ", "");

        Algorithm algorithm = Algorithm.HMAC256("baeldung");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Baeldung")
                .build();

        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = verifier.verify(jwtToken);
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
        }

        UUID userId = UUID.fromString(decodedJWT.getClaim("userId").asString());
        CartCreateRequestDTO cartCreateRequestDTOWithUserId = new CartCreateRequestDTO(userId, cartCreateRequestDTO.idProduct(), cartCreateRequestDTO.price(), cartCreateRequestDTO.quantity());*/
        return ResponseEntity.status(HttpStatus.CREATED).body(cartGatewayService.createCart(cartCreateRequestDTO, token));
    }

    @PostMapping(path = "/add-item-cart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<CartAddResponseDTO>> addItemCart(@PathVariable UUID id, @RequestBody @Valid CartAddRequestDTO cartAddRequestDTO) throws JSONException {
        Mono<CartAddResponseDTO> cartAddResponse = cartGatewayService.addItemCart(id, cartAddRequestDTO);

        JSONObject responseBody = new JSONObject();
        responseBody.put("message", "Item successfully added");

        return ResponseEntity.status(HttpStatus.CREATED).body(cartAddResponse);
    }

    @PutMapping(path = "/add-quantity-item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<CartAddResponseDTO>> addQuantityItemCart(@PathVariable UUID id, @RequestBody @Valid CartQuantityRequestDTO cartQuantityRequestDTO) throws JSONException {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartGatewayService.addQuantityItemCart(id, cartQuantityRequestDTO));
    }

    @PutMapping(path = "/remove-quantity-item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<CartAddResponseDTO>> removeQuantityItemCart(@PathVariable UUID id, @RequestBody @Valid CartQuantityRequestDTO cartQuantityRequestDTO) throws JSONException {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartGatewayService.removeQuantityItemCart(id, cartQuantityRequestDTO));
    }

    @DeleteMapping(path = "/remove-item-cart/{idCart}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<String>> removeItemCartList(@PathVariable UUID idCart, @RequestParam UUID idProduct){
        return ResponseEntity.status(HttpStatus.OK).body(cartGatewayService.removeItemCart(idCart, idProduct));
    }

    @DeleteMapping(path = "/delete-cart-items/{idCart}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<String>> removeAllItemsCart(@PathVariable UUID idCart){
        return ResponseEntity.status(HttpStatus.OK).body(cartGatewayService.removeAllItemsCart(idCart));
    }
}
