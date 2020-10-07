package infotronic.sous.com.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue()
	private Long id ;
	private String name;
	private String description;
	private String image ;
	private boolean active ;
	
	
	
	
	public Category(Long id, String name, String description, String image, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.active = active;
	}
	public Category() {
		super();
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	

}
