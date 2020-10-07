package infotronic.sous.com.exceptions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import infotronic.sous.com.entities.Error;
import infotronic.sous.com.web.Urls;

@ControllerAdvice
@Controller
public class ExeptionHandler {
	
	/*@ExceptionHandler(Exception.class)
	public String ErrorHandler (Model model, Exception e) {
		Error error = new Error();
		model.addAttribute("title","Error");
		error.setTitle("Errors ");
		
		//StringWriter stringWriter = new StringWriter();
		//e.printStackTrace(new PrintWriter((stringWriter)));
		error.setMessage("Request Failled ");
		model.addAttribute("error",error);
		model.addAttribute("Home",Urls.Home);
		return "errors";
				
	}*/
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String ErrorHandlerNotFoundExeption (Model model) {
		Error error = new Error();
		model.addAttribute("title","Error");
		error.setTitle("Page Not Find ");
		error.setMessage("The page you request is not available ");
		model.addAttribute("error",error);
		model.addAttribute("Home",Urls.Home);
		return "errors";
				
	}
	
	@ExceptionHandler(ProductNotFoundExeption.class)
	public String ErrorHandlerProducts (Model model) {
		model.addAttribute("title","Error");
		Error error = new Error();
		error.setTitle("Sorry Product Not Find  ");
		error.setMessage("The product  you  are loking for is not available Right Now request is not available ! ");
		model.addAttribute("error",error);
		model.addAttribute("Home",Urls.Home);
		return "errors";
				
	}
	

	
	

}
