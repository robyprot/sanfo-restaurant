package model.daily;

import java.util.GregorianCalendar;

public class TablesCheck {

	int id;
	int year, month, day, hour, minute, second;
	GregorianCalendar calend = new GregorianCalendar(year, month, day, hour, minute, second);
	float amount;
	int peopleNumb;

	// constructors
	public TablesCheck(int id, GregorianCalendar calend, float amount, int peopleNumb) {
		this.id = id;
		this.calend = calend;
		this.amount = amount;
		this.peopleNumb = peopleNumb;
	}

	
	// methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GregorianCalendar getCalend() {
		return calend;
	}

	public void setCalend(GregorianCalendar calend) {
		this.calend = calend;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getPeopleNumb() {
		return peopleNumb;
	}

	public void setPeopleNumb(int peopleNumb) {
		this.peopleNumb = peopleNumb;
	}
	
}



