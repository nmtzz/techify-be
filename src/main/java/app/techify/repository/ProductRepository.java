package app.techify.repository;

import app.techify.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p " +
            "LEFT JOIN FETCH p.color " +
            "LEFT JOIN FETCH p.image " +
            "LEFT JOIN FETCH p.attribute " +
            "WHERE p.id = :id")
    Optional<Product> findByIdWithDetails(@Param("id") String id);

    @Query("SELECT p FROM Product p " +
            "LEFT JOIN FETCH p.color " +
            "LEFT JOIN FETCH p.image " +
            "LEFT JOIN FETCH p.attribute")
    List<Product> findAllWithDetails();

    @Query("SELECT p FROM Product p " +
           "LEFT JOIN FETCH p.color " +
           "LEFT JOIN FETCH p.image " +
           "LEFT JOIN FETCH p.attribute " +
           "WHERE p.category.id = :categoryId")
    Page<Product> findByCategoryIdWithDetails(
        @Param("categoryId") Integer categoryId, 
        Pageable pageable
    );

    @Query("SELECT DISTINCT p.brand FROM Product p WHERE p.category.id = :categoryId")
    List<String> findDistinctBrandsByCategory_Id(@Param("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category LEFT JOIN FETCH p.color LEFT JOIN FETCH p.image LEFT JOIN FETCH p.attribute WHERE p.category.id = :categoryId AND p.brand IN :brands")
    Page<Product> findByCategoryIdAndBrandsWithDetails(@Param("categoryId") Integer categoryId, @Param("brands") List<String> brands, Pageable pageable);
}
