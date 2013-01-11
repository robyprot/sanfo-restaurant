package model.daily;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta un elemento della tabella NearTable.
 * 
 * @author Mauro
 */
public class NearTable {

	/** The a. */
	int a;
	
	/** The table1. */
	int table1;
	
	/** The table2. */
	int table2;
	
	/** The sum seats. */
	int sumSeats;
	

	/**
	 * Istanzia un nuovo tavolo vicino.
	 *
	 * @param a l'identificatore di riga
	 * @param table1 il tavolo 1
	 * @param table2 il tavolo 2
	 * @param sumSeats i posti a sedere
	 */
	public NearTable(int a, int table1, int table2, int sumSeats) {
		this.a = a;
		this.table1 = table1;
		this.table2 = table2;
		this.sumSeats = sumSeats;
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
	 * Gets the table1.
	 *
	 * @return the table1
	 */
	public int getTable1() {
		return table1;
	}

	/**
	 * Sets the table1.
	 *
	 * @param table1 the new table1
	 */
	public void setTable1(int table1) {
		this.table1 = table1;
	}

	/**
	 * Gets the table2.
	 *
	 * @return the table2
	 */
	public int getTable2() {
		return table2;
	}

	/**
	 * Sets the table2.
	 *
	 * @param table2 the new table2
	 */
	public void setTable2(int table2) {
		this.table2 = table2;
	}
	
	/**
	 * Gets the sum seats.
	 *
	 * @return the sum seats
	 */
	public int getSumSeats() {
		return sumSeats;
	}

	/**
	 * Sets the sum seats.
	 *
	 * @param seats the new sum seats
	 */
	public void setSumSeats(int seats) {
		this.sumSeats = seats;
	}
	
}



