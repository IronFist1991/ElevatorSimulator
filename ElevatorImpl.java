import java.util.ArrayList;
import static gui.ElevatorDisplay.Direction.DOWN;
import static gui.ElevatorDisplay.Direction.UP;
import static gui.ElevatorDisplay.Direction.IDLE;

public class ElevatorImpl implements ElevatorSelector {
	
	
	
	
	

	ElevatorImpl(){}

	@Override
	// Implementation to choose which elevator goes to specified floor
	public int pickElevator(ArrayList<Elevator> elevators,Floor floor) {
		// TODO Auto-generated method stub
		int elevNum = 1;
		for(Elevator e : elevators) {
			if(e.isFull()) continue;
			else if(e.getState() == IDLE && e.getCurrentFloor() == floor.getFloorId()) {
				elevNum = e.geteId();
			}
			else if (e.getState() == UP && e.getCurrentFloor() < floor.getFloorId()) {
				elevNum = e.geteId();
			}
			else if (e.getState() == DOWN && e.getCurrentFloor() > floor.getFloorId()){
				elevNum = e.geteId();
			}
			else if(e.getState() == IDLE)
				elevNum = e.geteId();
		}
		return elevNum;
	}
}
