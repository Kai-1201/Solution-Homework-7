package Airport_Tower_Simulator;

import java.util.*;


public class ControlTower implements TowerMediator {
    private final Deque<Aircraft> landingQueue = new LinkedList<>();
    private final Queue<Aircraft> takeoffQueue = new LinkedList<>();
    private boolean runwayOccupied = false;
    private Aircraft currentOnRunway;

    @Override
    public synchronized void broadcast(String message, Aircraft sender) {
        if ("MAYDAY".equals(message)) {
            return;
        }
        System.out.printf("[Tower] Broadcast: %s from %s\n", message, sender.id);
    }

    @Override
    public synchronized boolean requestLanding(Aircraft aircraft) {
        if ("MAYDAY".equals(aircraft.status)) {
            landingQueue.addFirst(aircraft);
            System.out.printf("[Tower] EMERGENCY! %s prioritized (Fuel: %d)\n",
                    aircraft.id, aircraft.fuel);
            return true;
        }
        landingQueue.addLast(aircraft);
        System.out.printf("[Tower] %s queued for landing (Fuel: %d)\n",
                aircraft.id, aircraft.fuel);
        return false;
    }

    @Override
    public synchronized boolean requestTakeoff(Aircraft aircraft) {
        takeoffQueue.offer(aircraft);
        return false;
    }

    @Override
    public synchronized void runwayAvailable() {
        runwayOccupied = false;
        currentOnRunway = null;
    }
}