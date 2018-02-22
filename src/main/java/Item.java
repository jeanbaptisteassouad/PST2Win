import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public abstract class Item {

	int ID;
	String name;
	int weight;
	ArrayList<Item> children;
	
	static String del = ",";
	
		public Item() {
			this.ID = 0;
			this.name = "";
			this.weight = 0;
			this.children = new ArrayList<Item>();
		}
		
	public abstract String get_CSVline(Item parent);
	
	public static String get_CSVhead() {
		String res ="";
		
		res += surr("ID");
		res += del;
		
		res += surr("Type");
		res += del;
		
		res += surr("exp_adresse");
		res += del;
		
		res += surr("exp_nom");
		res += del;
		
		res += surr("liste_destinataires");
		res += del;
		
		res += surr("titre");
		res += del;
		
		res += surr("date_envoi");
		res += del;
		
		res += surr("poids");
		res += del;
		
		res += surr("poids_email_seul");
		res += del;
		
		res += surr("parent_ID");
		res += del;
		
		res += surr("parent_titre");
		res += del;
		
		res += surr("liste_PJs");
		
		return res;
	}
	
	public String get_CSV(Item parent) {
		
		String res = "";
		
		res += this.get_CSVline(parent);
		res += "\n";
		
		if(this.children != null) {
			for(Item child : this.children) {
				res += child.get_CSV(this);
			}
		}
		
		return res;
	}
	
	/*public boolean write_CSV(Path filepath) {
		File file = new File(filepath);
		
		file.append(get_CSVhead() + "\n");
		file.append(this.get_CSVline(null));
		
		file.close();
	}*/
	
	public static String surr(String s) {
		return "\"" + (s != null ? s : "") + "\"";
	}
	
	public static String surr(int i) {
		return "\"" + Integer.toString(i) + "\"";
	}
	
}
