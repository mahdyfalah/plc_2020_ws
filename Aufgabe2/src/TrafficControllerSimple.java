public class TrafficControllerSimple implements TrafficController{
    private TrafficRegistrar registrar;
    private boolean free = true;
    private int counter = 0;

    public TrafficControllerSimple(TrafficRegistrar r) {
        this.registrar = r;
    }

    public synchronized void enterRight(Vehicle v) {
        counter++;
        while (!free){
            try { wait(); }
            catch(InterruptedException e) {}
        }
        if (counter>1)free = false;
        registrar.registerRight(v);
    }

    public synchronized void enterLeft(Vehicle v) {
        while (!free){
            try { wait(); }
            catch(InterruptedException e) {}
        }
        free = false;
        registrar.registerLeft(v);
    }

    public synchronized void leaveLeft(Vehicle v) {
        notify();
        counter--;
        if (counter%2==0)free = true;
        registrar.deregisterLeft(v);
    }

    public synchronized void leaveRight(Vehicle v) {
        notify();
        free = true;
        registrar.deregisterRight(v);
    }
}
