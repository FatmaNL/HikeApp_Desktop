package application;



import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import entity.User;
import services.CrudUser;

public class Main2 {
	
		
	
	public static void main(String[] args) {
	
		String plain_password="toto";
		String pw_hash = BCrypt.hashpw( plain_password, BCrypt.gensalt(13));
		System.out.println(pw_hash);
		System.out.println(BCrypt.gensalt());
		System.out.println(BCrypt.checkpw(plain_password, pw_hash));
		if( BCrypt.checkpw(plain_password, pw_hash) ) {
		    System.out.println("mot de passe OK");
		}else {
		    System.out.println("Mauvais mdp");
		}
		
}
	
}


