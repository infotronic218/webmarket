package infotronic.sous.com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import infotronic.sous.com.services.CartService;

@Controller
@RequestMapping(value=Urls.USER_CART_LINE)
public class CartLineController {
	
	@Autowired
	private CartService cartService ;
	
	@RequestMapping(value="")
	
	public String Home(Model model, String message) {
		if(message!=null ) {
			if(!message.isEmpty())
			model.addAttribute("message",message);
		}
		model.addAttribute("cartlines", cartService.getCartLines());
		model.addAttribute("title","Costomer cart");
		model.addAttribute("urls",Urls.getAllUrls());
		model.addAttribute("cUrls",Urls.CartLines());	
		model.addAttribute("rUrls",Urls.GETResister());
		model.addAttribute("commandUrls",Urls.getCommand());
		
		return "category/line";
	}
	
	@RequestMapping(value="/add/{product_id}/product")
	public String addCartLine(Model model,@PathVariable("product_id") Long id) {
        cartService.addCartLineToCostomerCart(id);
		
		return "redirect:"+Urls.USER_CART_LINE;
	}
	
	@RequestMapping(value="/update/{product_id}/{count}/product")
	public String UpdateCartLine(Model model,@PathVariable("product_id") Long product_id,
			@PathVariable("count")int count,  RedirectAttributes red) {
		
	     String message = cartService.updateCartLine(product_id, count);
	     System.out.println("Le message est :" +message+ " id : "+product_id);
		return "redirect:"+Urls.USER_CART_LINE+"?message="+message;
	}
	
	@RequestMapping(value="/delete/{product_id}/cartline")
	public String deleteCartLine(Model model,@PathVariable("product_id") Long product_id, RedirectAttributes red) {
		if(cartService.deleteCartLine(product_id))
		red.addAttribute("message", "CartLine deleted successfully");
		else
			red.addAttribute("message", "CartLine not find. Failed to  delete it !");
		return "redirect:"+Urls.USER_CART_LINE;
	}

}
