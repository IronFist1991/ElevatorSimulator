import java.util.ArrayList;

public class Building {
	private static Building instance;	//singleton instance
	private int numFloors;			//number of floors
	private int numElevators;			//number of elevators
	private ArrayList<Floor> floors = new ArrayList<Floor>();
	private ArrayList<Elevator> elevators = new ArrayList<Elevator>();
	private ElevatorController controller;
	
	//Building Constructor
	private Building(int numFloors, int numElevators ) {
		this.numFloors = numFloors;
		this.numElevators = numElevators;
		for(int i = 1; i <= numFloors; i++) {
			floors.add(new Floor(i));
		}
		for(int i = 1; i <= numElevators; i++) {
			elevators.add(new Elevator(i,20));
		}
		controller = ElevatorController.getInstance(0,5,10,20,5,elevators);
		
	}
	
	//Singleton Design method for Building
	public static Building getInstance(int floorNumber, int elevatorNumber) {
		if(instance == null) {
			instance = new Building(floorNumber, elevatorNumber);
		}
		return instance;
	}
	
	public ArrayList<Floor> getFloorList(){
		return floors;
	}
	
	public ArrayList<Elevator> getElevatorList(){
		return elevators;
	}

	public int getNumFloors() {
		return numFloors;
	}

	public int getNumElevators() {
		return numElevators;
	}
}
