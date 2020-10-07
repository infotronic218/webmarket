package infotronic.sous.com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infotronic.sous.com.dao.RepositoryCategory;
import infotronic.sous.com.services.CommandService;

@Controller
@RequestMapping(value=Urls.COMMAND_PRODUCT)
public class CommandController {
     @Autowired
	 private RepositoryCategory mCatR;
     @Autowired
     private CommandService mCommandService;
     
     @RequestMapping(value="")
     public String myCommands(Model model ) {
    	model.addAttribute("title","List Command");
 		model.addAttribute("urls",Urls.getAllUrls());
 		model.addAttribute("cUrls",Urls.CartLines());	
 		model.addAttribute("rUrls",Urls.GETResister());
 		model.addAttribute("categories",mCatR.findAll());
 		model.addAttribute("commands", mCommandService.getCommandsOfCostomer());
    	 return "category/command";
     }
	
	@RequestMapping(value="/save")
	public String launchCommand(Model model) {
		
		mCommandService.saveCommand();
		return "redirect:"+Urls.COMMAND_PRODUCT;
	}
	
}
