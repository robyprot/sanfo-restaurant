package start;

public class Conto {

	String dish;
	String name;
	float price;
	String type;
	int quantity;
	String state;
	

	// constructors
	public Conto(String dish, String name, float price, String type, int quantity, String state) {
		this.dish = dish;
		this.name = name;
		this.price = price;
		this.type = type;
		this.quantity = quantity;
		this.state = state;
	}
	
	
	public String getDish() {
		return dish;
	}

	public void setDish(String dish) {
		this.dish = dish;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
