package com.example.hw28.Controller;


import com.example.hw28.Model.Product;
import com.example.hw28.Model.User;
import com.example.hw28.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(200).body("done added");
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity updateProduct(@PathVariable Integer productId, @RequestBody @Valid Product product) {
        productService.updateProduct(productId, product);
        return ResponseEntity.status(200).body("done updated");
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(200).body("done deleted");
    }

    @GetMapping("/get-all-product")
    public ResponseEntity getAllProduct() {
        List<Product> productList = productService.getAllProduct();
        return ResponseEntity.status(200).body(productList);
    }


}
