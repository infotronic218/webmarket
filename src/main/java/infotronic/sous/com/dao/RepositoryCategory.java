package infotronic.sous.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import infotronic.sous.com.entities.Category;

public interface RepositoryCategory extends JpaRepository<Category, Long>{
	public Category findByName(String name);
	

}
