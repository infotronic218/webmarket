package infotronic.sous.com.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import infotronic.sous.com.entities.Costomer;

public class CostomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Costomer.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors error) {
	 Costomer costomer = (Costomer) object;
	 if(!(costomer.getConfirm().equals(costomer.getPassword())))
	 {
		error.rejectValue("password", null,"Verify your password ");
		return ;
	 }
		
	}

}
