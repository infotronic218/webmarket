package infotronic.sous.com.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import infotronic.sous.com.entities.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors error) {
	 User user = (User) object;
	 if(!user.getConfirm().equals(user.getPassword()))
	 {
		error.rejectValue("password", null,"Verify your password ");
		return ;
	 }
		
	}

}
