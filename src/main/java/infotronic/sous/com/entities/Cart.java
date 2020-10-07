package infotronic.sous.com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id ;
	private User user;
	private double grandTotal;
    private List<CartLine> cartLines;
	public Cart() {
		super();
		this.cartLines = new ArrayList<>();
	}
	
	
	public List<CartLine> getCartLines() {
		return cartLines;
	}


	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}

	public void addCartLine(CartLine cartLine) {
		this.cartLines.add(cartLine);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getGrandTotal() {
		 grandTotal = 0 ;
		for(CartLine line : this.cartLines)
		{
			grandTotal+=line.getTotal();
		}
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	public int totalCount() {
		int t = 0 ;
		for(CartLine line : this.cartLines)
		{
			t+=line.getProductCount();
		}
		return t ;
	}
	/* To find a specific cartLine using  the product id*/
	public CartLine getCartLineByProductId(Long product_id) {
		for(CartLine cline : this.cartLines) {
			if(cline.getProduct().getId().equals(product_id))
				return cline ;
		}
		return null ;
	}
	/* To get the index of a cartLine in the list*/
	public int getIndexOfCartLine(Long product_id) {
		
		int index = -1,  i=0;
		for(CartLine cline : this.cartLines) {
			
			if(cline.getProduct().getId().equals(product_id))
			{
				index= i;
			}
				
			i++;
		}
		return index;
	}
	
	/* To update the product count on cartLine */
	public boolean updateCardline(Long product_id ,CartLine line) {
		 int index = getIndexOfCartLine(product_id);
		 if(index>=0) {
			 this.cartLines.set(index, line);
			 return true ;
		 }
		return false ;
	}
	/* To delete a cart line knowing the product ID*/
	public boolean deleteCartline(Long product_id) {
		return this.cartLines.remove(this.getCartLineByProductId(product_id));
	}
	
	
	
	

}
