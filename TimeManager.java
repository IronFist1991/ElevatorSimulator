package ElevatorSim;
import gui.ElevatorDisplay.Direction;

public class TimeManager {
	
	private static TimeManager instance;
	private long currentTimeMillis = 0;
	
	private TimeManager() {}
	
	public static TimeManager getInstance() {
		if(instance == null)
			instance = new TimeManager();
		return instance;
	}
	
	//TIME STRING
	public String getTimeString() {
		long seconds = currentTimeMillis/1000;
		long hours = seconds / 3600;
		seconds -= hours * 3600;
		long minutes = seconds/60;
		seconds -= minutes * 60;
		return String.format("%d:%02d:%02d ", hours, minutes, seconds);
	}
	
	//RUN SIMULATION
	public void run(int test, long duration) {
		while(currentTimeMillis <= duration) {
			
			System.out.println("\nTIME: " + getTimeString());

				if (test == 3) {
					Test3();
				} else if (test == 4) {
					Test4();
				} else
					Test1();
			
			Building.getInstance().update(1000);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			currentTimeMillis += 1000;
		}
		
		// print some data about the rides
		Building.getInstance().completeReport();
		System.exit(0);
	}
	
	//MANUALLY CREATE PEOPLE AT SPECIFIC POINTS IN TIME
	private void Test1(){
		if (currentTimeMillis == 0) {
			System.out.println("Starting Test 1\n");
			Person p = new Person(1,1,10); //pId, floor start, floor end
			Building.getInstance().addWaitingPeople(1, p); //floor number, person
			Building.getInstance().floorRequest(1, 1, Direction.UP); //which elevator, floor to go to,  direction
		}
	}
	
	private void Test3() {
		if (currentTimeMillis == 0) {
			System.out.println("Starting Test 3\n");
			Person p2 = new Person (2,1,20);
			Building.getInstance().addWaitingPeople(1, p2);
			Building.getInstance().floorRequest(2, 1, Direction.UP);
		}
		
		if (currentTimeMillis == 5000){
			Person p3 = new Person (3,1,10);
			Building.getInstance().addWaitingPeople(1, p3);
			Building.getInstance().floorRequest(3, 1, Direction.UP);
		}
	}
	
	private void Test4(){
		if (currentTimeMillis == 0) {
			System.out.println("Starting Test 4\n");
			Person p1 = new Person (1,1,20);
			Building.getInstance().addWaitingPeople(1, p1);
			Building.getInstance().floorRequest(1, 1, Direction.UP);
		}
		if (currentTimeMillis == 3000){
			Person p2 = new Person (2,1,20);
			Building.getInstance().addWaitingPeople(1, p2);
			Building.getInstance().floorRequest(2, 1, Direction.UP);
		}
		
		if (currentTimeMillis == 5000){
			Person p3 = new Person (3,1,20);
			Building.getInstance().addWaitingPeople(1, p3);
			Building.getInstance().floorRequest(3, 1, Direction.UP);
		}
		
		if (currentTimeMillis == 7000){
			Person p4 = new Person (4,1,20);
			Building.getInstance().addWaitingPeople(1, p4);
			Building.getInstance().floorRequest(4, 1, Direction.UP);
		}
		
		//Having trouble making elevator 1 go back down to pick up Person 5. Will have to continue working on this step.
		if (currentTimeMillis == 9000){
			Person p5 = new Person (5,1,10);
			Building.getInstance().addWaitingPeople(1, p5);
			Building.getInstance().floorRequest(1, 1, Direction.UP);
		}
	}
	
	//RETURNS CURRENT TIME
	public long getCurrentTime() {
		return currentTimeMillis;
	}


}
