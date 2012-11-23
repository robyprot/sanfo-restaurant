package model.daily;

public class Client {

	int id;
	String name;
	String surname;
	int counter;
	String allergy;
	
	
	// constructors
	public Client(int id, String name, String surname, int counter) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.counter = counter;
	}
	
	public Client(int id, String name, String surname, int counter, String allergy) {
		this(id, name, surname, counter);
		this.allergy = allergy;
	}

	
	// methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
		
}

