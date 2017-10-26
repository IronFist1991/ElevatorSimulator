package ElevatorSim;
import gui.ElevatorDisplay.Direction;


public class Person {

	private int pId; // person ID 
	private int floorStart; //where the person is
	private int floorEnd; //where they want to go
	private double waitTime; //how long outside elevator, waiting
	private double rideTime; // how long in the elevator
	private Direction pDir; //direction person wants to go
	
	//C'TOR
	public Person(int pId, int floorStart, int floorEnd) {
		this.pId = pId; 
		this.floorStart = floorStart;
		this.floorEnd = floorEnd;
		if(floorStart > floorEnd) {
			pDir = Direction.DOWN;
		}
		else
			pDir = Direction.UP;
		waitTime = TimeManager.getInstance().getCurrentTime();
		System.out.println(TimeManager.getInstance().getTimeString() + "Person P" + pId + " created on Floor " + 
			floorStart + ", wants to go " + pDir + " to Floor " + floorEnd);
	}
	
	// SET METHODS
	public void setWaitTime(double waitTime){
		this.waitTime = waitTime;
	}
	
	public void setRideTime(double rideTime){
		this.rideTime = rideTime;
	}
	
	// GET METHODS
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

	public int getPid(){
		return pId;
	}
	
	public Direction getDir() {
		return pDir;
	}
	
	// person status
	public void personReport() {
		System.out.println("Person P"+ pId + " started on Floor " + floorStart + " and ended at Floor " 
				+ floorEnd + ". Ride time: " + rideTime + "ms Wait Time: " + waitTime + "ms");
	}
	
}
