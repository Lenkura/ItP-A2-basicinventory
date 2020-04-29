
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
		gt.addTextField("", 150); // Text Field 0
		gt.setXY(220, 40);
		gt.print("Value\n");
		gt.addTextField("", 150); // Text Field 1
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
		gt.addButton("Sort Alphabetically", this, "sortAlpha");
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

		gt.setXY(500, 600);
		gt.addButton("Test block", this, "Test");
		gt.addButton("Test clear", this, "clearList");
		gt.addButton("Test fill", this, "fillList");
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
				message = this.expense[i] + " " + this.cost[i];
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
			gt.showMessageDialog("Entry Removed");
			clearList();
			fillList();
		}
	}

	public void editExpense() {
		if (gt.getSelectedElementFromList(0) == null) {
			gt.showMessageDialog("Please Select an Expense to delete");

		} else {
			int EditID = matchExpense((String) gt.getSelectedElementFromList(0));
			this.expense[EditID] = gt.getInputString("Change Entry Name\n Current Name: " + this.expense[EditID]);
			this.cost[EditID] = Double
					.parseDouble(gt.getInputString("Change Entry Value\n Current Value: " + this.cost[EditID]));
			gt.showMessageDialog("Entry Changed");
			clearList();
			fillList();
		}
	}

	public void sortAlpha() {
		clearList();
		// Bubble Sort for alphabetical order. Whenever a switch is made, we also
		// switched the corresponding positions in the Location array so data stayed
		// together

		int i = 0;
		while (i < this.currentNumExpenses - 1) {
			int j=0;
			while (j < this.currentNumExpenses - i - 1) {
				if (this.expense[j].compareToIgnoreCase(this.expense[j + 1]) > 0) {
					String temp = this.expense[j];
					this.expense[j] = this.expense[j + 1];
					this.expense[j + 1] = temp;
					double temp1 = this.cost[j];
					this.cost[j] = this.cost[j + 1];
					this.cost[j + 1] = temp1;

				}
				j += 1;
			}
			i += 1;
		}
		// Array should now be in alphabetical order, just display it
		fillList();
	}

	public void sortExpense() {
		clearList();
//
//	    { 
//	        for (int i = 0; i < currentNumExpenses-1; i++) 
//	            for (int j = 0; j < currentNumExpenses-i-1; j++) 
//	                if (this.cost[j] > this.cost[j+1]) 
//	                { 
//	                    // swap arr[j+1] and arr[i] 
//	                    double temp = this.cost[j]; 
//	                    this.cost[j] = this.cost[j+1]; 
//	                    this.cost[j+1] = temp; 
//	                } 
//	    } 

		int i = 0;
		while (i < this.currentNumExpenses - 1) {
			int j=0;
			while (j < this.currentNumExpenses - i - 1) {
				if (this.cost[j] > this.cost[j + 1]) {
					double temp = this.cost[j];
					this.cost[j] = this.cost[j + 1];
					this.cost[j+1] = temp;
					String temp1 = this.expense[j];
					this.expense[j] = this.expense[j+1];
					this.expense[j+1] = temp1;

				}
				j += 1;

			}
			i += 1;
		}
		fillList();
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
			String message = this.expense[i] + " " + this.cost[i];
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
		// talk about issues here with formatting
		while (i < this.currentNumExpenses
				&& !(this.expense[i].equalsIgnoreCase(name) == true && this.cost[i] == (value)))
			i += 1;
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
		this.expense[0] = "Laser";
		this.cost[0] = 12.5;
		this.expense[1] = "Food";
		this.cost[1] = 2.5;
		this.expense[2] = "Tiger";
		this.cost[2] = 1.5;
		this.expense[3] = "Man";
		this.cost[3] = 12;
		this.expense[4] = "are";
		this.cost[4] = 5.50;
		this.expense[5] = "sop";
		this.cost[5] = 19.99;
		this.expense[6] = "wqe";
		this.cost[6] = 56.21;
		this.expense[7] = "let";
		this.cost[7] = 7.6;
		this.expense[8] = "bone";
		this.cost[8] = 45.5;
		this.expense[9] = "er";
		this.cost[9] = 112;
		currentNumExpenses = 10;

	}

	public static void main(String[] args) {
		ExpenseJournal assignment2 = new ExpenseJournal();

	}

}
