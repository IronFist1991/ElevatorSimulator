import java.util.ArrayList;

public class ElevatorController implements ElevatorSelector {
	private static ElevatorController instance;		//singleton instance
	private int clock;								//running clock
	private int speed;								//elevator speed
	private int maxOccupancy;						//max occupancy
	private int idleTime;							//elevator idle time
	private int doorTime;							//elevator door open time
	private ElevatorSelector delegate;				//delegate for controller
	
	private ArrayList<Integer> floorCalls = new ArrayList<>();		//List of floor requests
	private ArrayList<Elevator> elevators = new ArrayList<>();		//List of elevators
	
	//ElevatorController constructor
	private ElevatorController(int clock, int speed, int maxOccupancy, int idleTime, int doorTime, ArrayList<Elevator> elevators){
		this.clock = clock;
		this.speed = speed;
		this.maxOccupancy = maxOccupancy;
		this.idleTime = idleTime;
		this.doorTime = doorTime;
		this.elevators = elevators;
		delegate = SelectorFactory.build(elevators);
	}
	
	//Singleton Design method for ElevatorController
	public static ElevatorController getInstance(int clock, int speed, int maxOccupancy, int idleTime, int doorTime, ArrayList<Elevator> elevators) {
		if (instance == null)
				instance = new ElevatorController(clock,speed,maxOccupancy,idleTime,doorTime, elevators);
		return instance;
	}
	
	public int pickElevator(int floor, boolean direction) {
		return delegate.pickElevator(floor, direction);
	}
	
	
}
