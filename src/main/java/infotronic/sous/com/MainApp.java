package infotronic.sous.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import infotronic.sous.com.utils.FileUploadUtility;

@SpringBootApplication
@ComponentScan("infotronic.sous.com")
public class MainApp {

	public static void main(String[] args) {
	
		SpringApplication.run(MainApp.class, args);
		new FileUploadUtility();
	}
}
