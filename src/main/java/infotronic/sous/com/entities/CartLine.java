package infotronic.sous.com.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CartLine  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id ;
	@OneToOne
	private Product product ;
	
	private int productCount ;
	
	public CartLine(Long id,Product product,  int productCount) {
		super();
		this.id = id ;
		this.product = product;
		
		this.productCount = productCount;
	}
	
	public CartLine(Product product,int productCount) {
		super();
		
		this.product = product;
		this.productCount = productCount;
	}
	public CartLine() {
		super();
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
	public double getTotal() {
		double p = 0 ;
	    if(product!=null && this.productCount !=0) {
	    	p = product.getPrice() * this.productCount  ;
	    }
	    return p ;
	}

	

	
}
