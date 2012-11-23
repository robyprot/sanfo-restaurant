package model.daily;

public class Table{

	int tableNumb;
	int seats;
	int content;
	String state;
	int waiterCode;
	String joined;


	// constructors
	public Table(int tableNumb, int seats, int content, String state, int waiterCode) {
		this.tableNumb = tableNumb;
		this.seats = seats;
		this.content = content;
		this.state = state;
		this.waiterCode = waiterCode;
	}

	public Table(int tableNumb, int seats, int content, String state, int waiterCode, String joined) {
		this(tableNumb, seats, content, state, waiterCode);
		this.joined = joined;
	}

	
	// methods
	public int getTableNumb() {
		return tableNumb;
	}

	public void setTableNumb(int tableNumb) {
		this.tableNumb = tableNumb;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getContent() {
		return content;
	}

	public void setContent(int content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getWaiterCode() {
		return waiterCode;
	}

	public void setWaiterCode(int waiterCode) {
		this.waiterCode = waiterCode;
	}

	public String getJoined() {
		return joined;
	}

	public void setJoined(String joined) {
		this.joined = joined;
	}

	
	
}