package infotronic.sous.com.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class AdminController {
	@RequestMapping("/login")
	public String getLogin(Model model , String message) {
		return "login";
	}
	
	/*@RequestMapping("/logout")
	public String getLogOut(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getAuthorities());
		if(auth!=null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		return "redirect:"+Urls.Home+"?message=User has successfully logged out ! ";
		}
		return "redirect:"+Urls.Home;
	}*/
   

	@RequestMapping("/403")
	public String AccessDenied() {
		return "redirect:/error?message=403";
	}
}
