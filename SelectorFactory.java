package ElevatorSim;
public class SelectorFactory  {

	public static ElevatorSelector build() {
		return new ElevatorImpl();
		
	}
}
