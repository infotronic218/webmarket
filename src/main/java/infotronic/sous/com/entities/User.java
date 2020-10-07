package infotronic.sous.com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="users")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    private String email ;
	private String firstname;
	private String lastname;
	private String password ;
	
	@ManyToMany
	@JoinTable(name="USERS_ROLES",
	joinColumns=@JoinColumn(referencedColumnName="email",name="user_email",columnDefinition="VARCHAR(100)"),
	inverseJoinColumns=@JoinColumn(referencedColumnName="role",name="roles_role",columnDefinition="VARCHAR(70)"))

	private Collection<Role>roles ;
	
	public Collection<Role> getRoles() {
		return roles;
	}
	
	public User() {
		super();
		this.roles= new ArrayList<>();
		
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	@Transient
	private String confirm;
	
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	public String getFirstname() {
		return firstname;
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
	public void addRole(Role role) {
		if(role!=null)
		this.roles.add(role);
	}
 
	public String toStringRole() {
		String r = "";
		for(Role p: roles) {
			r+="  "+p.getRole();
		}
		return r;
	}
	
	

	@Override
	public String toString() {
		return "User [ firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", roles=" + roles + ", confirm=" + confirm + "]";
	}

}
