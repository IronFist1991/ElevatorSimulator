
import static gui.ElevatorDisplay.Direction.DOWN;
import static gui.ElevatorDisplay.Direction.UP;

import java.util.ArrayList;

import gui.ElevatorDisplay;
import gui.ElevatorDisplay.Direction;

public class Elevator {
	
	private int eId; //elevator ID
	private int currentFloor; // floor the elev is on
	private int capacity; // how many people can fit in the elev
	private long floorTime;
	private long doorOpenTime;
	private long idleTime;
	private gui.ElevatorDisplay.Direction state; //state of the elevator
	private boolean doors; // true = open, false = close
	
	private ArrayList<Integer> riders = new ArrayList<Integer>(); // people on elevator
	private ArrayList<Integer> floorStops = new ArrayList<Integer>(); // stops the elevator has to make as given by people pressing buttons
	private ArrayList<Integer> riderStops = new ArrayList<Integer>(); // stops the elevator has to make because the people on the elevator want to stop
	Building b = Building.getInstance();
	
	//constructor
	public Elevator(int eId, int capacity, long floorTime, long doorOpenTime, long idleTime){
		this.eId = eId;
		this.currentFloor = 1;
		this.capacity = capacity;
		this.floorTime = floorTime;
		this.doorOpenTime = doorOpenTime;
		this.idleTime = idleTime;
		doors = false;
		state = Direction.IDLE;
	}
	

	//return eId
	public int geteId() {
		return eId;
	}
	
	//return elevator state
	public gui.ElevatorDisplay.Direction getState() {
		return state;
	}

	// get current floor number
	public int getCurrentFloor() {
		return currentFloor;
	}

	// are the doors open or closed
	public boolean isDoors() {
		return doors;
	}
	
	public boolean isFull() {
		if(riders.size() == this.capacity)
			return true;
		return false;
	}

	// set doors to open or close
	public void setDoors(boolean doors) {
		this.doors = doors;
	}

	// return rider list
	public ArrayList<Integer> getRiders() {
		return riders;
	}
	
	// get floor stops
	public ArrayList<Integer> getFloorStops() {
		return floorStops;
	}
	
	// get rider stops
	public ArrayList<Integer> getRiderStops() {
		return riderStops;
	}

	// add a rider
	public void addRiders(int rider) {
		riders.add(rider);
	}
	
	// remove a rider
	public void removeRiders(int rider) {
		for(int r = 0; r < riders.size(); r++) {
			if (riders.get(r) == rider)
					riders.remove(r);
		}
	}

	// add to the floor stops
	public void addFloorStops(int floorId) {
		riderStops.add(floorId);
	}
	
	// remove to the floor stops
	public void removeFloorStops(int floorId) {
		for(int r = 0; r < floorStops.size(); r++) {
			if (floorStops.get(r) == floorId)
					floorStops.remove(r);
		}
	}
	
	// add to the rider stops
	public void addRiderStops(int floorId) {
		riderStops.add(floorId);
	}
	
	// remove to the rider stops
	public void removeRiderStops(int floorId) {
		for(int r = 0; r < riderStops.size(); r++) {
			if (riderStops.get(r) == floorId)
					riderStops.remove(r);
		}
	}
	
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	
	//if elevator is idle, send back to floor 1
	public void setIdle() {
		try {
			moveElevator(currentFloor, 1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Moves this elevator
	public void moveElevator(int fromFloor, int toFloor) throws InterruptedException {
    	int numRiders = riders.size();
    	ElevatorDisplay.getInstance().closeDoors(eId);
    	doors = false;
        if (fromFloor < toFloor) {
            for (int i = fromFloor; i <= toFloor; i++) {
            	setCurrentFloor(i);
                ElevatorDisplay.getInstance().updateElevator(eId, i, numRiders, UP);
                state = UP;
                Thread.sleep(floorTime);
            }
        } else {
            for (int i = fromFloor; i >= toFloor; i--) {
            	setCurrentFloor(i);
                ElevatorDisplay.getInstance().updateElevator(eId, i, numRiders, DOWN);
                state = DOWN;
                Thread.sleep(floorTime);
            }
        }
        ElevatorDisplay.getInstance().openDoors(eId);
        doors = true;
        Thread.sleep(doorOpenTime);
    }
	
	
}
