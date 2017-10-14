

import java.util.ArrayList;
import java.util.Random;
import static gui.ElevatorDisplay.Direction.DOWN;
import static gui.ElevatorDisplay.Direction.UP;
import static gui.ElevatorDisplay.Direction.IDLE;

import gui.ElevatorDisplay;

public class Building {
	private int numFloors; //num of floors in building
	private int numElevators; // num of elevators
	private ArrayList<Floor> floors = new ArrayList<Floor>();
	private ArrayList<Elevator> elevators = new ArrayList<Elevator>();
	Random rand = new Random();
	private int Id = 0;
	
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
		for (int i = 0; i < numElevators; i++){
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
	public synchronized void Step() {
		
		//Create People
		int floorStart = 0;
        int floorEnd = 0;
        while(floorStart == floorEnd) {
			floorStart = rand.nextInt(numFloors);
    		floorEnd = rand.nextInt(numFloors);
		}
        Person p = new Person(Id,floorStart,floorEnd);
        System.out.println("Person " + Id + " on floor " + floorStart + " wants to go to floor " + floorEnd );
        floors.get(floorStart).addWaitingPeople(Id);
        
        //Send signal to ElevatorController and retrieve appropriate elevatorID
        int elevId = p.sendOrigin();
        Elevator e = elevators.get(elevId);
        int curFloor = elevators.get(elevId).getCurrentFloor();
        int numRiders = elevators.get(elevId).getRiders().size();
        gui.ElevatorDisplay.Direction elevDirection;
        if(curFloor < floorStart) elevDirection = UP;
        else elevDirection = DOWN;
        //move the elevator
        try {
			e.moveElevator(curFloor, floorEnd);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
        Id++;
	}
}
