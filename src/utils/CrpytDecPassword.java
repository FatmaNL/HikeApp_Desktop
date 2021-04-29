package utils;

import java.util.Base64;

import org.springframework.security.crypto.bcrypt.BCrypt;


public class CrpytDecPassword {
	
	public static String getEncoString(String password) {
		return BCrypt.hashpw( password, BCrypt.gensalt(13));
		
		//return Base64.getEncoder().encodeToString(password.getBytes());
	}
	
	
}
