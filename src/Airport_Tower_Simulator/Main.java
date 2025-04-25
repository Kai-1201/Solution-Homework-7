package Airport_Tower_Simulator;

import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        Random random = new Random();
        List<Aircraft> aircrafts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Aircraft aircraft;
            int fuel = 10 + random.nextInt(20);

            switch (random.nextInt(3)) {
                case 0:
                    aircraft = new PassengerPlane("PAX-" + (100 + i), fuel);
                    break;
                case 1:
                    aircraft = new CargoPlane("CAR-" + (200 + i), fuel);
                    break;
                default:
                    aircraft = new Helicopter("HEL-" + (300 + i), fuel);
            }

            aircraft.setMediator(tower);
            aircrafts.add(aircraft);
        }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            Aircraft aircraft = aircrafts.get(random.nextInt(aircrafts.size()));
            aircraft.consumeFuel();

            if (random.nextDouble() < 0.3) {
                if (random.nextBoolean()) {
                    aircraft.requestLanding();
                } else {
                    aircraft.requestTakeoff();
                }
            }

            if (random.nextDouble() < 0.05) {
                aircraft.mayday();
            }

            System.out.println("-----");
        }, 0, 2, TimeUnit.SECONDS);
    }
}