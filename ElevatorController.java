import java.util.ArrayList;

public class ElevatorController implements ElevatorSelector {
	private static ElevatorController instance;		//singleton instance
	private ElevatorSelector delegate;				//delegate for controller
	
	private ArrayList<Integer> floorCalls = new ArrayList<>();		//List of floor requests
	
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
	
	public int pickElevator(int floor, boolean direction) {
		floorCalls.add(floor);
		int sendElev =delegate.pickElevator(floor, direction);
		return sendElev;
	}
	
	public void setFloorEnd (int floor) {
		//Floor end from person who is inside the elevator
	}
	
	
}
