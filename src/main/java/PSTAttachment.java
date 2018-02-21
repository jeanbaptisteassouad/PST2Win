import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PSTAttachment extends PSTItem {
	
	PSTMessage parent_message;

	public PSTAttachment() {
		super();
		this.parent_message = new PSTMessage();
	}
	
	public PSTAttachment(int ID, String name, int weight, PSTMessage parent_message) {
		this.ID = ID;
		this.name = name;
		this.weight = weight;
		this.parent_message = parent_message;
		
	}
	
	public String write_CSVline() {
		String res = "";
		
		res += surr(this.ID);
		res += del;
		
		res += surr(this.parent_message.sender.emailAddress);
		res += del;
		
		res += surr(this.parent_message.sender.name);
		res += del;
		
		ArrayList<String> liste_destinataires = new ArrayList<String>();
		
		for(Person d : this.parent_message.receivers) {
			liste_destinataires.add(d.name + " <" + d.emailAddress + ">");
		}
		
		res += surr(String.join(", ", liste_destinataires));
		res += del;
		
		res += surr(this.name);
		res += del;
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		
		res += surr(df.format(this.parent_message.send_date));
		res += del;
		
		res += surr(this.weight);
		res += del;
		
		res += surr(this.parent_message.email_weight);
		res += del;
		
		res += surr(this.parent_message.ID);
		res += del;
		
		res += surr(this.parent_message.name);
		res += del;
		
		
		ArrayList<String> liste_PJs = new ArrayList<String>();
		
		for(PSTAttachment PJ : this.parent_message.attachments) {
			liste_PJs.add(PJ.name);
		}
		
		res += surr(String.join(", ", liste_PJs));
		
		return res;
	}

}
