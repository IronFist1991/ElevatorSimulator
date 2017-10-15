

import java.util.ArrayList;
import java.util.Random;

public class Building {
	private int numFloors; //num of floors in building
	private int numElevators; // num of elevators
	private ArrayList<Floor> floors = new ArrayList<Floor>();
	private ArrayList<Elevator> elevators = new ArrayList<Elevator>();
	Random rand = new Random();
	private int Id = 1;
	private ElevatorController controller = ElevatorController.getInstance();
	
	private static Building instance; //singleton pattern
	
	//constructor
	private Building(){}
	
	//making the singleton
	public static Building getInstance() {
		if (instance == null)
			instance = new Building();
		return instance;
	}
	
	public int getnumFloors(){
		return this.numFloors;
	}
	
	public int getnumElevators(){
		return this.numElevators;
	}
	
	//Create our Building
	public void buildBuilding(int numFloors, int numElevators, int capacity, long floorTime, long doorOpenTime, long idleTime){
		this.numFloors = numFloors;
		this.numElevators = numElevators;
		//int newPeople = (int) (11.0 * Math.random()) + 1;
    	
		// Create Floors, gotta do other stuff
		for (int i = 1; i < numFloors; i++) {
			floors.add(new Floor(i));
		}
		
		// Create Elevators, gotta do other stuff
		for (int i = 1; i < numElevators; i++){
			elevators.add(new Elevator(i, capacity,floorTime,doorOpenTime,idleTime));
		}
		
		Run();
		
	}
	
	//Simulation Start
	private void Run()  {
		for(int i = 0; i < 1000; i++) {
			Step();
			i++;
		}
	}
	//One step in the simulation
	public void Step() {
		
		//Create People
		int floorStart = -1;
        int floorEnd = -1;
        while(floorStart == floorEnd || floorStart <= 0 || floorEnd <= 0  ) {
			floorStart = rand.nextInt(numFloors);
    		floorEnd = rand.nextInt(numFloors);
		}
        Person p = new Person(Id,floorStart,floorEnd);
        System.out.println("Person " + Id + " on floor " + floorStart + " wants to go to floor " + floorEnd );
        Id = Id + 1;
        //add to list of waiting people
        floors.get(floorStart - 1).addWaitingPeople(Id);
        //Person presses call button on their floor
        controller.floorSignal(elevators,floors.get(floorStart -1),p);
	}
}
