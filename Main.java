/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import static gui.ElevatorDisplay.Direction.DOWN;
import static gui.ElevatorDisplay.Direction.UP;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import gui.ElevatorDisplay;

/**
 *
 * @author chield
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, org.json.simple.parser.ParseException, FileNotFoundException, IOException {
    	JSONParser parser = new JSONParser();
    	final int inputFloor;
    	final int inputElevator;
    	final int inputCapacity;
    	final long inputFloorTime;
    	final long inputDoorTime;
    	final long inputIdleTime;
    
    	Object obj = parser.parse(new FileReader(Main.class.getClassLoader()
               .getResource("input.json").getPath()
               .replaceAll("%20", " ")));
    	JSONObject jsonObject = (JSONObject) obj;
        System.out.println(jsonObject);

        inputFloor = (int) (long)jsonObject.get("floors");
        inputElevator = (int) (long)jsonObject.get("elevators");
        inputCapacity = (int) (long)jsonObject.get("max_persons");
        inputFloorTime = (long) jsonObject.get("time_per_floor_ms");
        inputDoorTime = (long) jsonObject.get("door_time_ms");
        inputIdleTime = (long) jsonObject.get("idle_time_ms");
        
     // Initialize the elevator display
        ElevatorDisplay.getInstance().initialize(inputFloor);
        for (int i = 1; i <= inputElevator; i++) {
            ElevatorDisplay.getInstance().addElevator(i, 1);
        }
        
        //Create Building
        Building b = Building.getInstance();
        b.buildBuilding(inputFloor, inputElevator, inputCapacity, inputFloorTime, inputDoorTime, inputIdleTime);
    	

        /*/ Go up
        for (int j = 1; j <= numElev; j++) {
            moveElevator(j, 1, numFloor);
            ElevatorDisplay.getInstance().setIdle(j);
        }
        Thread.sleep(1000);

        // Go down
        for (int j = 1; j <= numElev; j++) {
            moveElevator(j, numFloor, 1);
            ElevatorDisplay.getInstance().setIdle(j);
        }
        Thread.sleep(1000);

        // Go up halfway
        moveElevator(1, 1, numFloor / 2);
        Thread.sleep(1000);

        // Go up remaining floors
        moveElevator(1, numFloor / 2, numFloor);
        Thread.sleep(1000);

        for (int i = numFloor; i > 1; i--) {
            moveElevator(1, i, i - 1);
            Thread.sleep(500);
        }
        ElevatorDisplay.getInstance().setIdle(1);
        Thread.sleep(1000);

        ElevatorDisplay.getInstance().shutdown(); */
    }

    private static void moveElevator(int elevNum, int numRiders, int fromFloor, int toFloor) throws InterruptedException {
    	
    	
    	ElevatorDisplay.getInstance().closeDoors(elevNum);
        if (fromFloor < toFloor) {
            for (int i = fromFloor; i <= toFloor; i++) {
                ElevatorDisplay.getInstance().updateElevator(elevNum, i, numRiders, UP);
                Thread.sleep(80);
            }
        } else {
            for (int i = fromFloor; i >= toFloor; i--) {
                ElevatorDisplay.getInstance().updateElevator(elevNum, i, numRiders, DOWN);
                Thread.sleep(80);
            }
        }
        ElevatorDisplay.getInstance().openDoors(elevNum);
    }
}
