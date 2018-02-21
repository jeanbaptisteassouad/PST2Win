
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class PSTMessage extends PSTItem {
	
	Person sender;
	ArrayList<Person> receivers;
	
	int email_weight;
	
	Date send_date;
	
	ArrayList<PSTAttachment> attachments;
	PSTFolder parent_folder;
	

	public PSTMessage() {
		super();
		
		this.sender = new Person();
		this.receivers =  new ArrayList<Person>();
		this.email_weight = 0;
		this.send_date = new Date();
		this.attachments =  new ArrayList<PSTAttachment>();
		this.parent_folder = new PSTFolder();
	}

	public PSTMessage(int ID, String name, Person sender, ArrayList<Person> receivers, int email_weight, Date send_date, ArrayList<PSTAttachment> attachments, PSTFolder parent_folder) {
		this.ID = ID;
		this.name = name;
		this.sender = sender;
		this.receivers =  receivers;
		this.email_weight = email_weight;
		this.send_date = send_date;
		this.attachments =  attachments;
		this.parent_folder = parent_folder;
		
		this.updateWeight();
	}
	
	
	public void updateWeight() {
		int res = this.email_weight;
		
		if(this.attachments != null) {
			for(PSTItem item : this.attachments) {
				res += item.weight;
			}
		}
		
		this.weight = res;
	}
	
	public String write_CSVline() {
		String res = "";
		
		res += surr(this.ID);
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
		
		res += surr(this.parent_folder != null ? this.parent_folder.ID : 0);
		res += del;
		
		res += surr(this.parent_folder != null ? this.parent_folder.name : "");
		res += del;
		
		if(this.attachments != null) {
			ArrayList<String> liste_PJs = new ArrayList<String>();
			
			for(PSTAttachment PJ : this.attachments) {
				liste_PJs.add(PJ.name);
			}
			
			res += surr(String.join(", ", liste_PJs));
		}
		
		return res;
	}
}
