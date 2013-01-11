package start;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta l'oggetto Singola Ordinazione proveniente dal palmare del cameriere.
 * 
 * @author Mauro
 */
public class SingOrd {
	
	/** The a. */
	int a;
	
	/** The num. */
	String num;
	
	/** The code. */
	String code;
	
	/** The quantity. */
	String quantity;
	
	/** The note. */
	String note;
	
	/** The status. */
	String status;
	

	/**
	 * Istanzia una nuova singola ordinazione.
	 *
	 * @param a l'identificatore di riga
	 * @param num il numero del tavolo
	 * @param code il codice del piatto
	 * @param quantity le porzioni ordinate
	 * @param note le note aggiuntive
	 * @param status l'indicazione se la singola ordinazione è valida oppure è stata cancellata
	 */
	public SingOrd(int a, String num, String code, String quantity, String note, String status) {
		this.a = a;
		this.num = num;
		this.code = code;
		this.quantity = quantity;
		this.note = note;
		this.status = status;
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
	 * Gets the num.
	 *
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * Sets the num.
	 *
	 * @param num the new num
	 */
	public void setNum(String num) {
		this.num = num;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the note.
	 *
	 * @param note the new note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
