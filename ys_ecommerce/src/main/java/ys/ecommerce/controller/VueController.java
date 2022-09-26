package ys.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ys.ecommerce.dto.Order.OrderDTO;
import ys.ecommerce.dto.Product.ProductDTO;
import ys.ecommerce.dto.Review.CommentDTO;
import ys.ecommerce.dto.Review.ReviewDTO;
import ys.ecommerce.dto.User.UserDTO;
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

    @GetMapping("/vendors/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> getVendorReviews(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.getVendorReviews(id), HttpStatus.OK);
    }

    @GetMapping("/vendors/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getVendorComments(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.getVendorComments(id), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}/orders/active")
    public ResponseEntity<List<OrderDTO>> getCustomerActiveOrders(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(customerService.getActiveOrders(id), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}/orders")
    public ResponseEntity<List<OrderDTO>> getCustomerOrders(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.getOrderHistory(id), HttpStatus.OK);
    }

    @PostMapping("/vendors")
    public ResponseEntity<UserDTO> createVendor(@RequestBody UserDTO userDTO){
        return userService.createVendorAccount(userDTO).map(user -> ResponseEntity.status(HttpStatus.CREATED).body((user))).orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping("/customers")
    public ResponseEntity<UserDTO> createCustomer(@RequestBody UserDTO userDTO){
        return userService.createCustomerAccount(userDTO).map(user -> ResponseEntity.status(HttpStatus.CREATED).body((user))).orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping("/customer/{id}/orders")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        return customerService.createOrder(orderDTO).map(order -> ResponseEntity.status(HttpStatus.CREATED).body(order)).orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping("/customer/{id}/cart/add")
    public ResponseEntity<ProductDTO> addProductToCart(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) throws Exception {
        return customerService.addProductToCart(productDTO, id, 1).map(product -> ResponseEntity.status(HttpStatus.CREATED).body(product)).orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) throws Exception {
        return vendorService.addProductToInventory(productDTO).map(product -> ResponseEntity.status(HttpStatus.CREATED).body(product)).orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
