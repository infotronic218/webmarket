package infotronic.sous.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import infotronic.sous.com.entities.User;

public interface RepositoryUser extends JpaRepository<User, String>{
	//public User findByEmail(String email);

}
