package infotronic.sous.com.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import infotronic.sous.com.dao.RepositoryCostomer;
import infotronic.sous.com.entities.Cart;
import infotronic.sous.com.entities.Costomer;
import infotronic.sous.com.entities.UserModel;

@ControllerAdvice

public class GlobalController {
	
	@Autowired
	private RepositoryCostomer dbCostomer;
	
	
	@Autowired
	private HttpSession session;
	
	
	private UserModel userModel =null;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {
	
		if(session.getAttribute("userModel")==null) {
			//Add user to session
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth!=null) {
				
			
			Costomer costomer = dbCostomer.findOne(auth.getName());
			
			/*If the user is a costomer */
			if(costomer!=null) {
				
				userModel = new UserModel();
				userModel.setId(System.currentTimeMillis());
				userModel.setEmail(costomer.getEmail());
				userModel.setName(costomer.getLastname()+" "+costomer.getFirstname());
				userModel.setCart(new Cart());
				}
	
			}
			session.setAttribute("userModel", userModel);
		}
		
		return (UserModel) session.getAttribute("userModel");
		 
	}

}
