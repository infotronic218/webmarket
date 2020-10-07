package infotronic.sous.com.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import infotronic.sous.com.entities.Costomer;

public interface RepositoryCostomer extends JpaRepository<Costomer, String> {

}
