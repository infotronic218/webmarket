package infotronic.sous.com.services;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infotronic.sous.com.dao.CostomerRepository;
import infotronic.sous.com.dao.RepositoryCartLine;
import infotronic.sous.com.dao.RepositoryCommand;
import infotronic.sous.com.dao.RepositoryProduct;
import infotronic.sous.com.entities.Cart;
import infotronic.sous.com.entities.CartLine;
import infotronic.sous.com.entities.Command;
import infotronic.sous.com.entities.Costomer;
import infotronic.sous.com.entities.Product;
import infotronic.sous.com.entities.UserModel;


@Service
public class CommandService {
	@Autowired
	private HttpSession session ;
	@Autowired
	private RepositoryCommand mCommandR;
	@Autowired
	private CostomerRepository mCostomerR;
	@Autowired
	private RepositoryCartLine mCartLineR ;
	@Autowired
	private RepositoryProduct mProductR ;
	
	public boolean isValide() {
		Costomer costomer = this.getCostomer();
		if(costomer!=null)
		{
			if(!costomer.getAddress().isEmpty() && 
			   !costomer.getTelephone().isEmpty() && 
			   !costomer.getCity().isEmpty())
			{
			 return true ;	
			}
		}
		return false ;
	}
	
	public Costomer getCostomer() {
		UserModel userModel = (UserModel) session.getAttribute("userModel");
		return mCostomerR.getOne(userModel.getEmail());
	}
	
	@Transactional
	public boolean saveCommand() {
		UserModel userModel = (UserModel) session.getAttribute("userModel");
		Costomer costomer = mCostomerR.getOne(userModel.getEmail());
		Command command = new Command();
		command.setCostomer(costomer);
		Date date = new Date();
		command.setDate(date);
		command.setTime(date);
		
		
		Cart cart = userModel.getCart();
		if(!cart.getCartLines().isEmpty())
		{
			for(CartLine line : cart.getCartLines()) {
				Product p = mProductR.findOne(line.getProduct().getId());
				p.setQuantity(p.getQuantity()-line.getProductCount());
				mProductR.save(p);
				mCartLineR.save(line);
				command.addCartLine(line);
			}
			command.calculatePrice();
			mCommandR.save(command);
		    userModel.setCart(new Cart());
			return true;
		}
		return false;
		
	}
	
	public List<Command> getCommandsOfCostomer() {
		return mCommandR.findByCostomer(getCostomer());
	}


}
