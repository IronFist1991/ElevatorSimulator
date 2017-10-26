package ElevatorSim;
import gui.ElevatorDisplay;
import gui.ElevatorDisplay.Direction;


import java.util.ArrayList;
import java.util.Random;

public class Building {
	private int numFloors; //num of floors in building
	private int numElevators; // num of elevators
	private ArrayList<Floor> floors = new ArrayList<Floor>();
	private ArrayList<Elevator> elevators = new ArrayList<Elevator>();
	Random rand = new Random();
	//private ElevatorController controller = ElevatorController.getInstance();
	
	private static Building instance; //singleton pattern
	
	//C'TOR
	private Building(){}
	
	//making the singleton
	public static Building getInstance() {
		if (instance == null)
			instance = new Building();
		return instance;
	}
	
	public int getnumFloors(){
		return numFloors;
	}
	
	public int getnumElevators(){
		return numElevators;
	}
	
	//UPDATE ALL OF THE ELEVATORS AT THIS POINT IN TIME
	public void update(long time) {
		for(Elevator e : elevators) {
			e.update(time);
		}
	}
	
	//ADD PEOPLE TO DONE LIST
	public void addDonePeople(int floor, ArrayList<Person> People) {
		for(Person p : People) {
			floors.get(floor - 1).addDonePeople(p);
		}
	}
	
	//ADD PEOPLE TO WAITING LIST
	public void addWaitingPeople(int floor, Person p) {
		floors.get(floor - 1).addWaitingPeople(p);
		System.out.println(TimeManager.getInstance().getTimeString() + "Person P" + p.getPid() + 
				" presses " + p.getDir() + " button on Floor " + floor);
	}
	
	//REMOVE PEOPLE FROM WAITING LIST
	public void removeWaitingPeople(int floor, Person p) {
		floors.get(floor - 1).removeWaitingPeople(p);
	}
	
	//GET A LIST OF PEOPLE ON A FLOOR WHO ARE WAITING TO GO IN THE SPECIFIED DIRECTION
	public ArrayList<Person> getWaitingPeople(int floor, Direction dir) {
		ArrayList<Person> waitingPeople = new ArrayList<Person>();
		for(Person p : floors.get(floor - 1).getWaitingPeople()) {
			if(p.getDir() == dir)
				waitingPeople.add(p);
		}
		return waitingPeople;
	}
	
	
	//A FLOOR REQUEST IS MADE
	public void floorRequest(int elevator, int floor, Direction dir) {
		elevators.get(elevator-1).addFloorStops(floor, dir);
	}
	
	//CREATE OUR BUILDING
	public void buildBuilding(int numFloors, int numElevators, int capacity, long floorTime, long doorOpenTime, long idleTime){
		this.numFloors = numFloors;
		this.numElevators = numElevators;
    	
		// CREATE FLOORS
		for (int i = 1; i <= numFloors; i++) {
			floors.add(new Floor(i));
		}
		
		// CREATE ELEVATORS
		for (int i = 1; i <= numElevators; i++){
			elevators.add(new Elevator(i, capacity,floorTime,doorOpenTime,idleTime));
		}
		
		//INITIALIZE DISPLAY
		ElevatorDisplay.getInstance().initialize(numFloors);
		for(int i = 1; i <= numElevators; i++)
			ElevatorDisplay.getInstance().addElevator(i, 1);	
	}
	

	// needs to be in Building to access
	public void completeReport() {
		for (Floor f : floors) {
			f.floorReport();
		}
	}
}
