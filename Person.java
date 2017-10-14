
import java.util.Random;
import java.time.LocalTime;

public class Person {

	private int pId; // person ID 
	private int floorStart; //where the person is
	private int floorEnd; //where they want to go
	enum location { FLOOR, ELEV};
	location loc;
	private double waitTime; //how long outside elevator, waiting
	private double rideTime; // how long in the elevator
	private String wantToGo; // which direction
	private boolean direction; //direction true = up, false = down 
	ElevatorController controller = ElevatorController.getInstance();
	
	Random rand = new Random();
	
	//constructor
	public Person(int pId, int floorStart, int floorEnd) {
		// given in MAIN based on number of riders
		this.pId = pId; 
		this.floorStart = floorStart;
		this.floorEnd = floorEnd;
		loc = location.FLOOR;
		placeOnFloor(floorStart,pId);
		
		// for text output
		if (floorStart < floorEnd) 
			wantToGo = "UP";
		else wantToGo = "DOWN";	
		// tell elevator controller
		if (wantToGo.equals("UP"))
			direction = true;
		else direction = false;
		
		/*/not sure. wanted to say if person_floor = elevator_floor but go thru controller?
		if (ElevatorController.getInstance().isPersonOnElevator()){
		sendDestination(floorStart, floorEnd);
			// if this is true, the person has entered the elevator and we can start time*/
	}
		
	
	//Add person to elevator waiting list
	public void placeOnFloor(int floorNumber, int pId) {
		
	}
	
	//direction true = up, false = down
	public int sendOrigin() {
		// send to elevator controller
		return ElevatorController.getInstance().pickElevator(floorStart, direction);
	}
	
	public void sendDestination(){
		ElevatorController.getInstance().setFloorEnd(floorEnd);
		System.out.println(LocalTime.now() + " Person " + pId + " presses " + wantToGo + " on " + floorStart);

	}
	
	// set methods
	public void setWaitTime(int waitTime){
		this.waitTime = waitTime;
	}
	
	public void setRideTime(int rideTime){
		this.rideTime = rideTime;
	}
	
	public int getPid(){
		return this.pId;
	}
	
	
	
}
