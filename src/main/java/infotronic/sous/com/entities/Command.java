package infotronic.sous.com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="commands")
public class Command implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	@OneToOne
	private Costomer costomer;
	@OneToMany
	private Collection<CartLine>cartLines ;
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="hh:MM:ss")
	private Date time ;
	private double price;
	private boolean  active ;
	
	
	
	public Command() {
		this.cartLines = new ArrayList<>();
		
		this.active = true ;
	}
	
	public void addCartLine(CartLine line) {
		this.cartLines.add(line);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Costomer getCostomer() {
		return costomer;
	}
	public void setCostomer(Costomer costomer) {
		this.costomer = costomer;
	}
	public Collection<CartLine> getCartLines() {
		return cartLines;
	}
	public void setCartLines(Collection<CartLine> cartLines) {
		this.cartLines = cartLines;
	}
	public double getPrice() {
		double p = 0;
		for(CartLine line: this.cartLines)
		{
			p+=line.getTotal();
		}
		price = p ;
		return price;
	}
	public double calculatePrice() {
		double p = 0;
		for(CartLine line: this.cartLines)
		{
			p+=line.getTotal();
		}
		price = p ;
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	

	

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	

}
