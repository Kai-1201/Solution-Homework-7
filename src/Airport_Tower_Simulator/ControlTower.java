package Airport_Tower_Simulator;

import java.util.*;
import java.util.concurrent.*;

public class ControlTower implements TowerMediator {
    private final Deque<Aircraft> landingQueue = new LinkedList<>();
    private final Queue<Aircraft> takeoffQueue = new LinkedList<>();
    private boolean runwayOccupied = false;
    private Aircraft currentOnRunway;

    @Override
    public synchronized void broadcast(String message, Aircraft sender) {
        if ("MAYDAY".equals(message)) {
            handleEmergency(sender);
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
            processQueues();
            return true;
        }
        landingQueue.addLast(aircraft);
        System.out.printf("[Tower] %s queued for landing (Fuel: %d)\n",
                aircraft.id, aircraft.fuel);
        processQueues();
        return false;
    }

    @Override
    public synchronized boolean requestTakeoff(Aircraft aircraft) {
        takeoffQueue.offer(aircraft);
        processQueues();
        return false;
    }

    private void handleEmergency(Aircraft emergencyAircraft) {
        System.out.printf("[Tower] EMERGENCY! %s has MAYDAY (Fuel: %d)\n",
                emergencyAircraft.id, emergencyAircraft.fuel);

        if (currentOnRunway != null) {
            currentOnRunway.receive("Abort approach! Emergency!");
        }

        landingQueue.forEach(a -> a.receive("Hold position! Emergency!"));
        takeoffQueue.forEach(a -> a.receive("Hold position! Emergency!"));

        landingQueue.addFirst(emergencyAircraft);
        runwayOccupied = false;
        processQueues();
    }

    private void processQueues() {
        if (runwayOccupied) return;

        if (!landingQueue.isEmpty()) {
            currentOnRunway = landingQueue.removeFirst();
            runwayOccupied = true;
            currentOnRunway.receive("Cleared to land");
            scheduleRunwayRelease(3000);
        }
        else if (!takeoffQueue.isEmpty()) {
            currentOnRunway = takeoffQueue.poll();
            runwayOccupied = true;
            currentOnRunway.receive("Cleared for takeoff");
            scheduleRunwayRelease(2000);
        }
    }

    private void scheduleRunwayRelease(int delayMillis) {
        Executors.newSingleThreadScheduledExecutor().schedule(
                this::runwayAvailable,
                delayMillis,
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public synchronized void runwayAvailable() {
        runwayOccupied = false;
        currentOnRunway = null;
        processQueues();
    }
}