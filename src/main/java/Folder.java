import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Folder extends Item {
	
	

	
	public Folder() {
		super();
		this.children = new ArrayList<Item>();
	}
	
	public Folder(int ID, String name, ArrayList<Item> children) {
		this.ID = ID;
		this.name = name;
		this.children = children;
		
		this.updateWeight();
		
	}
	
	
	
	public void updateWeight() {
		int res = 0;
		
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
		
		res += surr("Répertoire");
		res += del;
		
		res += surr(this.name);
		
		res += del;	
		res += del;
		res += del;
		res += del;
		res += del;
		
		res += surr(this.weight);
		
		res += del;
		res += del;
		
		res += surr(parent != null ? parent.ID : -1);
		
		res += del;

		res += surr(parent != null ? parent.name : "[racine]");
		
		res += del;
		res += del;
		
		return res;
	}

}
