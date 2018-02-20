import java.util.ArrayList;

public class PSTFolder extends PSTItem {
	
	ArrayList<PSTItem> children;

	
	public PSTFolder() {
		super();
		this.children = new ArrayList<PSTItem>();
	}

	public PSTFolder(String name) {
		super(name);
		this.children = new ArrayList<PSTItem>();
	}
	
	public PSTFolder(String name, ArrayList<PSTItem> children) {
		super(name);
		this.children = children;
		this.updateWeight();
	}

	public PSTFolder(String name, int weight) {
		super(name, weight);
		this.children = new ArrayList<PSTItem>();
	}
	
	
	
	public void updateWeight() {
		int res = 0;
		for(PSTItem item : this.children) {
			res += item.weight;
		}
		
		this.weight = res;
	}

}
