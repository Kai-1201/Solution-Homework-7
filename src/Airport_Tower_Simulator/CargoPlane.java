package Airport_Tower_Simulator;

public class CargoPlane extends Aircraft {
    public CargoPlane(String id, int fuel) {
        super(id, fuel);
    }

    @Override
    public void receive(String message) {
        System.out.printf("[Cargo Plane %s] Received: %s\n", id, message);
        if (message.contains("emergency")) status = "EMERGENCY_HOLD";
    }
}