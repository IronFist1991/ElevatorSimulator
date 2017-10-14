import java.util.ArrayList;

public class SelectorFactory  {

	public static ElevatorSelector build() {
		return new ElevatorImpl();
		
	}
}
