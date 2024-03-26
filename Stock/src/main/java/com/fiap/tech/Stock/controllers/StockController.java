package com.fiap.tech.Stock.controllers;

import com.fiap.tech.Stock.entities.Stock;
import com.fiap.tech.Stock.services.StockService;
import jakarta.validation.Valid;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/stocks", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getProductStock(@PathVariable UUID id){
        Stock stock = stockService.getByProductId(id);

        return ResponseEntity.status(HttpStatus.OK).body(stock);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Stock> createStock(@PathVariable UUID id){
        Stock stock = stockService.create(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(stock);
    }

    @PutMapping(path = "/add-stock-quantity/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addQuantityStock(@PathVariable UUID id, @RequestParam String quantity) throws JSONException {
        stockService.addQuantityStock(id, quantity);

        JSONObject responseBody = new JSONObject();
        responseBody.put("message", "quantity successfully added");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody.toString());
    }

    @PutMapping(path = "/remove-stock-quantity/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeQuantityStock(@PathVariable UUID id, @RequestParam String quantity) throws JSONException {
        stockService.removeQuantityStock(id, quantity);

        JSONObject responseBody = new JSONObject();
        responseBody.put("message", "quantity successfully removed");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody.toString());
    }

    @DeleteMapping("/remove-stock/{id}")
    public ResponseEntity<String> removeStock(@PathVariable UUID id) throws JSONException {
        stockService.removeStock(id);

        JSONObject responseBody = new JSONObject();
        responseBody.put("message", "stock successfully removed");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody.toString());
    }
}
