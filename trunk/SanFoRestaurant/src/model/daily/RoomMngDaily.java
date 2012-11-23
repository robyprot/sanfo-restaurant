package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.JavaDBException;


public class RoomMngDaily {

	private static RoomMngDaily instance = null;
	public ArrayList<Room> roomList;
	
	
	// constructor
	private RoomMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}
	
	
	private void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.Room";
		roomList = new ArrayList<Room>();

		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			roomList.add(new Room(res.getInt("RoomNumb"), 
								  res.getString("Name"),  
								  res.getString("Type"),
								  res.getInt("Mq")));
		}
		res.close();
		System.out.println("List Rooms OK");
		Database.disconnect();
	}
	
	
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
	
	
	// Returns a list of rooms
	public ArrayList<Room> getRoomList() {
		return roomList;
	}
	
	
	public void setRoomList(ArrayList<Room> roomList) {
		this.roomList = roomList;
	}
	

//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////

	
	// Ritorna le sale fumatori
	public ArrayList<Room> getSmokingRooms() throws ClassNotFoundException, SQLException{
		ArrayList<Room> smokingRoom = new ArrayList<Room>();
		for(int i=0; i<roomList.size(); i++){
			Room r = roomList.get(i);
			if(r.getType().equalsIgnoreCase("Fumatori")){
				smokingRoom.add(r);
			}
		}
		return smokingRoom;
	}
	
	
	// Ritorna le sale non fumatori
	public ArrayList<Room> getNoSmokingRooms() throws ClassNotFoundException, SQLException{
		ArrayList<Room> noSmokingRoom = new ArrayList<Room>();
		for(int i=0; i<roomList.size(); i++){
			Room r = roomList.get(i);
			if(r.getType().equalsIgnoreCase("NonFumatori")){
				noSmokingRoom.add(r);
			}
		}
		return noSmokingRoom;
	}
	
	
	// Ritorna le sale di un certo tipo fumatori/nonfumatori
	public ArrayList<Room> getRooms(String type) throws ClassNotFoundException, SQLException{
		ArrayList<Room> rooms = new ArrayList<Room>();
		for(int i=0; i<roomList.size(); i++){
			Room r = roomList.get(i);
			if(r.getType().equalsIgnoreCase(type)){
				rooms.add(r);
			}
		}
		return rooms;
	}


	// Ritorna la sala dato il numero
	public Room getRoom(int roomNumb) throws ClassNotFoundException, SQLException{
		Room room = null;
		for(int i=0; i<roomList.size(); i++){
			Room r = roomList.get(i);
			if(r.getRoomNumb()==roomNumb){
				room = r;
			}
		}
		return room;
	}
	
	
	// Ritorna la sala dato il nome
	public Room getRoom(String name) throws ClassNotFoundException, SQLException{
		Room room = null;
		for(int i=0; i<roomList.size(); i++){
			Room r = roomList.get(i);
			if(r.getName().equalsIgnoreCase(name)){
				room = r;
			}
		}
		return room;
	}
	
	
	
	// Dato un numero di sala, setta i mq
	public void setRoomMq(int roomNumb, int mq) throws ClassNotFoundException, SQLException{
		for(int i=0; i<roomList.size(); i++){
			Room r = roomList.get(i);
			if(r.getRoomNumb()==roomNumb){
				r.setMq(mq);
				roomList.set(i, r);
			}
		}
	}
	
	
	// Dato un numero di sala, setta il tipo
	public void setRoomType(int roomNumb, String type) throws ClassNotFoundException, SQLException{
		for(int i=0; i<roomList.size(); i++){
			Room r = roomList.get(i);
			if(r.getRoomNumb()==roomNumb){
				r.setType(type);
				roomList.set(i, r);
			}
		}
	}
	
	
	// Dato un numero di sala, setta il nome
	public void setRoomName(int roomNumb, String name) throws ClassNotFoundException, SQLException{
		for(int i=0; i<roomList.size(); i++){
			Room r = roomList.get(i);
			if(r.getRoomNumb()==roomNumb){
				r.setName(name);
				roomList.set(i, r);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	