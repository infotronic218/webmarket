package infotronic.sous.com.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import infotronic.sous.com.web.HomeController;

public class FileUploadUtility {
	private static String ABS_PATH ="E:\\MesProjets\\WEB_JAVA_PYTHON\\WebMarket\\src\\main\\resources\\static\\images\\";
	//private static final String ABS_PATH ="/home/pi/marcus/apps/images/";
    
	
	
	public FileUploadUtility() {
		super();
		String path = FileUploadUtility.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		System.out.println("The path is : "+path);
		path = path.replace("/target/classes/", "/src/main/resources/static/images_2020/");
		System.out.println("The new  path is : "+path);
		ABS_PATH = path.substring(1, path.length());
		System.out.println("The ABS  path is : "+ABS_PATH);
	
	}
	public static void Upload(MultipartFile file, String code) {
		try {
			byte[] bytes = file.getBytes() ;
			Path path = Paths.get(ABS_PATH+code+".jpg");
			Files.write(path, bytes);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	public static byte[] getFile(String code) {
		Path path = Paths.get(ABS_PATH+code+".jpg");
		try {
			byte[]  bts = Files.readAllBytes(path);
		
			return Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new byte[0];
	}
	

}
