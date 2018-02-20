
public class PSTItem {

	static int ID_count = 0;
	int ID;
	
	String name;
	
	int weight;
	
		public PSTItem() {
			this.ID = ID_count;
			ID_count++;
			
			this.name = "";
			
			this.weight = 0;
			
		}
		
		public PSTItem(String name) {
			this.ID = ID_count;
			ID_count++;
			
			this.name = name;
			
			this.weight = 0;
			
		}
		
		public PSTItem(String name, int weight) {
			this.ID = ID_count;
			ID_count++;
			
			this.name = name;
			
			this.weight = weight;
			
		}
}
