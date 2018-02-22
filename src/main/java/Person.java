
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

}
