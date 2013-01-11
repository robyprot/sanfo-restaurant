package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ManagerDailyInterface;

import database.Database;
import database.JavaDBException;


// TODO: Auto-generated Javadoc
/**
 * Classe che crea la lista di Room e che contiene i metodi per gestirla.
 * 
 * @author Mauro
 */
public class RoomMngDaily implements ManagerDailyInterface {

	/** The instance. */
	private static RoomMngDaily instance = null;
	
	/** The room list. */
	public ArrayList<Room> roomList;
	
	
	
	/**
	 * Istanzia un nuovo Manager Room Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private RoomMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}
	
	
	/**
	 * Inizializza.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.Room";
		roomList = new ArrayList<Room>();

		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			roomList.add(new Room(res.getInt("I"),
								  res.getInt("RoomNumb"), 
								  res.getString("Name"),  
								  res.getInt("Mq")));
		}
		res.close();
		System.out.println("List Rooms OK");
		Database.disconnect();
	}
	
	
	/**
	 * Ritorna l'istanza.
	 *
	 * @return l'istanza
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public static RoomMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (RoomMngDaily.class) {
				if (instance == null) {
					instance = new RoomMngDaily();
				}
			}
		}
		return instance;
	}
	
	
	
	/**
	 * Ritorna la lista delle sale.
	 *
	 * @return the room list
	 */
	public ArrayList<Room> getRoomList() {
		return roomList;
	}
	
	
	/**
	 * Setta la lista delle sale.
	 *
	 * @param roomList la nuova lista delle sale
	 */
	public void setRoomList(ArrayList<Room> roomList) {
		this.roomList = roomList;
	}
	
}
