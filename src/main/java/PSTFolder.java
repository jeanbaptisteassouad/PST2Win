import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	
	public String write_CSVline() {
		String res = "";
		
		res += surr(this.ID);
		res += del;
		
		
		res += del;
		

		res += del;
		
		
		res += del;
		
		
		res += surr(this.name);
		res += del;
		
		
		res += del;
		
		
		res += surr(this.weight);
		res += del;
		

		res += del;
		
		res += surr(this.parent_folder.ID);
		res += del;
		
		res += surr(this.parent_folder.name);
		res += del;
		
		
		return res;
	}

}
