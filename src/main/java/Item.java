import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class Item {

	int ID;
	String name;
	long weight;
	ArrayList<Item> children;
	
	static String del = ";";
	
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
		
		res += surr("Titre");
		res += del;
		
		res += surr("From/adresse");
		res += del;
		
		res += surr("From/nom");
		res += del;
		
		res += surr("Liste To");
		res += del;
		
		res += surr("Date d'envoi");
		res += del;
		
		res += surr("Poids brut");
		res += del;
		
		res += surr("Poids");
		res += del;
		
		res += surr("Poids brut du mail seul");
		res += del;
		
		res += surr("Poids du mail seul");
		res += del;
		
		res += surr("Parent/ID");
		res += del;
		
		res += surr("Parent/Titre");
		res += del;
		
		res += surr("Liste PJs");
		res += del;
		
		res += surr("Lien");
		
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
	
	public static String surr(double d) {
		DecimalFormat numberFormat = new DecimalFormat("#");
		return "\"" + numberFormat.format(d) + "\"";
	}
	
	public static String write_smart_size(long size) {
		
		int n = 4;
		
		while(size / (Math.pow(1024, n)) < 1 && n > 0) {
			n--;
		}
		
		double newSize = size / (Math.pow(1024, n));
		
		String suffix = "";
		switch (n){
			case 0: suffix = "o"; break;
			case 1: suffix = "ko"; break;
			case 2: suffix = "Mo"; break;
			case 3: suffix = "Go"; break;
			case 4: suffix = "To"; break;
		}
		
		DecimalFormat numberFormat = new DecimalFormat("#.##");
		
		return numberFormat.format(newSize) + " " + suffix;
	}
	
}
