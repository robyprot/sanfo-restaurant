package model.daily;

public class Room {

	int roomNumb;
	String name;
	String type;	
	int mq;


	
	// constructors
	public Room(int roomNumb, String name, String type) {
		this.roomNumb = roomNumb;
		this.name = name;
		this.type = type;
	}
	
	public Room(int roomNumb, String name, String type, int mq){
		this(roomNumb, name, type);
		this.mq = mq;
	}

	
	// methods
	public int getRoomNumb() {
		return roomNumb;
	}

	public void setRoomNumb(int roomNumb) {
		this.roomNumb = roomNumb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getMq() {
		return mq;
	}

	public void setMq(int mq) {
		this.mq = mq;
	}
	
	
}






