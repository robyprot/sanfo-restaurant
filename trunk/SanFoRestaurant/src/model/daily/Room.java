package model.daily;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta un elemento della tabella Room.
 * 
 * @author Mauro
 */
public class Room {

	/** The a. */
	int a;
	
	/** The room numb. */
	int roomNumb;
	
	/** The name. */
	String name;	
	
	/** The mq. */
	int mq;


	
	/**
	 * Istanzia un nuova Sala.
	 *
	 * @param a l'identificatore di riga
	 * @param roomNumb il numero della sala
	 * @param name il nome
	 */
	public Room(int a, int roomNumb, String name) {
		this.a = a;
		this.roomNumb = roomNumb;
		this.name = name;
	}
	
	/**
	 * Istanzia una nuova sala.
	 *
	 * @param a l'identificatore di riga
	 * @param roomNumb il numero della sala
	 * @param name il nome
	 * @param mq i metri quadri della sala
	 */
	public Room(int a, int roomNumb, String name, int mq){
		this(a, roomNumb, name);
		this.mq = mq;
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
	 * Gets the room numb.
	 *
	 * @return the room numb
	 */
	public int getRoomNumb() {
		return roomNumb;
	}

	/**
	 * Sets the room numb.
	 *
	 * @param roomNumb the new room numb
	 */
	public void setRoomNumb(int roomNumb) {
		this.roomNumb = roomNumb;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the mq.
	 *
	 * @return the mq
	 */
	public int getMq() {
		return mq;
	}

	/**
	 * Sets the mq.
	 *
	 * @param mq the new mq
	 */
	public void setMq(int mq) {
		this.mq = mq;
	}
	
	
}






