package model.daily;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import database.Database;
import database.JavaDBException;


public class ReservationMngDaily {

	private static ReservationMngDaily instance = null;
	public ArrayList<Reservation> reservationList;

	
	// constructor
	private ReservationMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}

	
	private void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.Reservation";
		reservationList = new ArrayList<Reservation>();
		
		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			Date d = res.getDate("Date");
			Time t = res.getTime("Time");
			GregorianCalendar greg = new GregorianCalendar();
			greg.setTime(d);
			greg.set(Calendar.HOUR, t.getHours());
			greg.set(Calendar.MINUTE, t.getMinutes());
			greg.set(Calendar.SECOND, t.getSeconds());
			reservationList.add(new Reservation(res.getInt("ReservationNumb"),
												greg,
												res.getInt("ReservedTable"), 
												res.getInt("IDClient"),
												res.getInt("PeopleNumb"),
												res.getString("SmokingRoom")));
		}
		res.close();
		System.out.println("List Reservation OK");
		Database.disconnect();
	}

	
	public static ReservationMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (ReservationMngDaily.class) {
				if (instance == null) {
					instance = new ReservationMngDaily();
				}
			}
		}
		return instance;
	}
	
	
	// Returns a list of reservations
	public ArrayList<Reservation> getReservationList() {
		return reservationList;
	}
	
	
	public void setReservationList(ArrayList<Reservation> reservationList) {
		this.reservationList = reservationList;
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// Ritorna la prenotazione passato il numero
	public Reservation getReservationNumber(int reservationNumb){
		Reservation reserv = null;
		for(int i=0; i<reservationList.size(); i++){
			Reservation r = reservationList.get(i);
			if(r.getReservationNumb()==reservationNumb){
				reserv = r;
			}
		}
		return reserv;
	}
	
	
	// Ritorna le prenotazioni di un certo tavolo
	public ArrayList<Reservation> getReservationsTable(int reservedTable) throws ClassNotFoundException, SQLException{
		ArrayList<Reservation> reserv = new ArrayList<Reservation>();
		for(int i=0; i<reservationList.size(); i++){		
			Reservation r = reservationList.get(i);
			if(r.getReservedTable()==reservedTable){
				reserv.add(r);
			}
		}
		return reserv;
	}
	
		
	// Ritorna le prenotazioni di un certo tavolo in un certo giorno (ora non importa)
	public ArrayList<Reservation> getReservationsTableOnDate(int reservedTable, GregorianCalendar gr) throws ClassNotFoundException, SQLException{
		ArrayList<Reservation> reserv = new ArrayList<Reservation>();
		for(int i=0; i<reservationList.size(); i++){		
			Reservation r = reservationList.get(i);
			if(r.getReservedTable()==reservedTable && r.getCalend().get(Calendar.YEAR)==gr.get(Calendar.YEAR)
					&& r.getCalend().get(Calendar.MONTH)==gr.get(Calendar.MONTH)	
					&& r.getCalend().get(Calendar.DAY_OF_MONTH)==gr.get(Calendar.DAY_OF_MONTH)){
				reserv.add(r);
			}
		}
		return reserv;
	}
	
	
	// Ritorna le prenotazioni di un certo cliente
	public ArrayList<Reservation> getReservationsClient(int idClient) throws ClassNotFoundException, SQLException{
		ArrayList<Reservation> reserv = new ArrayList<Reservation>();
		for(int i=0; i<reservationList.size(); i++){		
			Reservation r = reservationList.get(i);
			if(r.getIdClient()==idClient){
				reserv.add(r);
			}
		}
		return reserv;
	}
	
	
	// Ritorna le prenotazioni di un certo cliente in un certo giorno (ora non importa)
	public ArrayList<Reservation> getReservationsClientOnDate(int idClient, GregorianCalendar gr) throws ClassNotFoundException, SQLException{
		ArrayList<Reservation> reserv = new ArrayList<Reservation>();
		for(int i=0; i<reservationList.size(); i++){		
			Reservation r = reservationList.get(i);
			if(r.getIdClient()==idClient && r.getCalend().get(Calendar.YEAR)==gr.get(Calendar.YEAR)
					&& r.getCalend().get(Calendar.MONTH)==gr.get(Calendar.MONTH)	
					&& r.getCalend().get(Calendar.DAY_OF_MONTH)==gr.get(Calendar.DAY_OF_MONTH)){
				reserv.add(r);
			}
		}
		return reserv;
	}
	
	
	// Ritorna le prenotazioni di un tipo di sala (fumatori o non fumatori)
	public ArrayList<Reservation> getReservationsTypeRoom(String smokingRoom) throws ClassNotFoundException, SQLException{
		ArrayList<Reservation> reserv = new ArrayList<Reservation>();
		for(int i=0; i<reservationList.size(); i++){		
			Reservation r = reservationList.get(i);
			if(r.getSmokingRoom().equalsIgnoreCase(smokingRoom)){
				reserv.add(r);
			}
		}
		return reserv;
	}
	
	
	// Ritorna le prenotazioni di un tipo di sala (fumatori o non fumatori) in un certo giorno (ora non importa)
	public ArrayList<Reservation> getReservationsTypeRoomOnDate(String smokingRoom, GregorianCalendar gr) throws ClassNotFoundException, SQLException{
		ArrayList<Reservation> reserv = new ArrayList<Reservation>();
		for(int i=0; i<reservationList.size(); i++){		
			Reservation r = reservationList.get(i);
			if(r.getSmokingRoom().equalsIgnoreCase(smokingRoom) 
					&& r.getCalend().get(Calendar.YEAR)==gr.get(Calendar.YEAR)
					&& r.getCalend().get(Calendar.MONTH)==gr.get(Calendar.MONTH)	
					&& r.getCalend().get(Calendar.DAY_OF_MONTH)==gr.get(Calendar.DAY_OF_MONTH)){
				reserv.add(r);
			}
		}
		return reserv;
	}
	
	
	// Ritorna le prenotazioni con un certo numero di persone
	public ArrayList<Reservation> getReservationsPeopleNumbers(int peopleNumb) throws ClassNotFoundException, SQLException{
		ArrayList<Reservation> reserv = new ArrayList<Reservation>();
		for(int i=0; i<reservationList.size(); i++){		
			Reservation r = reservationList.get(i);
			if(r.getPeopleNumb()==peopleNumb){
				reserv.add(r);
			}
		}
		return reserv;
	}
	
	
	// Ritorna le prenotazioni con un certo numero di persone in un certo giorno (ora non importa)
	public ArrayList<Reservation> getReservationsPeopleNumbersOnDate(int peopleNumb, GregorianCalendar gr) throws ClassNotFoundException, SQLException{
		ArrayList<Reservation> reserv = new ArrayList<Reservation>();
		for(int i=0; i<reservationList.size(); i++){		
			Reservation r = reservationList.get(i);
			if(r.getPeopleNumb()==peopleNumb && r.getCalend().get(Calendar.YEAR)==gr.get(Calendar.YEAR)
					&& r.getCalend().get(Calendar.MONTH)==gr.get(Calendar.MONTH)	
					&& r.getCalend().get(Calendar.DAY_OF_MONTH)==gr.get(Calendar.DAY_OF_MONTH)){
				reserv.add(r);
			}
		}
		return reserv;
	}
	
	
	// Ritorna tutte le prenotazioni di un certo giorno (ora non importa)
	public ArrayList<Reservation> getReservationsOnDate(GregorianCalendar gr) throws ClassNotFoundException, SQLException{
		ArrayList<Reservation> reserv = new ArrayList<Reservation>();
		for(int i=0; i<reservationList.size(); i++){		
			Reservation r = reservationList.get(i);
			if(r.getCalend().get(Calendar.YEAR)==gr.get(Calendar.YEAR)
					&& r.getCalend().get(Calendar.MONTH)==gr.get(Calendar.MONTH)	
					&& r.getCalend().get(Calendar.DAY_OF_MONTH)==gr.get(Calendar.DAY_OF_MONTH)){
				reserv.add(r);
			}
		}
		return reserv;
	}
	
	
	
	/// AGGIUNGERE METODI SET //////////////////////////
	
	
	
	
	
}






