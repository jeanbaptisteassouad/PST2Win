
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Message extends Item {
	
	Person sender;
	ArrayList<Person> receivers;
	
	int email_weight;
	
	Date send_date;
	

	public Message() {
		super();
		
		this.sender = new Person();
		this.receivers =  new ArrayList<Person>();
		this.email_weight = 0;
		this.send_date = new Date();
		this.children =  new ArrayList<Item>();
	}

	public Message(int ID, String name, Person sender, ArrayList<Person> receivers, int email_weight, Date send_date, ArrayList<Item> attachments) {
		this.ID = ID;
		this.name = name;
		this.sender = (sender != null ? sender : new Person());
		this.receivers =  (receivers != null ? receivers : new ArrayList<Person>());
		this.email_weight = email_weight;
		this.send_date = (send_date != null ? send_date : new Date());
		this.children =  attachments;
		
		this.updateWeight();
	}
	
	
	public void updateWeight() {
		int res = this.email_weight;
		
		if(this.children != null) {
			for(Item item : this.children) {
				res += item.weight;
			}
		}
		
		this.weight = res;
	}
	
	public String get_CSVline(Item parent) {
		String res = "";
		
		res += surr(this.ID);
		res += del;
		
		res += surr("E-mail");
		res += del;
		
		res += surr(this.sender != null ? this.sender.emailAddress : "");
		res += del;
		
		res += surr(this.sender != null ? this.sender.name : "");
		res += del;
		
		if(receivers != null) {
			ArrayList<String> liste_destinataires = new ArrayList<String>();
			
			for(Person d : this.receivers) {
				liste_destinataires.add(d.name + " <" + d.emailAddress + ">");
			}
			
			res += surr(String.join(", ", liste_destinataires));
		}
		res += del;
		
		res += surr(this.name != null ? this.name : "");
		res += del;
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		
		res += surr(this.send_date != null ? df.format(this.send_date) : "");
		res += del;
		
		res += surr(this.weight);
		res += del;
		
		res += surr(this.email_weight);
		res += del;
		
		res += surr(parent != null ? parent.ID : 0);
		res += del;
		
		res += surr(parent != null ? parent.name : "");
		res += del;
		
		if(this.children != null) {
			ArrayList<String> liste_PJs = new ArrayList<String>();
			
			for(Item PJ : this.children) {
				liste_PJs.add(PJ.name);
			}
			
			res += surr(String.join(", ", liste_PJs));
		}
		
		return res;
	}
}
