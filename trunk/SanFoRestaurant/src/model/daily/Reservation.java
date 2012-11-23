package model.daily;

import java.util.GregorianCalendar;

public class Reservation {

	int reservationNumb;
	int year, month, day, hour, minute, second;
	GregorianCalendar calend = new GregorianCalendar(year, month, day, hour, minute, second);
	int reservedTable;
	int idClient;
	int peopleNumb;
	String smokingRoom;
	
	
	// constructors
	public Reservation(int reservationNumb, GregorianCalendar calend, int reservedTable, int idClient, int peopleNumb, String smokingRoom) {
		this.reservationNumb = reservationNumb;
		this.calend = calend;
		this.reservedTable = reservedTable;
		this.idClient = idClient;
		this.peopleNumb = peopleNumb;
		this.smokingRoom = smokingRoom;
	}


	// methods
	public int getReservationNumb() {
		return reservationNumb;
	}

	public void setReservationNumb(int reservationNumb) {
		this.reservationNumb = reservationNumb;
	}

	public GregorianCalendar getCalend() {
		return calend;
	}

	public void setCalend(GregorianCalendar calend) {
		this.calend = calend;
	}

	public int getReservedTable() {
		return reservedTable;
	}

	public void setReservedTable(int reservedTable) {
		this.reservedTable = reservedTable;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getPeopleNumb() {
		return peopleNumb;
	}

	public void setPeopleNumb(int peopleNumb) {
		this.peopleNumb = peopleNumb;
	}

	public String getSmokingRoom() {
		return smokingRoom;
	}

	public void setSmokingRoom(String smokingRoom) {
		this.smokingRoom = smokingRoom;
	}
	
	
}







