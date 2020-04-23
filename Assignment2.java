import java.awt.Color;
import java.awt.Font;

public class Assignment2 {
	private GTerm gt;
	private String[] inventory;
	private int[] invValue;
	int debt;
	
	private String [] storeList;
	private int [] storePrice;
	private String location;
	
	//Reminders:
	//Location Text box = 0
	//Debt Text box = 1
	//List = 0
	public Assignment2() {
		gt = new GTerm(1200, 800);
		debt = 5000;
		opening();
		gt.clear();
		
		gt.setXY(50, 50);
		gt.addButton("Current Inventory", this, "inventory");
		gt.addButton("Check Store", this, "store");
		gt.addButton("", this, null);
		gt.addButton("d", this, null);
		gt.addButton("e", this, null);
		gt.addButton("f", this, null);
		gt.setXY(490, 85);
		gt.addButton("g", this, null);
		gt.println("");
		gt.addButton("h", this, null);
		gt.println("");
		gt.addButton("Help", this, null);
		gt.setXY(50, 85);
		gt.addList(400, 500, this, null);
		
		gt.setXY(650, 50);
		gt.println("Current Location");
		gt.addTextArea("", 300, 50); //Text Area 0
		gt.setXY(650, 300);
		gt.println("Current Location");
		
	}
	public void inventory() {
		gt.clear();
		gt.setXY(50, 50);
		gt.addButton("Check Store", this, "store");
		gt.addList(200, 200, this, null);
		gt.addTextField("", 300);
		gt.addButton("beep", this, "tester1");
	}
	public void tester1() {
		String text = gt.getTextFromEntry(0);
		gt.addElementToList(1, text);
	}
	public void store() {
		gt.clear();
		gt.setXY(50, 50);
		gt.addButton("Check Inventory", this, "inventory");
		gt.addList(200, 200, this, null);
		gt.addTextField("", 300);
		gt.addButton("beep", this, "tester");
	}
	public void tester() {
		String text = gt.getTextFromEntry(0);
		gt.addElementToList(2, text);
	}
	public void debt() {
		gt.clear();
		gt.setXY(50, 50);
		gt.println("Add some set dressing here");
		
		
		
		gt.setXY(200,200);
		gt.setFontSize(50);
		gt.print("CURRENT DEBT:" +debt);
		gt.println("");
		gt.addButton("Current Inventory", this, "inventory");
		gt.addButton("Check Store", this, "store");
		
	}
	public void opening () {
		
	}

	public static void main(String[] args) {
		Assignment2 menu = new Assignment2();

	}

}
