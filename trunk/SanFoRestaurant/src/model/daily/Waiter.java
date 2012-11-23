package model.daily;

public class Waiter {

	int numb;
	String surname;
	String name;
	


	// constructors
	public Waiter(int numb, String surname, String name) {
		this.numb = numb;
		this.surname = surname;
		this.name = name;
	}

	
	// methods
	public int getNumb() {
		return numb;
	}

	public void setNumb(int numb) {
		this.numb = numb;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

	