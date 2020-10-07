package infotronic.sous.com.web;

import java.util.HashMap;
import java.util.Map;

public class Urls {
	public static final String Root ="/market";
	public static final String Home ="/market";
	public static final String Contact ="/market/contact";
	public static final String About   = "/market/about1";
	public static final String Products = "/market/show/all/products";
	public static final String ProductsByCategory ="/market/show/category/{id}/products";
	
	public static final String JSON_All_PRODUCT = "/json/data/all/products";
	public static final String JSON_All_PRODUCT_BY_ADMIN = "/json/data/admin/all/products";
	public static final String JSON_All_PRODUCT_BY_CATEGORIE = "/json/data/all/products/{id}/category";
	//SHOW PRODUCT URL
	public static final String SHOW_ONE_PRODUCT = "/market/show/{id}/product";
	public static final String SHOW_PRODUCT_ROOT = "/market/show";
	
	//PRODUCT MANAGEMENT 
	
	public static final String MANAGE_PRODUCT  = "/market/manage/products";
	//GET IMAGE URL 
	public static final String GET_PRODUCT_IMAGE  = "/market/products/images";
	
	public static final String USER_REGISTRATION  = "/market/resister/user";
	
	public static final String USER_CART_LINE = "/market/cart/user";
	
	public static final String COMMAND_PRODUCT = "/market/command/user";
	
	
	
	public static Map<String, String> getAllUrls(){
		Map<String, String> p = new HashMap<>();
		p.put("Home", Home);
		p.put("Contact", Contact);
		p.put("About", About);
		p.put("Products", Products);
		p.put("ManageProduct", MANAGE_PRODUCT);
		p.put("getImage", GET_PRODUCT_IMAGE);
		p.put("show",SHOW_PRODUCT_ROOT );
		return p ;
	}
	
	public static Map<String, String> getManageProductUrls(){
		Map<String, String> p = new HashMap<>();
		p.put("root", MANAGE_PRODUCT);
		p.put("new", MANAGE_PRODUCT+"/new");
		p.put("list", MANAGE_PRODUCT+"/list" );
		p.put("save",  MANAGE_PRODUCT+"/save" );
		p.put("delete",  MANAGE_PRODUCT+"/delete");
		p.put("edit", MANAGE_PRODUCT+"/edit");
		p.put("getImage", GET_PRODUCT_IMAGE);
		p.put("activation", MANAGE_PRODUCT+"/activation");
		p.put("addCategory", MANAGE_PRODUCT+"/add/category");
		return p ;
	}
	
	
	
	public static Map<String, String> GETResister(){
		Map<String, String> p = new HashMap<>();
		p.put("root", USER_REGISTRATION);
		p.put("new", USER_REGISTRATION+"/signUp");
		p.put("memoriseUser", USER_REGISTRATION+"/memoriseUser");
		p.put("update", USER_REGISTRATION+"/update");
		p.put("save", USER_REGISTRATION+"/save");
		p.put("account", USER_REGISTRATION+"/setting");
		return p ;
	}
	
	//CartLines Urls
	public static Map<String, String> CartLines(){
		Map<String, String> p = new HashMap<>();
		p.put("root", USER_CART_LINE+"");
		p.put("add", USER_CART_LINE+"/add");
		p.put("update", USER_CART_LINE+"/update");
		p.put("delete", USER_CART_LINE+"/delete");
		p.put("account", USER_REGISTRATION+"/setting");
		return p ;
	}
	//Command Url
	public static Map<String, String> getCommand(){
		Map<String, String> p = new HashMap<>();
		p.put("root",COMMAND_PRODUCT+"");
		p.put("save",COMMAND_PRODUCT+"/save");
		
		return p ;
	}
	
}
