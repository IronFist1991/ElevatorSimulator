import java.util.ArrayList;

public class Floor {
	private int floorID; //which floor number
	private ArrayList<Integer> waitingPeople = new ArrayList<Integer>();
	private ArrayList<Integer> donePeople = new ArrayList<Integer>();
	
	//constructor
	public Floor(int floorID) {
		this.floorID = floorID;
	}
	
	//get the floor number
	public int getFloorID() {
		return floorID;
	}
	
	public void addWaitingPeople(int person) {
		waitingPeople.add(person);
	}
	
	public void removeWaitingPeople(int person) {
		waitingPeople.remove(person);
	}
	
	public void addDonePeople(int person) {
		donePeople.add(person);
	}
}
