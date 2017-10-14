
import java.util.ArrayList;

public class Elevator {
	
	private int eId; //elevator ID
	private int currentFloor; // floor the elev is on
	private int capacity; // how many people can fit in the elev
	private long floorTime;
	private long doorOpenTime;
	private long idleTime;
	
	enum direction { EUP, EDOWN, IDLE };
	private direction state; //state of the elevator
	private boolean doors; // true = open, false = close
	
	private ArrayList<Integer> riders = new ArrayList<Integer>(); // people on elevator
	private ArrayList<Integer> floorStops = new ArrayList<Integer>(); // stops the elevator has to make as given by people pressing buttons
	private ArrayList<Integer> riderStops = new ArrayList<Integer>(); // stops the elevator has to make because the people on the elevator want to stop
	Building b = Building.getInstance();
	
	//constructor
	public Elevator(int eId, int capacity, long floorTime, long doorOpenTime, long idleTime){
		this.eId = eId;
		this.currentFloor = 1;
		this.capacity = capacity;
		this.floorTime = floorTime;
		this.doorOpenTime = doorOpenTime;
		this.idleTime = idleTime;
		state = direction.IDLE;
	}
	

	//return eId
	public int geteId() {
		return eId;
	}
	
	//return elevator state
	public direction getState() {
		return state;
	}

	// get current floor number
	public int getCurrentFloor() {
		return currentFloor;
	}

	// are the doors open or closed
	public boolean isDoors() {
		return doors;
	}

	// set doors to open or close
	public void setDoors(boolean doors) {
		this.doors = doors;
	}

	// return rider list
	public ArrayList<Integer> getRiders() {
		return riders;
	}
	
	// get floor stops
	public ArrayList<Integer> getFloorStops() {
		return floorStops;
	}
	
	// get rider stops
	public ArrayList<Integer> getRiderStops() {
		return riderStops;
	}

	// add a rider
	public void addRiders(int rider) {
		riders.add(rider);
	}

	// add to the floor stops
	public void addFloorStops(int floorId) {
		riderStops.add(floorId);
	}
	
	// add to the rider stops
	public void addRiderStops(int floorId) {
		riderStops.add(floorId);
	}
	
	
}
