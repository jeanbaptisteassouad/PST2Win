
public class PSTAttachment extends PSTItem {
	
	PSTMessage parent_message;

	public PSTAttachment() {
		super();
		this.parent_message = new PSTMessage();
	}

	public PSTAttachment(String name) {
		super(name);
		this.parent_message = new PSTMessage();
		
	}

	public PSTAttachment(String name, int weight) {
		super(name, weight);
		this.parent_message = new PSTMessage();
		
	}
	
	public PSTAttachment(String name, int weight, PSTMessage parent_message) {
		super(name, weight);
		this.parent_message = parent_message;
		
	}

}
