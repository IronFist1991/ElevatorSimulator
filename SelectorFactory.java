import java.util.ArrayList;

public class SelectorFactory  {

	public static ElevatorSelector build(ArrayList<Elevator> elevators) {
		return new ElevatorImpl();
		
	}
}
