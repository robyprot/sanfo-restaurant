package model.daily;

public class Food {

	String code;
	String name;
	float price;
	String type;
	String description;


	// constructors
	public Food(String code, String name, float price, String type) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public Food(String code, String name, float price, String type, String description) {
		this(code, name, price, type);
		this.description = description;
	}
	
	// methods
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}





