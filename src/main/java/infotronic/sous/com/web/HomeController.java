package infotronic.sous.com.web;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import infotronic.sous.com.dao.RepositoryCategory;
import infotronic.sous.com.dao.RepositoryProduct;
import infotronic.sous.com.entities.Category;
import infotronic.sous.com.entities.Product;
import infotronic.sous.com.exceptions.ProductNotFoundExeption;
import infotronic.sous.com.utils.FileUploadUtility;

@Controller
public class HomeController {
	@Autowired
	private RepositoryCategory mCatR;
	@Autowired
	private RepositoryProduct mProdR ;
	@RequestMapping(Urls.Home)
	public String Home(Model model,@RequestParam(name="page", defaultValue="0") int page ,@RequestParam(name="message",required=false) String message) {
		model.addAttribute("urls",Urls.getAllUrls());
		model.addAttribute("cUrls",Urls.CartLines());
		model.addAttribute("rUrls",Urls.GETResister());
		model.addAttribute("commandUrls",Urls.getCommand());
		model.addAttribute("current",Urls.Home);
		model.addAttribute("title","Home");
		model.addAttribute("categories",mCatR.findAll());
		Page<Product> products =mProdR.findAll(new PageRequest(page, 10));
		model.addAttribute("products",products );
		int pages[] = new int[products.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("pageCurrent", page);
		if(message!=null) {
			model.addAttribute("message", message);
		}
		return "index";
	}
	//ALL PRODUCT PAGE 
	@RequestMapping(value=Urls.Products)
	public String Products(Model model) {
		model.addAttribute("urls",Urls.getAllUrls());
		model.addAttribute("cUrls",Urls.CartLines());
		model.addAttribute("rUrls",Urls.GETResister());
		model.addAttribute("commandUrls",Urls.getCommand());
		model.addAttribute("title","Produts");
		model.addAttribute("current",Urls.Products);
		model.addAttribute("categories",mCatR.findAll());
		model.addAttribute("productUrl",Urls.JSON_All_PRODUCT);
		return "category/products-by-category";
		
	}
	@RequestMapping(value=Urls.ProductsByCategory)
	public String ProductsByCategory(Model model , @PathVariable("id")Long id ) {
	    Category category = mCatR.findOne(id);
	    model.addAttribute("urls",Urls.getAllUrls());
	    model.addAttribute("cUrls",Urls.CartLines());
	    model.addAttribute("rUrls",Urls.GETResister());
	    model.addAttribute("commandUrls",Urls.getCommand());
	    model.addAttribute("title",category.getName());
	    model.addAttribute("categories",mCatR.findAll());
	    model.addAttribute("category",category);
	    model.addAttribute("current",category.getId());
	    model.addAttribute("productUrl",Urls.JSON_All_PRODUCT+"/"+String.valueOf(id)+"/category");
		return "category/products-by-category";
	}
	
	// SINGLE PAGE ELEMENT // SHOW PRODUCT IN SINGLE PAGE
	@RequestMapping(value=Urls.SHOW_ONE_PRODUCT)
	public String SinglePage(Model model, @PathVariable("id") Long id) throws ProductNotFoundExeption {
		model.addAttribute("urls",Urls.getAllUrls());
		model.addAttribute("cUrls",Urls.CartLines());
		model.addAttribute("rUrls",Urls.GETResister());
		model.addAttribute("commandUrls",Urls.getCommand());
		Product p = mProdR.findOne(id);
		if(p==null)throw new ProductNotFoundExeption();
		model.addAttribute("title","Produts");
		model.addAttribute("category",p.getCategory());
		model.addAttribute("product",p);
		model.addAttribute("current",Urls.Products);
		return "category/product";
	}
	
	@RequestMapping(value=Urls.Contact)
	public String Contact(Model model) {
		model.addAttribute("urls",Urls.getAllUrls());
		model.addAttribute("cUrls",Urls.CartLines());
		model.addAttribute("rUrls",Urls.GETResister());
		model.addAttribute("Contact","Produts");
		model.addAttribute("title","Contact ");
		model.addAttribute("commandUrls",Urls.getCommand());
		model.addAttribute("current",Urls.Contact);
		
		return "contact";
	}
	
	@RequestMapping(value=Urls.About)
	public String AboutPage(Model model) {
		model.addAttribute("urls",Urls.getAllUrls());
		model.addAttribute("current",Urls.About);
		model.addAttribute("rUrls",Urls.GETResister());
		model.addAttribute("cUrls",Urls.CartLines());
		model.addAttribute("title","About");
		model.addAttribute("commandUrls",Urls.getCommand());
		
				
		return "about";
	}
	
	
	// GET IMAGE Url
	@RequestMapping(value=Urls.GET_PRODUCT_IMAGE, produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[]getImage(Model model, String code){
		return FileUploadUtility.getFile(code);
	}
	

}
