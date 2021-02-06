
public class TrafficController2Cars implements TrafficController {

    private TrafficRegistrar registrar;
    private boolean free = true;

    private int countL = 0;
    private int countR = 0;

    public TrafficController2Cars(TrafficRegistrar r) {
        this.registrar = r;
    }

    public synchronized void enterRight(Vehicle v) {
        while (!free && countR>=2){
            try { wait(); }
            catch(InterruptedException e) {}
        }
        countR++;
        free = false;
        registrar.registerRight(v);
    }

    public synchronized void enterLeft(Vehicle v) {
        while (!free && countL>=2){
            try { wait(); }
            catch(InterruptedException e) {}
        }
        countL++;
        free = false;
        registrar.registerLeft(v);
    }

    public synchronized void leaveLeft(Vehicle v) {
        notify();
        countR--;
        free=true;
        registrar.deregisterLeft(v);
    }

    public synchronized void leaveRight(Vehicle v) {
        notify();
        countL--;
        free=true;
        registrar.deregisterRight(v);
    }
}