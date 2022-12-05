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
import ys.ecommerce.model.Products.CartItem;
import ys.ecommerce.model.Products.Product;
import ys.ecommerce.model.Review.Comment;
import ys.ecommerce.model.Review.Review;
import ys.ecommerce.model.User.Customer;
import ys.ecommerce.model.User.Vendor;
import ys.ecommerce.repository.*;

import javax.transaction.Transactional;
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

    private Customer getCustomerWithOrders(Long id) throws Exception{
        Optional<Customer> optCustomer = customerRepository.getCustomerWithOrders(id);
        if(optCustomer.isPresent()){
            Customer c = optCustomer.get();
            c.setOrders(orderRepository.findAllByBuyerId(id));
            return c;
        }

        else throw new Exception("No customer exists with that id");
    }

    private Customer getCustomerWithCart(Long id) throws Exception {
        Optional<Customer> optCustomer = customerRepository.getCustomerWithCart(id);
        if(optCustomer.isPresent()){
            Customer c = optCustomer.get();
            c.setCart(cartItemRepository.findAllByCustomerId(id));
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
        Customer customer = getCustomerWithOrders(Long.parseLong(commentDTO.getCustomer()));
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
        List<CommerceOrder> orders = orderRepository.findAllByBuyerId(customer.getId());
        for(CommerceOrder o : orders){
            System.out.println(o.getProducts().size());
            for(CartItem item : o.getProducts()){
                System.out.println(item.getId());
                if(item.getProduct().getVendor().getId() == vendor.getId()) return true;
            }
        }
        return true;
    }
    public Optional<ReviewDTO> createReview(ReviewDTO reviewDTO) throws Exception{
        Customer customer = getCustomerWithOrders(Long.parseLong(reviewDTO.getCustomer()));
        Vendor vendor = getVendorFromOptional(Long.parseLong(reviewDTO.getVendor()));
        if(!hasOrderedFromTheVendor(customer, vendor)) throw new Exception("The customer hasn't ordered from the vendor - impossible to leave a review");
        Review review = new Review(customer, vendor, Integer.parseInt(reviewDTO.getRating()), reviewDTO.getComment());
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
        Customer c = getCustomerWithOrders(customerId);
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
        Customer c = getCustomerWithCart(customerId);
        c.setOrders(orderRepository.findAllByBuyerId(customerId));
        CommerceOrder order = new CommerceOrder(c, LocalDate.now(), LocalDate.now().plusDays(7));
        c.getOrders().add(order);
        c.getCart().clear();
        orderRepository.save(order);
        customerRepository.save(c);
        return Optional.of(new OrderDTO(order));
    }

    public Optional<ProductDTO> addProductToCart(ProductDTO productDTO, long customerId, int amount) throws Exception {
        Customer c = getCustomerWithCart(customerId);
        Product product = getProductFromOptional(Long.parseLong(productDTO.getId()));
        checkProductAvailability(product, amount);
        CartItem item = new CartItem(amount, product, c);
        c.getCart().add(item);
        product.setStock(product.getStock() - amount);
        cartItemRepository.save(item);
        customerRepository.save(c);
        productRepository.save(product);
        return Optional.of(new ProductDTO(product));
    }

    public Optional<UserDTO> addFundsToBalance(Long customerId, double amount) throws Exception {
        Customer c = getCustomerWithCart(customerId);
        c.setBalance(c.getBalance() + amount);
        customerRepository.save(c);
        return Optional.of(new UserDTO(c));
    }



    public Optional<ProductDTO> removeProductFromCart(int index, long customerId) throws Exception {
        Customer c = getCustomerWithCart(customerId);
        CartItem product = c.getCart().get(index);
        c.getCart().remove(product);
        customerRepository.save(c);
        return Optional.of(new ProductDTO(product.getProduct()));
    }

    public List<CartItemDTO> getProductsInCart(long customerId) throws Exception{
        Customer c = getCustomerWithCart(customerId);
        List<CartItemDTO> items = new ArrayList<>();
        for(CartItem item : c.getCart()){
            items.add(new CartItemDTO(item));
        }
        return items;
    }
}
