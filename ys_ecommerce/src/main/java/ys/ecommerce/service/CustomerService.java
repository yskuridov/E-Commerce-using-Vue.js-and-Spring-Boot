package ys.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ys.ecommerce.dto.Order.OrderDTO;
import ys.ecommerce.dto.Product.CartItemDTO;
import ys.ecommerce.dto.Product.ProductDTO;
import ys.ecommerce.dto.Review.CommentDTO;
import ys.ecommerce.dto.Review.ReviewDTO;
import ys.ecommerce.dto.User.UserDTO;
import ys.ecommerce.model.Order.CommerceOrder;
import ys.ecommerce.model.Product.CartItem;
import ys.ecommerce.model.Product.Product;
import ys.ecommerce.model.Review.Comment;
import ys.ecommerce.model.Review.Review;
import ys.ecommerce.model.User.Customer;
import ys.ecommerce.model.User.Vendor;
import ys.ecommerce.repository.*;

import java.time.LocalDate;
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
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;

    private Vendor getVendorFromOptional(String username) throws Exception {
        Optional<Vendor> optVendor = vendorRepository.getVendorByUsername(username);
        if(optVendor.isPresent()){
            return optVendor.get();
        }
        else throw new Exception("No vendors exist with that username");
    }

    private Vendor getVendorFromOptional(Long id) throws Exception {
        Optional<Vendor> optVendor = vendorRepository.getVendorById(id);
        if(optVendor.isPresent()){
            return optVendor.get();
        }
        else throw new Exception("No vendors exist with that username");
    }

    private Customer getCustomerFromOptional(Long id) throws Exception{
        Optional<Customer> optCustomer = customerRepository.getCustomerWithCart(id);
        if(optCustomer.isPresent()){
            Customer c = optCustomer.get();
            c.setOrders(orderRepository.findAllByBuyerId(id));
            return c;
        }

        else throw new Exception("No customer exists with that id");
    }

    private void checkProductAvailability(Product p, int desiredAmount) throws Exception{
        if(p.getStock() < desiredAmount){
            throw new Exception("The product doesn't have enough stock");
        }
    }

    private Product getProductFromOptional(Long id) throws Exception{
        Optional<Product> optProduct = productRepository.findProductById(id);
        if(optProduct.isPresent()){
            return optProduct.get();
        }
        else throw new Exception("No product exists with that id");
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

    public Optional<CommentDTO> createComment(CommentDTO commentDTO) throws Exception {
        Vendor vendor = getVendorFromOptional(Long.parseLong(commentDTO.getVendor()));
        if(!vendor.isHasOpenComments()) throw new Exception("The vendor doesn't have open comments enabled");
        Customer customer = getCustomerFromOptional(Long.parseLong(commentDTO.getCustomer()));
        Comment comment = new Comment(customer, vendor, commentDTO.getComment());
        vendor.getComments().add(comment);
        vendorRepository.save(vendor);
        commentRepository.save(comment);
        return Optional.of(new CommentDTO(comment));
    }

    public List<ReviewDTO> getVendorReviews(long vendorId){
        List<Review> reviews = reviewRepository.findAllByVendorId(vendorId);
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        for(Review r : reviews) reviewDTOs.add(new ReviewDTO(r));
        return reviewDTOs;
    }

    private boolean hasOrderedFromTheVendor(Customer customer, Vendor vendor){
        for(CommerceOrder o : customer.getOrders()){
            for(CartItem item : o.getProducts()){
                if(item.getProduct().getVendor() == vendor) return true;
            }
        }
        return false;
    }
    public Optional<ReviewDTO> createReview(ReviewDTO reviewDTO) throws Exception {
        Customer customer = getCustomerFromOptional(Long.parseLong(reviewDTO.getCustomer()));
        Vendor vendor = getVendorFromOptional(Long.parseLong(reviewDTO.getVendor()));
        if(!hasOrderedFromTheVendor(customer, vendor)) throw new Exception("The customer hasn't ordered from the vendor - impossible to leave a review");
        Review review = new Review(customer, vendor, Integer.parseInt(reviewDTO.getRating()));
        reviewRepository.save(review);
        return Optional.of(new ReviewDTO(review));
    }

    public List<OrderDTO> getOrderHistory(long customerId){
        List<CommerceOrder> orders = orderRepository.findAllByBuyerId(customerId);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for(CommerceOrder o : orders) orderDTOs.add(new OrderDTO(o));
        return orderDTOs;
    }

    public List<OrderDTO> getActiveOrders(Long customerId) throws Exception{
        Customer c = getCustomerFromOptional(customerId);
        List<OrderDTO> activeOrders = new ArrayList<>();
        for(CommerceOrder o : c.getOrders()){
            if(o.getDeliveryDate().isAfter(LocalDate.now())){
                activeOrders.add(new OrderDTO(o));
            }
        }
        return activeOrders;
    }
    public Optional<OrderDTO> createOrder(OrderDTO orderDTO) throws Exception{
        Long customerId = Long.parseLong(orderDTO.getBuyer());
        Customer c = getCustomerFromOptional(customerId);
        CommerceOrder order = new CommerceOrder(c, new ArrayList<>(c.getCart()), LocalDate.now(), LocalDate.now().plusDays(7));
        c.getOrders().add(order);
        c.getCart().clear();
        orderRepository.save(order);
        customerRepository.save(c);
        return Optional.of(new OrderDTO(order));
    }

    public Optional<ProductDTO> addProductToCart(ProductDTO productDTO, long customerId, int amount) throws Exception {
        Customer c = getCustomerFromOptional(customerId);
        Product product = getProductFromOptional(Long.parseLong(productDTO.getId()));
        checkProductAvailability(product, amount);
        CartItem item = new CartItem(amount, product);
        c.getCart().add(item);
        product.setStock(product.getStock() - amount);
        cartItemRepository.save(item);
        customerRepository.save(c);
        productRepository.save(product);
        return Optional.of(new ProductDTO(product));
    }

    public Optional<UserDTO> addFundsToBalance(Long customerId, double amount) throws Exception {
        Customer c = getCustomerFromOptional(customerId);
        c.setBalance(c.getBalance() + amount);
        customerRepository.save(c);
        return Optional.of(new UserDTO(c));
    }



    public Optional<ProductDTO> removeProductFromCart(int index, long customerId) throws Exception {
        Customer c = getCustomerFromOptional(customerId);
        CartItem product = c.getCart().get(index);
        c.getCart().remove(index);
        customerRepository.save(c);
        return Optional.of(new ProductDTO(product.getProduct()));
    }

    public List<CartItemDTO> getProductsInCart(long customerId) throws Exception{
        Customer c = getCustomerFromOptional(customerId);
        List<CartItemDTO> items = new ArrayList<>();
        for(CartItem item : c.getCart()){
            items.add(new CartItemDTO(item));
        }
        return items;
    }
}
