package infotronic.sous.com.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
@Entity
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Length(max=30)
	private String role;
	private String  description;
	
	
	public Role() {
		
	}
	public Role(String role, String description) {
		
		this.role = role;
		this.description = description;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRole() {
		return role;
	}
    
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	@Override
	public String toString() {
		return "Role [role=" + role + ", description=" + description + "]";
	}
	
	
}
