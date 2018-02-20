
import java.util.Date;
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

	public PSTMessage(String name, int weight, Person sender, ArrayList<Person> receivers, int email_weight, Date send_date, ArrayList<PSTAttachment> attachments, PSTFolder parent_folder) {
		super(name, weight);
		
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
		for(PSTItem item : this.attachments) {
			res += item.weight;
		}
		
		this.weight = res;
	}
}
