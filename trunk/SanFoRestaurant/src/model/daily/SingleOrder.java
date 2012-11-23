package model.daily;


public class SingleOrder {

	int rowNumb;
	int ordNumb;
	int quantity;
	String dish;
	String state;
	String notes;
	
	// constructors
	public SingleOrder(int rowNumb, int ordNumb, int quantity, String dish, String state, String notes) {
		this.rowNumb = rowNumb;
		this.ordNumb = ordNumb;
		this.quantity = quantity;
		this.dish = dish;
		this.state = state;
		this.notes = notes;
	}

	
	//methods
	public int getRowNumb() {
		return rowNumb;
	}

	public void setRowNumb(int rowNumb) {
		this.rowNumb = rowNumb;
	}

	public int getOrdNumb() {
		return ordNumb;
	}

	public void setOrdNumb(int ordNumb) {
		this.ordNumb = ordNumb;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDish() {
		return dish;
	}

	public void setDish(String dish) {
		this.dish = dish;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	

}









