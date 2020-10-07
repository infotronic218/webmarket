package infotronic.sous.com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import infotronic.sous.com.dao.RepositoryCategory;
import infotronic.sous.com.entities.Costomer;
import infotronic.sous.com.services.UserService;
import infotronic.sous.com.validator.CostomerValidator;


@Controller
@RequestMapping(value=Urls.USER_REGISTRATION)
public class RegisterController {
	@Autowired
	private UserService userService ;
	@Autowired
	private RepositoryCategory mCatR;
	


	@RequestMapping(value="/signUp")
	public String newUser(Model model) {
	    Costomer costomer = new Costomer();
		model.addAttribute("costomer",costomer);
		model.addAttribute("rUrls", Urls.GETResister());
		return "registration/register";
	}
	
	
	@RequestMapping(value="/memoriseUser")
	public String memoriseUser( Costomer costomer,BindingResult results,Model model) {
			new CostomerValidator().validate(costomer, results);
			if(results.hasErrors()) {
				model.addAttribute("rUrls", Urls.GETResister());
				model.addAttribute("costomer",costomer);
				return "registration/register";
			}else {
				 userService.saveCostomer(costomer);
				  
				return "redirect:/login";
			}
		
		
		
		
	}
	
	@RequestMapping(value="/update")
	public String updateUser( Costomer costomer,BindingResult results,Model model) {	
		costomer.setConfirm(costomer.getPassword());
		new CostomerValidator().validate(costomer, results);
			if(results.hasErrors()) {
				System.out.println(results.getAllErrors().toString());
				model.addAttribute("urls",Urls.getAllUrls());
				model.addAttribute("cUrls",Urls.CartLines());
				model.addAttribute("rUrls",Urls.GETResister());
				model.addAttribute("current",Urls.Home);
				model.addAttribute("costomer",costomer);
				model.addAttribute("title","Account Setting");
				model.addAttribute("categories",mCatR.findAll());
				return "registration/account";
			}else {
				 userService.updateCostomer(costomer);
				  
				return "redirect:"+Urls.Home;
			}
		
		
		
		
	}
	
	@RequestMapping(value="/{email}/setting")
	public String  SettingAccount (Model model, @PathVariable("email") String email) {
		model.addAttribute("urls",Urls.getAllUrls());
		model.addAttribute("cUrls",Urls.CartLines());
		model.addAttribute("rUrls",Urls.GETResister());
		model.addAttribute("current",Urls.Home);
		model.addAttribute("costomer",userService.getCostomer(email));
		model.addAttribute("title","Account Setting");
		model.addAttribute("categories",mCatR.findAll());
		return "registration/account";
	}
	
	

}
