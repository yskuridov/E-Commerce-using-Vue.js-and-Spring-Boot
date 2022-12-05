package ys.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ys.ecommerce.dto.User.UserDTO;
import ys.ecommerce.model.User.CommerceUser;
import ys.ecommerce.model.User.Customer;
import ys.ecommerce.model.User.Vendor;
import ys.ecommerce.repository.CustomerRepository;
import ys.ecommerce.repository.VendorRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Optional<UserDTO> createVendorAccount(UserDTO user){
        Vendor vendor = new Vendor(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getAddress());
        vendorRepository.save(vendor);
        return Optional.of(new UserDTO(vendor));
    }

    public Optional<UserDTO> createCustomerAccount(UserDTO user){
        Customer customer = new Customer(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getAddress());
        customerRepository.save(customer);
        return Optional.of(new UserDTO(customer));
    }

    public Optional<UserDTO> login(String username, String password) throws Exception{
        Optional<Vendor> vendor = vendorRepository.getVendorByUsername(username);
        Optional<Customer> customer = customerRepository.getCustomerByUsername(username);
        CommerceUser user;
        boolean isVendor = false;
        boolean hasOpenComments = false;
        if(vendor.isEmpty() && customer.isEmpty()){
            throw new Exception("The username provided is incorrect");
        }
        if(vendor.isPresent()){
            user = vendor.get();
            isVendor = true;
            hasOpenComments = vendor.get().isHasOpenComments();
        } else user = customer.get();
        if(!user.getPassword().equals(password)) throw new Exception("The password is incorrect");
        return Optional.of(new UserDTO(user, isVendor, hasOpenComments));
    }
}
