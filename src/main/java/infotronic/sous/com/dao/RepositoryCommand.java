package infotronic.sous.com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import infotronic.sous.com.entities.Command;
import infotronic.sous.com.entities.Costomer;

public interface RepositoryCommand extends JpaRepository<Command,Long> {
	public List<Command> findByCostomer(Costomer costomer);

}
