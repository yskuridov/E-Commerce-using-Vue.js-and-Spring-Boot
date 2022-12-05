package ys.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ys.ecommerce.model.User.Vendor;

import java.util.Optional;
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    @Query("SELECT v FROM Vendor v LEFT JOIN FETCH v.inventory inv WHERE v.id = :id")
    Optional<Vendor> getVendorById(Long id);
    Optional<Vendor> getVendorByUsername(String username);

}
