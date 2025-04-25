package Airport_Tower_Simulator;

public class Helicopter extends Aircraft {
    public Helicopter(String id, int fuel) {
        super(id, fuel);
    }

    @Override
    public void receive(String message) {
        System.out.printf("[Helicopter %s] Received: %s\n", id, message);
        if (message.contains("MAYDAY")) status = "MAYDAY_ASSIST";
    }
}