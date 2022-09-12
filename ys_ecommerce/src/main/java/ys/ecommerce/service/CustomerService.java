package ys.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ys.ecommerce.dto.Order.OrderDTO;
import ys.ecommerce.dto.Product.ProductDTO;
import ys.ecommerce.dto.Review.CommentDTO;
import ys.ecommerce.dto.Review.ReviewDTO;
import ys.ecommerce.model.Order.Order;
import ys.ecommerce.model.Product.Product;
import ys.ecommerce.model.Review.Comment;
import ys.ecommerce.model.Review.Review;
import ys.ecommerce.model.User.Vendor;
import ys.ecommerce.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CommentRepository commentRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final ReviewRepository reviewRepository;

    private Vendor getVendorFromOptional(String username) throws Exception {
        Optional<Vendor> optVendor = vendorRepository.getVendorByUsername(username);
        if(optVendor.isPresent()){
            return optVendor.get();
        }
        else throw new Exception("No vendors exist with that username");
    }

    public List<ProductDTO> getProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product p : products) productDTOs.add(new ProductDTO(p));
        return productDTOs;
    }

    public List<ProductDTO> getProductsByName(String name){
        List<Product> products = productRepository.findAllByNameContaining(name);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product p : products) productDTOs.add(new ProductDTO(p));
        return productDTOs;
    }

    public List<ProductDTO> getProductsByDescription(String description){
        List<Product> products = productRepository.findAllByDescriptionContaining(description);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product p : products) productDTOs.add(new ProductDTO(p));
        return productDTOs;
    }

    public List<ProductDTO> getProductsByVendor(String username) throws Exception {
        List<Product> products = productRepository.findAllByVendorId(getVendorFromOptional(username).getId());
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product p : products) productDTOs.add(new ProductDTO(p));
        return productDTOs;
    }

    public List<ProductDTO> getProductsWithFreeDelivery(){
        List<Product> products = productRepository.findAllByDeliveryFeeIsLessThanEqual(0.00);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product p : products) productDTOs.add(new ProductDTO(p));
        return productDTOs;
    }

    public List<ProductDTO> getProductsByPrice(double price){
        List<Product> products = productRepository.findAllByPriceIsLessThanEqual(price);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product p : products) productDTOs.add(new ProductDTO(p));
        return productDTOs;
    }

    public List<CommentDTO> getVendorComments(long vendorId){
        List<Comment> comments = commentRepository.findAllByVendorId(vendorId);
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for(Comment c : comments) commentDTOs.add(new CommentDTO(c));
        return commentDTOs;
    }

    public Optional<CommentDTO> createComment(Comment comment){
        commentRepository.save(comment);
        return Optional.of(new CommentDTO(comment));
    }

    public List<ReviewDTO> getVendorReviews(long vendorId){
        List<Review> reviews = reviewRepository.findAllByVendorId(vendorId);
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        for(Review r : reviews) reviewDTOs.add(new ReviewDTO(r));
        return reviewDTOs;
    }

    public Optional<ReviewDTO> createReview(Review review){
        reviewRepository.save(review);
        return Optional.of(new ReviewDTO(review));
    }

    public List<OrderDTO> getOrderHistory(long customerId){
        List<Order> orders = orderRepository.findAllByBuyerId(customerId);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for(Order o : orders) orderDTOs.add(new OrderDTO(o));
        return orderDTOs;
    }

    public Optional<OrderDTO> createOrder(Order order){
        orderRepository.save(order);
        return Optional.of(new OrderDTO(order));
    }


}
