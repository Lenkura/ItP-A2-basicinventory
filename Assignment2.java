import java.awt.Color;
import java.awt.Font;

public class Assignment2 {
	private GTerm gt;
	private String[] inventory;
	private int[] invValue;
	private int[] invCount;
	int debt;
	int debtCounter;
	int cash;
	int day;
	int listNumber;
	int inventoryCarry;
	private String[] itemList;
	private int[] itemPrice;
	private String[] storeList;
	private int[] storePrice;
	private int[] storeCount;
	int storeSize;
	private String location;

	// Reminders:
	// Text Box 0 = Quantity
	// Text Box 1 = Subtotal
	// Text Box 2 = Location
	// Text Box 3 = Cash
	// Text Box 4 = Debt
	// Text Box 5 = Day
	public Assignment2() {
		gt = new GTerm(1200, 800);
		debt = 5000;
		day = 0;
		listNumber = 0;
		cash = 500;
		location = "Start";
		opening();
//		randomiseStore();
		gt.clear();

		gt.setXY(50, 50);
		gt.addButton("Current Inventory", this, "inventory");
		gt.addButton("Check Store - Buy", this, "storeBuy");
		gt.addButton("Check Store - Sell", this, "storeSell");
		gt.println("");
		gt.addList(400, 500, this, null); // List 0
		gt.setXY(490, 85);
		gt.println("Choose Quantity\n");
		gt.addTextField("Enter Quantity", 110); // Text Area 0
		gt.println("");
		gt.println("Subtotal\n");
		gt.addTextField("SubTotal", 110); // Text Area 1
		gt.println("");
		gt.addButton("Calculate", this, "calculateSubtotal");
		gt.println("");
		gt.addButton("Buy Items", this, "buyItem");
		gt.addButton("Sell Item", this, "sellItem");
		gt.println("");
		gt.addButton("Help", this, null);

		gt.setXY(750, 50);
		gt.println("Current Location");
		gt.addButton("Refresh", this, "rLocation");
		gt.println("");
		gt.addTextField("Press a button to go somewhere!", 250); // Text Area 2
		gt.setXY(750, 150);
		gt.println("Current Cash");
		gt.addButton("Refresh", this, "rCash");
		gt.addTextField("Press a button to go somewhere!", 250); // Text Area 3
		gt.setXY(650, 300);
		gt.println("Current Debt");
		gt.addButton("Refresh", this, "rDebt");
		gt.println("");
		gt.addTextField(String.valueOf(debt), 45); // Text Area 4

		gt.setXY(900, 300);
		gt.println("Current Day \n");
		gt.addButton("Next Day", this, "nextDay");
		gt.addTextField(String.valueOf(day), 45); // Text Area 5

	}

	public void inventory() {
		clearList();
		this.location = "Inventory";
		int i = 0;
		String message = "";
		while (i < inventoryCarry) {
			message = this.inventory[i] + " " + this.invCount;
			gt.addElementToList(0, message);
			// List counter goes up
			this.listNumber += 1;
			i += 1;
		}

	}

	public void storeBuy() {
		clearList();
		this.location = "Buying";
		//List the array that store randomize handles
	}
	public void storeSell() {
		clearList();
		this.location = "Selling";
		//List the Item List and prices, will randomise with store
		
	}
	public void clearList() {
		// Removes everything in the list. Need to keep track of the list numbers
		// manually with the counter
		while (this.listNumber > 0) {
			gt.removeElementFromList(0, 0);
			this.listNumber += -1;
		}
	}

	public void rLocation() {
		gt.setTextInEntry(2, this.location);
	}

	public void rCash() {
		gt.setTextInEntry(3, String.valueOf(this.cash + 1));
	}

	public void rDebt() {
		gt.setTextInEntry(4, String.valueOf(this.debt + 1));
	}

	public void nextDay() {
		this.day += 1;
		gt.setTextInEntry(5, String.valueOf(this.day));
		clearList();
		randomiseStore();
	}

	public void calculateSubtotal() {
		gt.setTextInEntry(1,String.valueOf(Math.floor(Math.random()*(8))+3) );
	}

	public void buyItem() {
		//Transfer items from array, deduct money from price
	}

	public void sellItem() {
		//Select item, set quantity, sell. remove items from array. Add money to list

	}

	public void randomiseStore() {
		//Randomize the amount of items in the store. The program needs variety to progress
		//
		storeSize = (int) (Math.floor(Math.random()*(8))+3); // Source this?
		int i = 0;
		int item = 0;
		while (i <storeSize) {
			item = (int) (Math.floor(Math.random()*(8))+3);
			this.storeList[i] = this.itemList[item];
			// set item price ranges. If 0<item<3 then whatever. randomize a number put it into store price
			//Same thing with count, have ranges depending on the item. Use return here.
			
			i+=1;
		}
		

	}

	public void opening() {

	}

	public static void main(String[] args) {
		Assignment2 menu = new Assignment2();

	}

}
