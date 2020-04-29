
public class ExpenseJournal {
	private GTerm gt;
	private String[] expense;
	private double[] cost;
	private String[] category;
	private int currentNumExpenses;
	private int maxNumExpenses;
	private int listNumber;

	public ExpenseJournal() {
		gt = new GTerm(1200, 800);
		currentNumExpenses = 0;
		maxNumExpenses = 10;
		listNumber = 0;
		this.expense = new String[maxNumExpenses];
		this.cost = new double[maxNumExpenses];
		this.category = new String[maxNumExpenses];

		gt.setXY(50, 40);
		gt.print("Expense\n");
		gt.addTextField("Enter an Expense Name", 150); // Text Field 0
		gt.setXY(220, 40);
		gt.print("Value\n");
		gt.addTextField("Value (Number)", 150); // Text Field 1
		gt.setXY(50, 80);
		gt.addButton("Add Expense", this, "addExpense");
		gt.addButton("Search for Expense", this, "findExpense");
		gt.addButton("Search for Value", this, "findValue");
		gt.println("");
		gt.addList(400, 500, this, null);

		gt.setXY(450, 120);
		gt.addButton("Remove Expense", this, "removeExpense");
		gt.println("");
		gt.addButton("Edit Expense", this, "editExpense");
		gt.println("");
		gt.addButton("Set Category", this, "setCategory");
		gt.println("");
		gt.addButton("Sort Alphabetically", this, "sortalpha");
		gt.println("");
		gt.addButton("Sort by Category", this, "sortCategory");
		gt.println("");
		gt.addButton("Sort by Expense", this, "sortExpense");
		gt.println("");

		gt.setXY(450, 500);
		gt.print("Total Expenses\n");
		gt.addTextField("", 100); // Text Field 2
		gt.println("");
		gt.addButton("Recalculate", this, "rTotal");
		
		gt.setXY(500,600);
		gt.addButton("Test block", this, "Test");
	}

	public void addExpense() {
		// check if array is full, if it is extend the array
		arrayExtender();

		String title = gt.getTextFromEntry(0);
		double cost = Double.parseDouble(gt.getTextFromEntry(1));
		if (title == null || cost < 0) {
			gt.showMessageDialog("Please enter a valid name and value (> 0)");
			gt.setTextInEntry(0, "Type Something Here!");
		} else {
//		 Check if the song title matches one already entered
//			int i = matchExpense(title);
			// If it matches, update the location info only, no new entry is made
//		if (i < this.currentNumExpenses) {
//			this.songLocations[i] = location;
//			gt.showMessageDialog("Song name already entered, location updated");
//			// otherwise make a new entry
//		} else {
			this.expense[this.currentNumExpenses] = title;
			this.cost[this.currentNumExpenses] = cost;
			gt.addElementToList(0, this.expense[this.currentNumExpenses] + " " + this.cost[this.currentNumExpenses]);
			this.currentNumExpenses += 1;
			this.listNumber += 1;
		}
	}

//	}
	public void findExpense() {
		// Get search query
		String target = gt.getTextFromEntry(0);
		// Clear list, only want the search results to be visible
		clearList();
		String message = "";
		// remove case sensitivity
		target = target.toLowerCase();

		int i = 0;
		while (i < this.currentNumExpenses) {
			// find matching songs that contain the query fragment
			if (this.expense[i].toLowerCase().contains(target)) {
				// as each one is found, add it to the list
				message = this.expense[i]+" "+ this.cost[i];
				gt.addElementToList(0, message);
				// List counter goes up
				this.listNumber += 1;
			}
			i += 1;
		}
	}

	public void removeExpense() {
		// Error message if no item in the list is selected
		if (gt.getSelectedElementFromList(0) == null) {
			gt.showMessageDialog("Please Select an Expense to delete");

		} else {
			// Two separate counters. One ticks up for the current array, one for);
			// Scope for removing an expense:
			// Make a new array, copy over everything EXCEPT the expense chosen for deletion
			// Making the an array to transfer data to be kept It's one smaller as we are
			// deleting an entry
			String[] TempExpense = new String[this.maxNumExpenses];
			double[] TempCost = new double[this.maxNumExpenses];

			// Find the index of the song to be deleted
			int removeID = matchExpense((String) gt.getSelectedElementFromList(0));
			// Two separate counters. One ticks up for the current array, one for the new
			// array
			int i = 0;
			int t = 0;
//			 Straight transfer across for data that is not to be deleted
			while (i < removeID) {
				TempExpense[t] = this.expense[i];
				TempCost[t] = this.cost[i];
				i += 1;
				t += 1;
			}
			// Should now be at i = removeID; the index which we want to delete. Skip over
			// it, no data transfer counter ticks up only
			i += 1;

			// resume data transfer, note that the temp array is now one smaller
			while (removeID < i && i < this.currentNumExpenses) {
				TempExpense[t] = this.expense[i];
				TempCost[t] = this.cost[i];
				i += 1;
				t += 1;
			}
			// reassign the array names to keep things tidy
			this.expense = TempExpense;
			this.cost = TempCost;

			this.currentNumExpenses += -1;
			// remove item from the list
			clearList();
			fillList();
			}
		}

	public void editExpense() {

	}

	public void rTotal() {
		int i = 0;
		double total = 0;
		while (i < currentNumExpenses) {
			total = this.cost[i] + total;
			i += 1;
		}
		gt.setTextInEntry(2, String.valueOf(total));
	}

	public void clearList() {
		// Removes everything in the list. Need to keep track of the list numbers
		// manually with the counter
		while (this.listNumber > 0) {
			gt.removeElementFromList(0, 0);
			this.listNumber += -1;
		}

	}
	public void fillList() {
		int i = 0;
		while (i < this.currentNumExpenses) {
			String message = this.expense[this.currentNumExpenses] + " " + this.cost[this.currentNumExpenses];
			gt.addElementToList(0, message);
			i += 1;
			// List counter goes up
			this.listNumber += 1;
		}
	}

	public String retrieveExpense(String Entry) {
		String[] elementsofEntry = Entry.split(" ");
		String name = elementsofEntry[0];
		return name;
	}

	public Double retrievevalue(String Entry) {
		String[] elementsofEntry = Entry.split(" ");
		double value = Double.parseDouble(elementsofEntry[1]);
		return value;
	}

	public int matchExpense(String Entry) {
		// method for whenever I want to find a specific match in the array
		String[] elementsofEntry = Entry.split(" ");
		String name = elementsofEntry[0];
		double value = Double.parseDouble(elementsofEntry[1]);

		int i = 0;
		while (i < this.currentNumExpenses && this.expense[i].equalsIgnoreCase(name) == false)
			if (this.cost[i] != value) {
				i += 1;
			} else {
			}
		return i;
	}

	public void arrayExtender() {
		// Only triggers when at at the limit
		if (this.currentNumExpenses == this.maxNumExpenses) {
			// make a new array, one larger. No reason not to extend array sizes by more
			// than 1
			String[] TempExpenses = new String[this.maxNumExpenses + 10];
			double[] TempCosts = new double[this.maxNumExpenses + 10];
			String[] Tempcategory = new String[this.maxNumExpenses + 10];
			// Transfer all data to new array
			int i = 0;
			while (i < this.maxNumExpenses) {
				TempExpenses[i] = this.expense[i];
				TempCosts[i] = this.cost[i];
				Tempcategory[i] = this.category[i];
				i += 1;
			}
			// renaming, to keep old naming scheme so everything else works
			this.expense = TempExpenses;
			this.cost = TempCosts;
			this.category = Tempcategory;
			// Increase this as its now larger
			this.maxNumExpenses += 10;
		}
	}

	public void Test() {
		this.expense[0] = "Food";
		this.cost[0] = 12.5;
		this.expense[1] = "Food";
		this.cost[1] = 2.5;
		this.expense[2] = "Food";
		this.cost[2] = 1.5;
		this.expense[3] = "Food";
		this.cost[3] = 12;
		currentNumExpenses = 4;
		
	}
	public static void main(String[] args) {
		ExpenseJournal assignment2 = new ExpenseJournal();

	}

}
