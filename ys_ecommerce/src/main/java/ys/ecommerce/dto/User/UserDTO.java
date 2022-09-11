package ys.ecommerce.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.User.Customer;

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

    public UserDTO(Customer customer){
        this(Long.toString(customer.getId()), customer.getUsername(), customer.getPassword(), customer.getFirstName(), customer.getLastName(), customer.getAddress(), Double.toString(customer.getBalance()));
    }
}
