package entity;

public class User {

	private int cin;
	private String nom;
	private String prenom;
	private int age;
	private String sexe;
	private String adresse;
	private int tel;
	private String email;
	private String password;
	private String roles="ROLE_USER";
	private String status="DESACTIVE";
	
	public User() {
		
	}
	
	public User(int cin) {
		super();
		this.cin = cin;
	}

	public User(int cin, String nom, String prenom, int age, String sexe, String adresse, int tel, String email,String password) {
		
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.sexe = sexe;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
		this.password = password;
	}
	
	public User(int cin, String nom, String prenom, int age, String sexe, String adresse, int tel, String email,String password, String roles, String status) {
		
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.sexe = sexe;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.status = status;
	}
	
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
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
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", sexe=" + sexe
				+ ", adresse=" + adresse + ", tel=" + tel + ", email=" + email + ", password=" + password + ", roles="
				+ roles + ", status=" + status + "]";
	}
	
	
	
}
