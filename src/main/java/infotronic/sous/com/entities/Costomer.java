package infotronic.sous.com.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="costomers")
public class Costomer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private String email ;
	private String firstname;
	private String lastname ;
	private String password;
	@Transient
	private String confirm ;
	private String telephone ;
	private String address ;
	private String city ;
	@OneToOne
	private Role role ;
	
	public Costomer() {
		this.telephone ="";
		this.city="";
		this.address="";
		
	}
	
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String ville) {
		this.city = ville;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}

}
