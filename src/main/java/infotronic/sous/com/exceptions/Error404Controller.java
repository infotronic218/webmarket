package infotronic.sous.com.exceptions;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import infotronic.sous.com.entities.Error;
import infotronic.sous.com.web.Urls;
@Controller
public class Error404Controller  implements ErrorController{
	
	@RequestMapping(value="/error")
	public String Error404(Model model, @RequestParam(required=false, defaultValue="") String message) {
	
		
		Error error = new Error();
		model.addAttribute("title","Error");
		if(!message.isEmpty()) {
			error.setMessage("You do not have permission to see this particular page !");
			error.setTitle("Permission Dennied ");
		}else  {
			error.setTitle("Page Not Find ");
			error.setMessage("The page you request is not available !");
		}
		
		model.addAttribute("error",error);
		model.addAttribute("Home",Urls.Home);
		return "errors";
	}

	@Override
	public String getErrorPath() {
		
		return "/error";
	}

}
