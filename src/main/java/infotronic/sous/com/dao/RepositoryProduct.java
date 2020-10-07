package infotronic.sous.com.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import infotronic.sous.com.entities.Category;
import infotronic.sous.com.entities.Product;

public interface RepositoryProduct extends JpaRepository<Product, Long> {
	
	public List<Product> findByCategory(Category category);
	public List<Product> findByActive(boolean active);
	@Query("SELECT p FROM Product p WHERE p.active= :active")
	public List<Product> findActive(@Param("active")boolean active);
	public Page<Product> findAll(Pageable pageable);
	
	
	

}
