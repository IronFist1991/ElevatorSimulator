package ElevatorSim;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import gui.ElevatorDisplay;
import gui.ElevatorDisplay.Direction;
public class Elevator {
	
	private int eId; //elevator ID
	private double currentFloor; // floor the elev is on
	private int capacity; // how many people can fit in the elev
	private long floorTime; // time per floor
	private long doorOpenTime; // time doors stay open
	private long idleTime; // time elev sits idle before returning to floor 1
	private long elevTime; //used locally for time
	private Direction direction; //state of the elevator
	private Direction pendingDirection; //direction the elevator will eventually go
	private boolean doors; // true = open, false = close
	
	private HashMap<Integer, ArrayList<Person>> riders = new HashMap(); //People on the elevator
	private HashMap<Direction, ArrayList<Integer>> floorStops = new HashMap(); //Hashmap of floor requests
	
	//C'TOR
	public Elevator(int eId, int capacity, long floorTime, long doorOpenTime, long idleTime){
		this.eId = eId;
		this.currentFloor = 1;
		this.capacity = capacity;
		this.floorTime = floorTime;
		this.doorOpenTime = doorOpenTime;
		this.idleTime = idleTime;
		doors = false;
		direction = Direction.IDLE;
	}
	
	//UPDATE THIS ELEVATOR
	public void update(long time) {
		moveElevator(time);
		
		// if the elev has no floor stops
		if(!floorStops.isEmpty()) {
			floorStops.get(pendingDirection).remove(new Integer((int) currentFloor));
			if(floorStops.get(pendingDirection).isEmpty()) {
				floorStops.remove(pendingDirection);
			}
			direction = pendingDirection;
			pendingDirection = direction;
			processFloor();
		}
		// if there are no riders
		else if(!riders.isEmpty()) {
			elevTime = TimeManager.getInstance().getCurrentTime();
			processFloor();
		}
		// if the elev is at ground floor
		else if(currentFloor == 1) {
			direction = Direction.IDLE;
		}
		// if we are not at 1 and the elev is idle, go to floor 1
		if(currentFloor > 1 && (TimeManager.getInstance().getCurrentTime() - elevTime) >= idleTime && direction == Direction.IDLE) {
			resetElevator(time);
		}
		
	}
	
	//ELEVATOR STOPS AT A FLOOR
	private void processFloor() {
		long pre = System.currentTimeMillis();
		ArrayList<Person> waitingPeople = Building.getInstance().getWaitingPeople((int)currentFloor, direction);
		for(Person p: waitingPeople) {
			// if there is a waiting person, we pick them up
			if(!doors) {
				System.out.println(TimeManager.getInstance().getTimeString() + "Elevator " + eId + " has arrived at Floor " + (int) currentFloor + " for Floor Request [Current Floor Requests: " 
						+ displayFloorStops() + "][Current Rider Requests: " + displayRiders() + "]");
				open();
			}
			if(!isFull()) {
				addPerson(p);
				p.setRideTime(TimeManager.getInstance().getCurrentTime());
			}
		}
		ArrayList<Person> gettingOff = new ArrayList<>();
		// let people off when we arrive at their requested floor
		if(riders.containsKey((int) currentFloor)){
			for(Person p : riders.get((int)currentFloor)) {
				if(!doors){
					System.out.println(TimeManager.getInstance().getTimeString() + "Elevator " + eId + " has arrived at Floor " + (int) currentFloor + " for Floor Request [Current Floor Requests: " 
							+ displayFloorStops() + "][Current Rider Requests: " + displayRiders() + "]");
					open();
				}
				gettingOff.add(p);
				System.out.println(TimeManager.getInstance().getTimeString() + "Person P" + p.getPid() + " has left Elevator " + eId + "[Riders: " 
						+ displayRiders() + "]");
				p.setRideTime(TimeManager.getInstance().getCurrentTime() - p.getRideTime());
			}
			riders.remove(new Integer((int)currentFloor));
			Building.getInstance().addDonePeople((int)currentFloor, gettingOff);
		}
		// no riders and no stops
		if(riders.isEmpty() && floorStops.isEmpty()) {
			direction = Direction.IDLE;
			ElevatorDisplay.getInstance().updateElevator(eId, (int) currentFloor, 0, Direction.IDLE);
		}
		long post = System.currentTimeMillis();
		try {
			Thread.sleep(doorOpenTime -(post-pre));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(doors) {
			close();
		}
	}
	
	//PERSON ENTERS THE ELEVATOR
	private void addPerson(Person p) {

		if(!riders.containsKey(p.getFloorEnd())) {
			riders.put(p.getFloorEnd(), new ArrayList<Person>());
		}
		riders.get(p.getFloorEnd()).add(p);
		System.out.println(TimeManager.getInstance().getTimeString() + "Person P" + p.getPid() + " entered Elevator " + eId + "[Riders: " 
				+ displayRiders() + "]");
		if(direction == Direction.IDLE) {
			direction = p.getDir();
			System.out.println("Elevator Direction set to " + direction);
		}
		Building.getInstance().removeWaitingPeople(p.getFloorStart(), p);
		p.setWaitTime(TimeManager.getInstance().getCurrentTime() - p.getWaitTime());
		System.out.println(TimeManager.getInstance().getTimeString() + "Elevator " + eId + " Rider Request made for Floor " + p.getFloorEnd() + " [Current Floor Requests: " 
				+ displayFloorStops() + "][Current Rider Requests: " + displayRiders() + "]");
	}
	

	//GETTERS
	public int getEid() {
		return eId;
	}
	
	public Direction getDirection() {
		return direction;
	}

	// DOORS OPEN OR CLOSED?
	public boolean isDoors() {
		return doors;
	}
	
	// RETURNS TRUE IF CAPACITY HAS BEEN REACHED
	public boolean isFull() {
		if(riders.size() == this.capacity)
			return true;
		return false;
	}
	
	//RETURN DIRECTION FROM CURRENT FLOOR TO DESTINATION FLOOR
	public Direction getDirTo(int floor) {
		if(currentFloor < floor)
			return Direction.UP;
		else if(currentFloor > floor)
			return Direction.DOWN;
		return null;
	}
		
	//OPEN DOORS
	private void open() {
		System.out.println(TimeManager.getInstance().getTimeString() + "Elevator " + eId + " Doors Open");
		ElevatorDisplay.getInstance().openDoors(eId);
		doors = true;
	}
		
	//CLOSE DOORS
	private void close() {
		System.out.println(TimeManager.getInstance().getTimeString() + "Elevator " + eId + " Doors Close");
		ElevatorDisplay.getInstance().closeDoors(eId);
		doors = false;
	}
	
	//ADD FLOOR REQUESTS
	public void addFloorStops(int floor, Direction dir) {
		if(!floorStops.containsKey(dir)) {
			floorStops.put(dir, new ArrayList<Integer>());
		}
		if(floor == currentFloor && !floorStops.get(dir).contains(floor)) {
			floorStops.get(dir).add(floor);
			pendingDirection = dir;
			System.out.println(TimeManager.getInstance().getTimeString() + "Elevator " + eId + " is going to Floor " + floor + " for " + dir + " request. [Current Floor Requests: " 
					+ displayFloorStops() + "][Current Rider Requests: " + displayRiders() + "]");
			return;
		}
		if(direction == Direction.IDLE) {
			getDirTo(floor);
			pendingDirection = dir;
		}
		if(direction == Direction.UP && getDirTo(floor) != Direction.UP) {
			//Here is where in Test4, Person 5's request to go up from floor1 should be taken into account by
			//elevator 1. However, we have not found this solution just yet.
		}
		if(direction == Direction.DOWN && getDirTo(floor) != Direction.DOWN) {
			System.exit(-1);
		}
	}
	
	
	
	//MOVES THIS ELEVATOR
	private void moveElevator(long time){
		if(direction != Direction.IDLE) {
			double move = time/floorTime;
			if(direction == Direction.UP) {
				System.out.println(TimeManager.getInstance().getTimeString() + "Elevator " + eId + " is moving from Floor " + (int) currentFloor + " to Floor " + (int)(currentFloor + 1) + " [Current Floor Requests: " 
						+ displayFloorStops() + "][Current Rider Requests: " + displayRiders() + "]");
				currentFloor += move;
			}
			else {
				System.out.println(TimeManager.getInstance().getTimeString() + "Elevator " + eId + " is moving from Floor " + (int) currentFloor + " to Floor " + (int)(currentFloor - 1) + " [Current Floor Requests: " 
						+ displayFloorStops() + "][Current Rider Requests: " + displayRiders() + "]");
				currentFloor -= move;
			}
			ElevatorDisplay.getInstance().updateElevator(eId, (int) currentFloor, riders.size(), direction);
		}
    	
    }
	
	//SEND ELEVATOR BACK TO FLOOR 1 AFTER TIMEOUT PERIOD
	private void resetElevator(long time) {
		direction = Direction.DOWN;
		System.out.println(TimeManager.getInstance().getTimeString() + "Elevator " + eId + 
				" returning to first floor." );
	}
	
	//PRINTS RIDER STOPS
	public String displayRiders(){
		String toPrint = "";
		for (Entry<Integer, ArrayList<Person>> entry : riders.entrySet()) {
	        for(Person people : entry.getValue()){
	            toPrint += "P" + people.getPid() + ", " ;
	        }
	    } 
	    return toPrint;
	}

	//PRINTS FLOORSTOPS
	public String displayFloorStops(){
		String toPrint = "";
		for (Entry<Direction, ArrayList<Integer>> entry : floorStops.entrySet()) {
	        for(int f : entry.getValue()){
	            toPrint += f + ", " ;
	        }
	    }
	    return toPrint;
	}
	
	
	

}
