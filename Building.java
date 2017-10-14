

import java.util.Random;

public class Building {
	private int numFloors; //num of floors in building
	private int numElevators; // num of elevators
	Random rand = new Random();
	
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
	
	//Simulation Start
	public void buildBuilding(int numFloors, int numElevators, int capacity, long floorTime, long doorOpenTime, long idleTime){
		this.numFloors = numFloors;
		this.numElevators = numElevators;
		int newPeople = (int) (11.0 * Math.random()) + 1;
        int floorStart = 0;
        int floorEnd = 0;
    	
		// Create Floors, gotta do other stuff
		for (int i = 1; i < numFloors; i++) {
			Floor F = new Floor(i);
		}
		
		// Create Elevators, gotta do other stuff
		for (int i = 0; i < numElevators; i++){
			Elevator E = new Elevator(i, capacity,floorTime,doorOpenTime,idleTime);
		}
		
		// create person objects
    	for (int i = 0; i < newPeople; i ++) {
    		while(floorStart == floorEnd) {
    			floorStart = rand.nextInt(numFloors);
        		floorEnd = rand.nextInt(numFloors);
    		}
    		Person p = new Person(i,floorStart,floorEnd);
    	}
	}
}
