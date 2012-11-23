package model.daily;

import java.util.GregorianCalendar;

public class Order {

	int orderNumb;
	int year, month, day, hour, minute, second;
	GregorianCalendar calend = new GregorianCalendar(year, month, day, hour, minute, second);
	int relativeTo;
	int seatsNumb;
	String stateOrd;
	
	
	// constructors
	public Order(int orderNumb, GregorianCalendar calend, int relativeTo, int seatsNumb, String stateOrd) {
		this.orderNumb = orderNumb;
		this.calend = calend;
		this.relativeTo = relativeTo;
		this.seatsNumb = seatsNumb;
		this.stateOrd = stateOrd;
	}


	// methods
	public int getOrderNumb() {
		return orderNumb;
	}

	public void setOrderNumb(int orderNumb) {
		this.orderNumb = orderNumb;
	}

	public GregorianCalendar getCalend() {
		return calend;
	}

	public void setCalend(GregorianCalendar calend) {
		this.calend = calend;
	}

	public int getRelativeTo() {
		return relativeTo;
	}

	public void setRelativeTo(int relativeTo) {
		this.relativeTo = relativeTo;
	}

	public int getSeatsNumb() {
		return seatsNumb;
	}
	
	public void setSeatsNumb(int seatsNumb) {
		this.seatsNumb = seatsNumb;
	}

	public String getStateOrd() {
		return stateOrd;
	}

	public void setStateOrd(String stateOrd) {
		this.stateOrd = stateOrd;
	}


	
}
