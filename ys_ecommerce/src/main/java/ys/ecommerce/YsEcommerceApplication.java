package ys.ecommerce;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ys.ecommerce.dto.Product.ProductDTO;
import ys.ecommerce.dto.User.UserDTO;
import ys.ecommerce.model.Product.Product;
import ys.ecommerce.model.User.Customer;
import ys.ecommerce.model.User.Vendor;
import ys.ecommerce.service.CustomerService;
import ys.ecommerce.service.UserService;
import ys.ecommerce.service.VendorService;

@SpringBootApplication
@RequiredArgsConstructor
public class YsEcommerceApplication {

    private final CustomerService cs;
    private final VendorService vs;
    private final UserService us;

    public static void main(String[] args) {
        SpringApplication.run(YsEcommerceApplication.class, args);
    }


    public void run(String... args) throws Exception{
        Customer c1 = new Customer("Customer1", "111", "Dwayne", "Johnson", "222 Washington St");
        us.createCustomerAccount(new UserDTO(c1));
        Vendor v1 = new Vendor("Vendor1", "111", "Tim", "Cook", "222 Hollywood St");
        us.createVendorAccount(new UserDTO(v1));
        Product p1 = new Product("iPhone 14", "https://www.apple.com/v/iphone-14/b/images/overview/selfies/selfie_startframe__ex2suisayck2_large.jpg", "iPhone 14 Space Black with a 64GB Storage", 500.00, 0, 50, v1);
        Product p2 = new Product("iPhone 14 Pro Max", "https://static.s-sfr.fr/media/catalogue/article/mobile/pw3iohmi/iPhone14Pro_VioletProfond_3-4-Side_Back_Left_400x540px.png", "iPhone 14 Pro Max Deep Purple with a 128GB Storage", 1000.00, 0, 50, v1);
        Product p3 = new Product("MacBook Air", "https://www.apple.com/v/macbook-air-m2/b/images/overview/design/design_top_starlight__dqra2r206k6e_large.jpg", "MacBook Air M2 - Gold", 1500, 0, 20, v1);
        vs.addProductToInventory(new ProductDTO(p1));
        vs.addProductToInventory(new ProductDTO(p2));
        vs.addProductToInventory(new ProductDTO(p3));
    }
}
