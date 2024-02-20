package org.launchcode.givewise.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.launchcode.givewise.models.Product;
import org.launchcode.givewise.models.data.ProductRepository;
import org.launchcode.givewise.request.ProductRequest;

import org.launchcode.givewise.service.MailjetService;

import org.launchcode.givewise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.launchcode.givewise.config.SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private MailjetService mailjetService;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;
    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productDto){
        Product product = productService.addProduct(productDto);
        log.info("Product added: {}", product);
       //mailjetService.notifySubscriberOnNewProduct(product);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> products = productRepository.getAllProducts();

        return ResponseEntity.ok(products);
    };
    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        try{
            productService.deleteProduct(id);
            log.info("Product deleted with id: {}", id);
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            log.error("Error deleting product with id: {}", id, e);
            return ResponseEntity.status(500).build();
        }
    }
}

