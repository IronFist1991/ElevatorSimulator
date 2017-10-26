package ElevatorSim;


import java.util.ArrayList;

public class Floor {
	
	private int floorId; // what floor number
	private ArrayList<Person> waitingPeople = new ArrayList<Person>(); // people waiting on that floor
	private ArrayList<Person> donePeople = new ArrayList<Person>(); // people who have gotten off on that floor
	
	//C'TOR
	public Floor(int floorId) {
		this.floorId = floorId;
	
	}
	
	//RETURN FLOOR #
	public int getFloorId(){
		return this.floorId;
	}
	
	//RETURNS LIST OF WAITING PEOPLE
	public ArrayList<Person> getWaitingPeople(){
		return waitingPeople;
	}
	
	//MODIFY LISTS
	public void addWaitingPeople(Person person){
		waitingPeople.add(person);
	}
	
	public void removeWaitingPeople(Person person){
		waitingPeople.remove(person);
	}
	
	public void addDonePeople(Person person){
		donePeople.add(person);
	}
	
	// get list of done people
	public ArrayList<Person> getDonePeople(){
		return donePeople;
	}
	
	// get the floors needed
	public void floorReport() {
		for (Person p: donePeople){
			p.personReport();
		}
	}
}
