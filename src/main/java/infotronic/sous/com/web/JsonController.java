package infotronic.sous.com.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import infotronic.sous.com.dao.RepositoryCategory;
import infotronic.sous.com.dao.RepositoryProduct;
import infotronic.sous.com.entities.Category;
import infotronic.sous.com.entities.Product;

@Controller
public class JsonController {
	@Autowired
	private RepositoryProduct mProdR ;
	@Autowired
	private RepositoryCategory mCatR ;
	@RequestMapping(value=Urls.JSON_All_PRODUCT)
	@ResponseBody
	public List<Product> getAllProduct(){
		
		return mProdR.findActive(true);
	}
	
	@RequestMapping(value=Urls.JSON_All_PRODUCT_BY_ADMIN)
	@ResponseBody
	public List<Product> getAllProductByAdmin(){
		
		return mProdR.findAll();
	}
	
	
	@RequestMapping(value=Urls.JSON_All_PRODUCT_BY_CATEGORIE)
	@ResponseBody
	public List<Product> getAllProductByCategorie(@PathVariable("id")Long id){
		Category category = mCatR.findOne(id);
		return mProdR.findByCategory(category);
	}
	
	

}
