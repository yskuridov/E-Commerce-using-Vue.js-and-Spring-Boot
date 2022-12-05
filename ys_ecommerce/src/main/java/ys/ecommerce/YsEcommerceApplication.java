package ys.ecommerce;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ys.ecommerce.dto.Order.OrderDTO;
import ys.ecommerce.dto.Product.CartItemDTO;
import ys.ecommerce.dto.Product.ProductDTO;
import ys.ecommerce.dto.Review.ReviewDTO;
import ys.ecommerce.dto.User.UserDTO;
import ys.ecommerce.service.CustomerService;
import ys.ecommerce.service.UserService;
import ys.ecommerce.service.VendorService;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class YsEcommerceApplication implements CommandLineRunner {

    private final CustomerService cs;
    private final VendorService vs;
    private final UserService us;

    public static void main(String[] args) {
        SpringApplication.run(YsEcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        us.createCustomerAccount(new UserDTO("1", "Customer1", "111", "Dwayne", "Johnson", "222 Washington St", "500", false, false));
        us.createVendorAccount(new UserDTO("2", "Vendor1", "111", "Tim", "Cook", "222 Hollywood St", "99999", true, true));
        vs.addProductToInventory(new ProductDTO("1", "Iphone 13", "https://lzd-img-global.slatic.net/g/p/727027a4e41ff5704dc4bec7851992d1.jpg_720x720q80.jpg_.webp", "iPhone 13 Pink with a 64GB Storage", "500", "0", "50", "2", "Vendor1"));
        vs.addProductToInventory(new ProductDTO("2", "Iphone 14 Pro Max", "https://static.s-sfr.fr/media/catalogue/article/mobile/pw3iohmi/iPhone14Pro_VioletProfond_3-4-Side_Back_Left_400x540px.png", "iPhone 14 Pro Max Deep Purple with a 128GB Storage", "1000", "0", "50", "2", "Vendor1"));
        vs.addProductToInventory(new ProductDTO("3", "MacBook Air", "https://www.apple.com/v/macbook-air-m2/b/images/overview/design/design_top_starlight__dqra2r206k6e_large.jpg", "MacBook Air M2 - Gold", "1500", "0", "20", "2", "Vendor1"));
        List<CartItemDTO> items = new ArrayList<>();
        items.add(new CartItemDTO("1", "3", "4", "1"));
        cs.createOrder(new OrderDTO("1", "1", items, "2022-11-22", "2022-12-21"));
        cs.createReview(new ReviewDTO("1", "1", "Customer1" , "2", "10", "Satisfied with the product"));
    }
}
