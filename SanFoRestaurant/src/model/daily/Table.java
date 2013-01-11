package model.daily;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta un elemento della tabella TableR.
 * 
 * @author Mauro
 */
public class Table implements Comparable<Table>{

	/** The a. */
	int a;
	
	/** The table numb. */
	int tableNumb;
	
	/** The seats. */
	int seats;
	
	/** The content. */
	int content;
	
	/** The state. */
	String state;
	
	/** The waiter code. */
	int waiterCode;
	
	/** The joined. */
	String joined;


	
	/**
	 * Istanzia un nuovo tavolo.
	 *
	 * @param a l'identificatore di riga
	 * @param tableNumb il numero del tavolo
	 * @param seats il numero di posti
	 * @param content la sala in cui è contenuto
	 * @param state lo stato del tavolo
	 * @param waiterCode il codice del cameriere
	 */
	public Table(int a, int tableNumb, int seats, int content, String state, int waiterCode) {
		this.a = a;
		this.tableNumb = tableNumb;
		this.seats = seats;
		this.content = content;
		this.state = state;
		this.waiterCode = waiterCode;
	}

	/**
	 * Istanzia un nuovo tavolo.
	 *
	 * @param a l'identificatore di riga
	 * @param tableNumb il numero del tavolo
	 * @param seats il numero di posti
	 * @param content la sala in cui è contenuto
	 * @param state lo stato del tavolo
	 * @param waiterCode il codice del cameriere
	 * @param joined il tavolo a cui è unito
	 */
	public Table(int a, int tableNumb, int seats, int content, String state, int waiterCode, String joined) {
		this(a, tableNumb, seats, content, state, waiterCode);
		this.joined = joined;
	}

	
	
	/**
	 * Gets the a.
	 *
	 * @return the a
	 */
	public int getA() {
		return a;
	}

	/**
	 * Sets the a.
	 *
	 * @param a the new a
	 */
	public void setA(int a) {
		this.a = a;
	}

	/**
	 * Gets the table numb.
	 *
	 * @return the table numb
	 */
	public int getTableNumb() {
		return tableNumb;
	}

	/**
	 * Sets the table numb.
	 *
	 * @param tableNumb the new table numb
	 */
	public void setTableNumb(int tableNumb) {
		this.tableNumb = tableNumb;
	}

	/**
	 * Gets the seats.
	 *
	 * @return the seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * Sets the seats.
	 *
	 * @param seats the new seats
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public int getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(int content) {
		this.content = content;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the waiter code.
	 *
	 * @return the waiter code
	 */
	public int getWaiterCode() {
		return waiterCode;
	}

	/**
	 * Sets the waiter code.
	 *
	 * @param waiterCode the new waiter code
	 */
	public void setWaiterCode(int waiterCode) {
		this.waiterCode = waiterCode;
	}

	/**
	 * Gets the joined.
	 *
	 * @return the joined
	 */
	public String getJoined() {
		return joined;
	}

	/**
	 * Sets the joined.
	 *
	 * @param joined the new joined
	 */
	public void setJoined(String joined) {
		this.joined = joined;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Table table) {
		// TODO Auto-generated method stub
		int i = 0;
		if(this.getSeats()<table.getSeats())
			i = -1;
		if(this.getSeats()>table.getSeats())
			i = +1;
		if(this.getSeats()==table.getSeats())
			i = 0;
		return i;
	}
	
}