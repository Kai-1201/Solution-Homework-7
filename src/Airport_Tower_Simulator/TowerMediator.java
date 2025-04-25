package Airport_Tower_Simulator;

public interface TowerMediator {
    void broadcast(String message, Aircraft sender);
    boolean requestLanding(Aircraft aircraft);
    boolean requestTakeoff(Aircraft aircraft);
    void runwayAvailable();
}