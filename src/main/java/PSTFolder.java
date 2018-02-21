import java.util.ArrayList;

public class PSTFolder extends PSTItem {
	
	ArrayList<PSTItem> children;
	PSTFolder parent_folder;

	
	public PSTFolder() {
		super();
		this.children = new ArrayList<PSTItem>();
		this.parent_folder = null;
	}
	
	public PSTFolder(int ID, String name, ArrayList<PSTItem> children, PSTFolder parent_folder) {
		this.ID = ID;
		this.name = name;
		this.children = children;
		this.parent_folder = parent_folder;
		
		this.updateWeight();
		
	}
	
	
	
	public void updateWeight() {
		int res = 0;
		for(PSTItem item : this.children) {
			res += item.weight;
		}
		
		this.weight = res;
	}

}
