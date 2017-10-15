import java.util.ArrayList;

public class ElevatorController implements ElevatorSelector {
	private static ElevatorController instance;		//singleton instance
	private ElevatorSelector delegate;				//delegate for controller
	
	//ElevatorController constructor
	private ElevatorController(){
		delegate = SelectorFactory.build();
	}
	
	//Singleton Design method for ElevatorController
	public static ElevatorController getInstance() {
		if (instance == null)
				instance = new ElevatorController();
		return instance;
	}
	
	//Signal received from floor
	public void floorSignal(ArrayList<Elevator> elevators, Floor floor, Person p) {
		//Choose an Elevator
		int sendElev = pickElevator(elevators,floor);
		double pre = System.currentTimeMillis();
		//Move Elevator to pick up Person
		Elevator e = elevators.get(sendElev - 1);
		e.addFloorStops(floor.getFloorId());
		try {
			e.moveElevator(e.getCurrentFloor(), floor.getFloorId());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		e.removeFloorStops(floor.getFloorId());
		//Person enters Elevator
		floor.removeWaitingPeople(p.getPid() - 1);
		p.enterElevator(e);
		double post = System.currentTimeMillis();
		p.setWaitTime(post - pre);
		elevSignal(e, p);
	}
	
	//signal received from elevator
	public void elevSignal(Elevator e, Person p) {
		e.addRiderStops(p.getFloorEnd());
		double pre = System.currentTimeMillis();
		//Move elevator to drop off person
		try {
			e.moveElevator(e.getCurrentFloor(), p.getFloorEnd());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		e.removeRiderStops(p.getFloorEnd());
		//person exits the elevator
		p.exitElevator(e);
		double post = System.currentTimeMillis();
		p.setRideTime(post - pre);
		
	}
	
	public int pickElevator(ArrayList<Elevator> elevators,Floor floor) {
		int sendElev =delegate.pickElevator(elevators,floor);
		return sendElev;
		
	}
	
	public void setFloorEnd (int floor) {
		//Floor end from person who is inside the elevator
	}
	
	
}
