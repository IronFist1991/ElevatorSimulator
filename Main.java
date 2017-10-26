package ElevatorSim;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 *  William Chirciu , Amy Edwards
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
    	final long inputDuration;
    	
    	File f = new File(".");
    	System.out.println(f.getAbsolutePath());
    	
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
        inputDuration = (long) jsonObject.get("duration_ms");
        
       //Build Building
        Building.getInstance().buildBuilding(inputFloor, inputElevator, inputCapacity, inputFloorTime, inputDoorTime, inputIdleTime);
        
       //Start Simulation
        System.out.println("Please choose which test you would like to run");
        System.out.println(" (1) for Test 1 \n (3) for Test 3 \n (4) for Test 4");


        Scanner scan = new Scanner(System.in);
        int test = -1;
        while(test < 1 || test == 2 || test > 4) {
        	test = scan.nextInt();
        	if(test < 1 || test == 2 || test > 4)
        		System.out.println("The number you have entered is invalid. Please try again.");
        }
        scan.close();
        TimeManager.getInstance().run(test,inputDuration);
        
    }
}
