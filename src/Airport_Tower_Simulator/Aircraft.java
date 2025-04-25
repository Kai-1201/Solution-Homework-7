package Airport_Tower_Simulator;

public abstract class Aircraft {
    protected final String id;
    protected int fuel;
    protected TowerMediator tower;
    protected String status = "READY";

    public Aircraft(String id, int fuel) {
        this.id = id;
        this.fuel = fuel;
    }

        public void setMediator(TowerMediator tower) {
        this.tower = tower;
    }

    public abstract void receive(String message);

    public void send(String message) {
        if (tower != null) {
            tower.broadcast(message, this);
        }
    }

    public void requestLanding() {
        tower.requestLanding(this);
    }

    public void requestTakeoff() {
        tower.requestTakeoff(this);
    }

    public void mayday() {
        this.status = "MAYDAY";
        send("MAYDAY");
    }

    public void consumeFuel() {
        if (fuel > 0) fuel--;
        if (fuel < 5) mayday();
    }
}