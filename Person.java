


public class Person {

	private int pId; // person ID 
	private int floorStart; //where the person is
	private int floorEnd; //where they want to go
	enum location { FLOOR, ELEV};
	location loc;
	private double waitTime; //how long outside elevator, waiting
	private double rideTime; // how long in the elevator
	ElevatorController controller = ElevatorController.getInstance();
	
	//constructor
	public Person(int pId, int floorStart, int floorEnd) {
		// given in MAIN based on number of riders
		this.pId = pId; 
		this.floorStart = floorStart;
		this.floorEnd = floorEnd;
		loc = location.FLOOR;
	}
	
	// set methods
	public void setWaitTime(double waitTime){
		this.waitTime = waitTime;
	}
	
	public void setRideTime(double rideTime){
		this.rideTime = rideTime;
	}
	
	public int getFloorStart() {
		return floorStart;
	}

	public int getFloorEnd() {
		return floorEnd;
	}

	public double getWaitTime() {
		return waitTime;
	}

	public double getRideTime() {
		return rideTime;
	}

	// getters
	public int getPid(){
		return this.pId;
	}
	
	// returns true if this person is on an elevator
	public boolean isOnElevator() {
		if(loc == location.ELEV)
			return true;
		return false;
	}
	
	//Person enters an elevator
	public void enterElevator(Elevator e) {
		e.addRiders(pId);
		loc = location.ELEV;
		System.out.println("Person " + pId + " enters Elevator " + e.geteId() );
	}
	
	//Person exits the elevator
	public void exitElevator(Elevator e) {
		e.removeRiders(pId);
		loc = location.FLOOR;
		System.out.println("Person " + pId + " leaves Elevator " + e.geteId() + " and arrives at floor " + floorEnd);
	}
	
	
	
}
