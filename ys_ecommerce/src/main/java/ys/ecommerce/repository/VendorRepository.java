package ys.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ys.ecommerce.model.User.Vendor;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> getVendorByUsername(String username);

}
