public class TrafficControllerFun implements TrafficController{
    TrafficRegistrar registrar;
    private boolean free = true;

    private int countL = 0;
    private int countR = 0;

    TrafficControllerFun(TrafficRegistrar registrar){
        this.registrar = registrar;
    }
    @Override
    synchronized public void enterRight(Vehicle v) {
        countR++;
        while(!free && countR%2!=0){
            try{ wait();}
            catch (InterruptedException e){}
        }

        free = false;

        registrar.registerRight(v);
    }

    @Override
    synchronized public void enterLeft(Vehicle v) {
        countL++;
        while(!free && countL%2!=0){
            try{ wait();}
            catch (InterruptedException e){}
        }

        free = false;

        registrar.registerLeft(v);
    }

    @Override
    synchronized public void leaveLeft(Vehicle v) {
        countR--;
        notify();

        if (countR%2==0)free = true;

        registrar.deregisterLeft(v);
    }

    @Override
    synchronized public void leaveRight(Vehicle v) {
        countL--;
        notify();

        if(countL%2==0)free = true;

        registrar.deregisterRight(v);
    }
}
