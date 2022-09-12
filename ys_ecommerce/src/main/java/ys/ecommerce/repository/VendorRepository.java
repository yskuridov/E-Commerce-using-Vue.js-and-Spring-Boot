package ys.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ys.ecommerce.model.User.Vendor;

import java.util.Optional;
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> getVendorById(Long id);
    Optional<Vendor> getVendorByUsername(String username);

}
