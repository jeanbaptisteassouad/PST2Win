
public class Person {
	
	String emailAddress;
	String name;


	public Person() {
		this.emailAddress = "";
		this.name = "";

	}
	
	public Person(String email) {
		this.emailAddress = email;
		this.name = "";

	}
	
	public Person(String email, String name) {
		this.emailAddress = email;
		this.name = name;

	}
	
	public String get_email() {
		return this.emailAddress;
	}
	
	public String get_name() {
		return this.name;
	}

}
