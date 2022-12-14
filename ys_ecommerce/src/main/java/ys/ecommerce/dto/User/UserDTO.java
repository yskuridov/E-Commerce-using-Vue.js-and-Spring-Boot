package ys.ecommerce.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.User.CommerceUser;
import ys.ecommerce.model.User.Customer;
import ys.ecommerce.model.User.Vendor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String balance;
    private boolean isVendor;
    private boolean hasOpenComments;

    public UserDTO(Customer customer){
        this(Long.toString(customer.getId()), customer.getUsername(), customer.getPassword(), customer.getFirstName(), customer.getLastName(), customer.getAddress(), Double.toString(customer.getBalance()), false, false);
    }

    public UserDTO(Vendor vendor){
        this(Long.toString(vendor.getId()), vendor.getUsername(), vendor.getPassword(), vendor.getFirstName(), vendor.getLastName(), vendor.getAddress(), Double.toString(vendor.getBalance()), true, vendor.isHasOpenComments());
    }

    public UserDTO(CommerceUser user, boolean isVendor, boolean hasOpenComments){
        this(Long.toString(user.getId()), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getAddress(), Double.toString(user.getBalance()), isVendor, hasOpenComments);
    }
}
