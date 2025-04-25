package Airport_Tower_Simulator;

public class PassengerPlane extends Aircraft {
    public PassengerPlane(String id, int fuel) {
        super(id, fuel);
    }

    @Override
    public void receive(String message) {
        System.out.printf("[Passenger Plane %s] Received: %s\n", id, message);
        if (message.contains("hold")) status = "HOLDING";
        if (message.contains("cleared")) status = message.contains("land") ? "LANDING" : "TAKEOFF";
    }
}