package infotronic.sous.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import infotronic.sous.com.entities.CartLine;

public interface RepositoryCartLine  extends JpaRepository<CartLine, Long>{
	
	

}
