package ys.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ys.ecommerce.dto.Product.ProductDTO;
import ys.ecommerce.model.Product.Product;
import ys.ecommerce.service.CustomerService;
import ys.ecommerce.service.UserService;
import ys.ecommerce.service.VendorService;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "https://localhost:3000")
@RequiredArgsConstructor
public class VueController {
    private final CustomerService customerService;
    private final UserService userService;
    private final VendorService vendorService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProductsList(){
        return new ResponseEntity<>(customerService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/name/{name}")
    public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable("name") String name){
        return new ResponseEntity<>(customerService.getProductsByName(name), HttpStatus.OK);
    }

    @GetMapping("/products/description/{desc}")
    public ResponseEntity<List<ProductDTO>> getProductsByDescription(@PathVariable("desc") String description){
        return new ResponseEntity<>(customerService.getProductsByDescription(description), HttpStatus.OK);
    }

    @GetMapping("/products/vendor/{name}")
    public ResponseEntity<List<ProductDTO>> getProductsByVendorName(@PathVariable("name") String name) throws Exception {
        return new ResponseEntity<>(customerService.getProductsByVendor(name), HttpStatus.OK);
    }

    @GetMapping("/products/freedelivery")
    public ResponseEntity<List<ProductDTO>> getProductsWithFreeDelivery(){
        return new ResponseEntity<>(customerService.getProductsWithFreeDelivery(), HttpStatus.OK);
    }

    @GetMapping("/products/price/{price}")
    public ResponseEntity<List<ProductDTO>> getProductsByPrice(@PathVariable("price") double price){
        return new ResponseEntity<>(customerService.getProductsByPrice(price), HttpStatus.OK);
    }

 }
