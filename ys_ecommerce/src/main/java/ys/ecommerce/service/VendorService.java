package ys.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ys.ecommerce.dto.Product.ProductDTO;
import ys.ecommerce.dto.User.UserDTO;
import ys.ecommerce.model.Products.Product;
import ys.ecommerce.model.User.Vendor;
import ys.ecommerce.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorService {
    private final CommentRepository commentRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final ReviewRepository reviewRepository;

    private Vendor getVendorFromOptional(Long id) throws Exception{
        Optional<Vendor> optVendor = vendorRepository.getVendorById(id);
        if(optVendor.isPresent()) return optVendor.get();
        else throw new Exception("The vendor does not exist");
    }

    private Product getProductFromOptional(Long id) throws Exception{
        Optional<Product> optProduct = productRepository.findProductById(id);
        if(optProduct.isPresent()) return optProduct.get();
        else throw new Exception("The product does not exist");
    }
    public List<ProductDTO> getInventory(Long id) throws Exception {
        Vendor v = getVendorFromOptional(id);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product p : v.getInventory()) productDTOs.add(new ProductDTO(p));
        System.out.println(productDTOs.size());
        return productDTOs;
    }

    public Optional<ProductDTO> updateProductStock(Long id, int newStock) throws Exception {
        Product product = getProductFromOptional(id);
        product.setStock(newStock);
        productRepository.save(product);
        return Optional.of(new ProductDTO(product));
    }

    public Optional<ProductDTO> updateProductPrice(Long id, double newPrice) throws Exception {
        Product product = getProductFromOptional(id);
        product.setPrice(newPrice);
        productRepository.save(product);
        return Optional.of(new ProductDTO(product));
    }

    public Optional<ProductDTO> updateProductDeliveryFee(Long id, double newFee) throws Exception {
        Product product = getProductFromOptional(id);
        product.setDeliveryFee(newFee);
        productRepository.save(product);
        return Optional.of(new ProductDTO(product));
    }

    public Optional<ProductDTO> updateProduct(ProductDTO updProduct) throws Exception {
        Product product = getProductFromOptional(Long.parseLong(updProduct.getId()));
        product.setName(updProduct.getName());
        product.setDescription(updProduct.getDescription());
        product.setPrice(Double.parseDouble(updProduct.getPrice()));
        product.setDeliveryFee(Double.parseDouble(updProduct.getDeliveryFee()));
        product.setStock(Integer.parseInt(updProduct.getStock()));
        product.setImageUrl(updProduct.getImageUrl());
        productRepository.save(product);
        return Optional.of(new ProductDTO(product));
    }

    public Optional<ProductDTO> addProductToInventory(ProductDTO productDTO) throws Exception {
        Vendor v = getVendorFromOptional(Long.parseLong(productDTO.getSoldBy()));
        Product p = new Product(productDTO.getName(), productDTO.getImageUrl(), productDTO.getDescription(), Double.parseDouble(productDTO.getPrice()), Double.parseDouble(productDTO.getDeliveryFee()), Integer.parseInt(productDTO.getStock()), v);
        productRepository.save(p);
        v.getInventory().add(p);
        vendorRepository.save(v);
        return Optional.of(new ProductDTO(p));
    }

    public Optional<UserDTO> closeVendorComments(Long id) throws Exception{
        Vendor v = getVendorFromOptional(id);
        if(v.isHasOpenComments()) v.setHasOpenComments(false);
        else v.setHasOpenComments(true);
        vendorRepository.save(v);
        return Optional.of(new UserDTO(v));
    }

    public Optional<ProductDTO> getProductById(Long id) throws Exception{
        Optional<Product> p = productRepository.findProductById(id);
        if(p.get() != null) return Optional.of(new ProductDTO(p.get()));
        else throw new Exception("The vendor does not exist");
    }

}
