package infotronic.sous.com.services;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import infotronic.sous.com.dao.RepositoryCostomer;
import infotronic.sous.com.dao.RepositoryRole;
import infotronic.sous.com.dao.RepositoryUser;
import infotronic.sous.com.entities.Costomer;
import infotronic.sous.com.entities.Role;
import infotronic.sous.com.entities.User;
import infotronic.sous.com.entities.UserModel;



@Service
@Transactional
public class UserService implements  UserServiceIter{
	@Autowired
	private RepositoryUser mUserR;
	@Autowired
	private RepositoryRole mRoleR;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RepositoryCostomer dbCostomer;
	@Autowired
	private HttpSession session;
	@Override
	@Transactional
	public boolean SaveUser(User user) {
		boolean state = false ;
		user.setPassword(encoder.encode(user.getPassword()));
		Role role = mRoleR.findOne("USER");
		if(role==null) {
			role = new Role("USER", "Role for costomers");
			mRoleR.save(role);
		}
		user.addRole(role);
		mUserR.save(user);
		if(user.getEmail()!=null)
			state = true ;
		return state;
	}

	@Transactional
	public boolean saveCostomer(Costomer costomer) {
		costomer.setPassword(encoder.encode(costomer.getPassword()));
		Role role = mRoleR.findOne("COSTOMER");
		if(role==null) {
			role = new Role("COSTOMER", "Role for costomers");
			mRoleR.save(role);
		}
		costomer.setRole(role);
		dbCostomer.save(costomer);
		
		return true;
	}
	
	private void updateSessionUser(Costomer costomer) {
		UserModel model = (UserModel) session.getAttribute("userModel");
		if(model!=null) {
			model.setName(costomer.getFirstname() + " "+costomer.getLastname());
			session.setAttribute("userModel", model);
		}
	}
	
	@Transactional
	public boolean updateCostomer(Costomer costomer) {
		Role role = mRoleR.findOne("COSTOMER");
		if(role==null) {
			role = new Role("COSTOMER", "Role for costomers");
			mRoleR.save(role);
		}
		costomer.setRole(role);
		dbCostomer.save(costomer);
		updateSessionUser(costomer);
		return true;
	}
	
	public Costomer getCostomer(String email) {
		if(email!=null)
		return dbCostomer.findOne(email);
		else
			return null;
	}

	
	
}
