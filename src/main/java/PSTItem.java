import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public abstract class PSTItem {

	int ID;
	String name;
	int weight;
	
	static String del = ",";
	
		public PSTItem() {
			this.ID = 0;
			this.name = "";
			this.weight = 0;
		}
		
	public abstract String write_CSVline();
	
	public static String write_CSV_head() {
		String res ="";
		
		res += surr("ID");
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
	
	public static String surr(String s) {
		return "\"" + s + "\"";
	}
	
	public static String surr(int i) {
		return "\"" + Integer.toString(i) + "\"";
	}
}
