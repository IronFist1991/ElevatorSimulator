
public class Person {

	private int pID; 			//person ID
	private int floorStart; 	// origin
	private int floorEnd;   	// destination
	private double waitTime; 	// wait time
	private double rideTime;	// ride time
	
	//Person Constructor
	public Person(int pID, int floorStart, int floorEnd) {
		this.pID = pID;
		this.floorStart = floorStart;
		this.floorEnd = floorEnd;
		waitTime = 0.0;
		rideTime = 0.0;
	}
	
	//returns the value of Pid for this object
	public int getpID() {
		return pID;
	}
	
	//direction true=up, false = down
	public void sendOrigin(int floorStart, boolean direction) {
		
	}
	
	public void sendDestination(int floorEnd) {
		
	}
	
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	public void setRideTime(int rideTime) {
		this.rideTime = rideTime;
	}
	
	
}
