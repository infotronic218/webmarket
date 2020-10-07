package infotronic.sous.com.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import infotronic.sous.com.dao.RepositoryCategory;
import infotronic.sous.com.dao.RepositoryProduct;
import infotronic.sous.com.entities.Category;
import infotronic.sous.com.entities.Product;
import infotronic.sous.com.utils.FileUploadUtility;
import infotronic.sous.com.validator.ProductValidator;



@Controller
@RequestMapping(value=Urls.MANAGE_PRODUCT)
public class ManageProductController {
	@Autowired
	private RepositoryCategory mCatR;
	@Autowired
	private RepositoryProduct mProdR ;
	@RequestMapping(value="")
	public String ProductManage(Model model,@RequestParam(name="message",required=false) String message) {
		model.addAttribute("urls",Urls.getAllUrls());
	    Product p = new Product();
	    model.addAttribute("product", p);
	    model.addAttribute("products",mProdR.findAll());
	    if(message!=null) {
	    	model.addAttribute("message",message);
	    }
	    model.addAttribute("categories",mCatR.findAll());
	    model.addAttribute("category", new Category());
	    model.addAttribute("action",Urls.getManageProductUrls().get("list"));
		model.addAttribute("current",Urls.MANAGE_PRODUCT);
		model.addAttribute("pUrls",Urls.getManageProductUrls());
		
		model.addAttribute("title","Management");
		return "category/manage-products";
	}
	
	
	@RequestMapping(value="/new")
	public String newProduct(Model model) {
		model.addAttribute("urls",Urls.getAllUrls());
	    Product p = new Product();
	    model.addAttribute("product", p);
	    model.addAttribute("category", new Category());
	    model.addAttribute("categories",mCatR.findAll());
	    model.addAttribute("action",Urls.getManageProductUrls().get("new"));
		model.addAttribute("current",Urls.MANAGE_PRODUCT);
		model.addAttribute("pUrls",Urls.getManageProductUrls());
		model.addAttribute("title","Management");
		
		return "category/manage-products";
	}
	
	@RequestMapping(value="/add/category",method=RequestMethod.POST)
	public String newCategory(Model model,Category category) {
		mCatR.save(category);
		String message ="Category added succefully";
		return "redirect:"+Urls.getManageProductUrls().get("root")+"?message="+message;
	}
	
	
	
	@RequestMapping(value="/edit")
	public String editProduct(Model model, Long id) {
		model.addAttribute("urls",Urls.getAllUrls());
		model.addAttribute("category", new Category());
	    Product p = mProdR.findOne(id);
	    model.addAttribute("product", p);
	    model.addAttribute("categories",mCatR.findAll());
	    model.addAttribute("action",Urls.getManageProductUrls().get("new"));
		model.addAttribute("current",Urls.MANAGE_PRODUCT);
		model.addAttribute("pUrls",Urls.getManageProductUrls());
		model.addAttribute("title","Management");
		
		return "category/manage-products";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveProduct( @Valid Product product, BindingResult result
			,String categoryId,Model model , HttpServletRequest request) {
		if(product.getId()==null) {
			new ProductValidator().validate(product, result);
		}
		if(!product.getFile().isEmpty()) {
			new ProductValidator().validate(product, result);
		}
		
		if(result.hasErrors()) {
			    model.addAttribute("product", product);
			    model.addAttribute("urls",Urls.getAllUrls());
			    model.addAttribute("categories",mCatR.findAll());
			    model.addAttribute("action",Urls.getManageProductUrls().get("new"));
				model.addAttribute("current",Urls.MANAGE_PRODUCT);
				model.addAttribute("pUrls",Urls.getManageProductUrls());
				model.addAttribute("message","Failed to save the product !");
				model.addAttribute("title","Management Product");
				return "category/manage-products";
		}
		
		product.setCategory(mCatR.findByName(categoryId));
		mProdR.save(product);
		if(!product.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.Upload(product.getFile(), product.getCode());
			
		}
		
	    String message ="Product Save succefully";
		return "redirect:"+Urls.getManageProductUrls().get("root")+"?message="+message;
	}
	
	@RequestMapping(value="/{id}/activation",method=RequestMethod.POST)
	@ResponseBody
	public String activationProduct(@PathVariable("id")Long id) {
		Product p = mProdR.findOne(id);
		String message = p.isActive()?"Product with id "
		+id+ " has been desactivate succefully !":"Product wirh id "+id+" has been activate succefully !";;
		p.setActive(!p.isActive());
		mProdR.save(p);
		return message;
	}
	
	

}
