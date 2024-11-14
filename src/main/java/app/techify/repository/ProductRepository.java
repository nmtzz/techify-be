package app.techify.repository;

import app.techify.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p " +
            "LEFT JOIN FETCH p.color " +
            "LEFT JOIN FETCH p.image " +
            "LEFT JOIN FETCH p.attribute")
    List<Product> findAllWithDetails();
}
