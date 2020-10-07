package infotronic.sous.com.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infotronic.sous.com.dao.RepositoryProduct;
import infotronic.sous.com.entities.Cart;
import infotronic.sous.com.entities.CartLine;
import infotronic.sous.com.entities.Product;
import infotronic.sous.com.entities.UserModel;

@Service("cartService")
public class CartService {
	@Autowired
	private RepositoryProduct dbProduct;
	
	@Autowired
	private HttpSession session ;
	
	public UserModel getUserModelFromSession() {
		return ((UserModel) session.getAttribute("userModel"));
	}
	
	public void UpdateUserModel(UserModel model) {
		session.setAttribute("userModel", model );
	}
	
	private Cart getCart() {
		return ((UserModel) session.getAttribute("userModel")).getCart();
	}
	
	public List<CartLine> getCartLines(){
		Cart cart = this.getCart();
		return (List<CartLine>) cart.getCartLines();
	}
  
	
	/*Function to add product to the user cart*/
	public boolean addCartLineToCostomerCart(Long product_id) {
		CartLine  cartLine =getCart().getCartLineByProductId(product_id);
		Cart cart = getCart();
		if(cartLine==null) {
			Product product = dbProduct.findOne(product_id);
	        if(product!=null) {
	        	cartLine = new CartLine(product, 1);
	        	cart.addCartLine(cartLine);
	        	/*UserModel model = getUserModelFromSession();
	        	model.setCart(cart);
	        	UpdateUserModel(model);*/
	        	
	        }
		}else {
			cartLine.setProductCount(cartLine.getProductCount()+1);
			cart.updateCardline(product_id, cartLine);
		}
        
		return true;
	}
	/* Update the user product count */
	public String updateCartLine(Long product_id, int count) {
		Cart cart = getCart();
		String message ="";
		CartLine cartLine;
		if(product_id!=null && (cartLine = cart.getCartLineByProductId(product_id))!=null) {
		  
			if(count>cartLine.getProduct().getQuantity()) {
				cartLine.setProductCount(cartLine.getProduct().getQuantity());
			}else {
				cartLine.setProductCount(count);
			}
			boolean state =cart.updateCardline(product_id, cartLine);
			System.out.println(state);
			if(state)
			{ 
				System.out.println("Je suis ici mon frere");
				message ="Product count updated succefully !";
				/*UserModel model = getUserModelFromSession();
	        	model.setCart(cart);
	        	UpdateUserModel(model);*/
			}	
			   
		}else {
			message ="error";
		}
		
		
		return message;
	}

	public boolean deleteCartLine(Long product_id) {
		
		Cart cart = getCart();
    	if(cart.deleteCartline(product_id)) {
    		/*UserModel mUserModel = getUserModelFromSession();
        	mUserModel.setCart(cart);
        	UpdateUserModel(mUserModel);*/
    	}
 	   
		return true ;
	}
	
	
}
