# ElevatorSimulator

The quarter programming project is to design and implement an object-oriented elevator simulator. This simulator application will model a building, its floors, its elevators, elevator controller, its people, etc. in order to perform a variety of analyses that will help determine the optimal elevator configuration for a given building.

Simulation Description

Elevator floor-call (Up/Down):
 A person presses the Up or Down button from a specific floor (note – the first floor will not have a Down button and the top floo       will not have an Up button).

 The Up/Down request goes to the Elevator Controller whose job it is to determine which elevator should respond to the call. That might   be an idle elevator, or an elevator already in motion.

 The Elevator Controller sends the Up/Down floor request (1-Up, 5-Down, 3-Up, 10-Down, etc.) to one specific elevator (determined in     the previous step).

 When the elevator passes a floor in a specific direction (Up/Down), if the elevator has a request to stop at that floor in the current   direction (Up/Down), the elevator will stop and open the doors.

    o Any people that want to exit the elevator at that floor will move from the elevator to the floor.
    
    o Any people on that floor that want an elevator going in the same direction (Up/Down) move from the floor to the elevator.
   
Elevator floor number pressed (from inside the elevator):

 A person presses a floor number from in the elevator, and the floor request is added to the elevator’s list of floor stops.

 Elevator continues moving towards its next destination.

 When the elevator passes a floor in a specific direction (Up/Down), if the elevator has a request to stop at that floor in the current   direction (Up/Down), the elevator will stop and open the doors.

    o Any people that want to exit the elevator at that floor will move from the elevator to the floor.
    
    o Any people on that floor that want an elevator going in the same direction (Up/Down) move from the floor to the elevator.

Simulation Component Descriptions/Requirements

 Person

    o Persons are created and placed on a floor of the building.
    
    o Persons are created with a specific desired destination floor.
    
    o Persons call the elevator by sending a message to the elevator controller.
    
    o When an elevator arrives on the person’s floor that is going in their desired direction (Up/Down), the person should be moved from       the floor to the elevator, where they become elevator “riders”.
    
    o Once in the elevator, a person will select a destination floor number
    
    o They will ride the elevator to their destination, then exit the elevator onto their desired floor.
    
    o Each person will need to record their wait-time duration in seconds (time from requesting the elevator to the time they enter the       elevator)
    
    o Each person will need to record their ride-time duration in seconds (time from entering the elevator to the time they exit the           elevator).
    
 Elevator

    o Elevators travel up and down to the various floors of the building.
    
    o Elevators will hold Person objects. They will enter and exit the elevator.
    
    o Elevators have a maximum person count (i.e., 10 people)
    
    o Elevators have a direction (going up or going down – or Idle)
    
    o Elevators have a time in milliseconds per floor value (i.e., the speed of the elevator – for example, 1000 ms per floor). Note,         1000 ms is 1 second.
    
    o When arriving at a floor (to pick up or drop off riders), the elevator doors should open, then remain open for a configurable           number of milliseconds (i.e., 2000 ms), then the doors should close.
    
    o When an elevator stops at a floor, any person in the elevator who wants to get out at that floor (the current floor is their             desired floor) should be moved from the elevator, to the floor.
    
    o When an elevator stops at a floor, any person on that floor that wants an elevator that is going in the direction the elevator is       going (Up/Down) should be moved from the floor onto the elevator.
    
    o Elevators should maintain a list of the floors they need to visit, in the order that they should be visited in.
    
    o When an elevator remains idle (no riders, no floor requests) for a configurable amount of time (i.e., 20 seconds), it should             automatically return to the first floor.
    
 Floor

    o Each floor of the building holds a list of persons waiting for an elevator
    
    o Each floor of the building holds a list of persons that have completed their elevator trip. These persons are “done” – they will       no longer be used in the simulation.
    
 Building

    o The simulations performed with this application represent one building.
    
    o The building owns a certain number of floors and a certain number of elevators.
    
    o A building owns an “elevator controller” that coordinates the actions of all elevators.
    
 Elevator Controller

    o The elevator controller receives Up/Down elevator calls from the persons on a floor (i.e., Floor 12, to go Up, or Floor 10, to go       Down, etc.)
    
    o The elevator controller determines which elevator should respond to that Up/Down request.
    
    o The elevator controller will instruct individual elevators to go to certain floors to respond to elevator up/down requests.

Simulation Inputs

The following must be loaded into the simulation as input.

1. Number of Floors in Building- Example: 24, 5, 7, etc.

2. Number of Elevators in Building – Example: 4, 8, 1, etc.

3. Max Persons Per Elevator – Example 12, 8, 9, etc.

4. Time (in milliseconds) per floor value, which represents the speed of the elevator – Example: 1000 ms per floor.

5. Door-open-time (in milliseconds), which is how long the elevator door stays open when arriving at a requested floor – Example: 2200      ms, 3000 ms, etc.

6. Elevator timeout, which is the time an elevator will sir idle (no requests) before it automatically returns to the first floor –        Example: 30000 ms, 60000 ms, etc.
