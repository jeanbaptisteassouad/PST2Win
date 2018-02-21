
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

}
