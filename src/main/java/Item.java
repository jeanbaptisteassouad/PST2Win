import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	
	public boolean write_CSV(String filepath) throws IOException {
		String str = get_CSVhead() + "\n" + this.get_CSV(null);
		
		OutputStream os = new FileOutputStream(filepath);
	    os.write(239);
	    os.write(187);
	    os.write(191);

	    PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));

	    w.print(str);
	    w.flush();
	    w.close();

	    return true;
	}
	
	public static String surr(String s) {
		return "\"" + (s != null ? s : "") + "\"";
	}
	
	public static String surr(int i) {
		return "\"" + Integer.toString(i) + "\"";
	}
	
}
