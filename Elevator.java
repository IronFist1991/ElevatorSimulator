import java.util.ArrayList;

public class Elevator {
	
	private int eID; 			//elevator ID
	private int floorNumber; 	//current floor number
	private int capacity;		//capacity
	private boolean doors;		//doors open(true), close (false)
	
	private enum direction{UP, DOWN, IDLE}    								//Elevator state
	private ArrayList<Integer> riders = new ArrayList<Integer>();			//List of riders (pID)
	private ArrayList<Integer> floorStops = new ArrayList<Integer>();		//List of floor stops
	private ArrayList<Integer> riderStops = new ArrayList<Integer>();		//List of rider stops
	
	//Elevator constructor
	public Elevator(int eID,int capacity) {
		this.eID = eID;
		floorNumber = 1;
		this.capacity = capacity;
	}
	

	public int geteID() {
		return eID;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public int getCapacity() {
		return capacity;
	}
	
	
	
}
