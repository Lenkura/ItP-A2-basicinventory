

public class ExpenseJournal {
	private GTerm gt;
	private String[] expense;
	private int[] cost;
	private String[] category;
	
	
	
	public ExpenseJournal() {
		gt = new GTerm(700, 800);
		gt.setXY(50, 50);
		gt.addButton("Add Expense",this, "addExpense");
		gt.addButton("Remove Expense", this, "removeExpense");
		gt.addButton("Set Category", this, "setCategory");
		gt.println("");
		gt.addList(400, 500, this, null);
		
		gt.setXY(450, 100);
		gt.addButton("Edit Expense",this, "editExpense");
		gt.println("");
		gt.addButton("Sort by Alphabetically",this, "sortalpha");
		gt.println("");
		gt.addButton("Sort by Category",this, "sortCategory");
		gt.println("");
		gt.addButton("Sort by Expense",this, "sortExpense");
		gt.println("");
		
		gt.setXY(450, 500);
		gt.print("Total Expenses\n");
		gt.addTextField("", 100);
		gt.println("");
		gt.addButton("Recalculate",this, "rTotal");
	}
	

	public static void main(String[] args) {
		ExpenseJournal assignment2 = new ExpenseJournal();

	}

}
