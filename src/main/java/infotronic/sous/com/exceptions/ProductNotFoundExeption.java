package infotronic.sous.com.exceptions;

import java.io.Serializable;

public class ProductNotFoundExeption extends Exception implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String message ;

	public ProductNotFoundExeption(String message) {
		super();
		this.message =System.currentTimeMillis()+""+ message;
	}
	
	public ProductNotFoundExeption() {
		this("This product is not available");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
