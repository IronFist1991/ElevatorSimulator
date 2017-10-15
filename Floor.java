

import java.util.ArrayList;

public class Floor {
	
	private int floorId; // what floor number
	private ArrayList<Integer> waitingPeople = new ArrayList<Integer>(); // people waiting on that floor
	private ArrayList<Integer> donePeople = new ArrayList<Integer>(); // people who have gotten off on that floor
	Building b = Building.getInstance();
	
	//c'tor
	public Floor(int floorId) {
		this.floorId = floorId;
	
	}
	
	//get the floor number
	public int getFloorId(){
		return this.floorId;
	}
	
	//set lists
	public void addWaitingPeople(int person){
		waitingPeople.add(person);
	}
	
	public void removeWaitingPeople(int person){
		for(int r = 0; r < waitingPeople.size(); r++) {
			if (waitingPeople.get(r) == person)
					waitingPeople.remove(r);
		}
	}
	
	public void addDonePeople(int person){
		donePeople.add(person);
	}

	public ArrayList<Integer> getWaitingPeople() {
		return waitingPeople;
	}

	public ArrayList<Integer> getDonePeople() {
		return donePeople;
	}
	
	
	

}
