import java.util.HashSet;
import java.util.Vector;

public interface ControlDeskInterface {
    void run();
//    void assignLane();
//    void addPartyQueue(Vector partyNicks);
//    Vector getPartyQueue();
    int getNumLanes();
//    void subscribe(ControlDeskObserver adding);
    HashSet getLanes();
}


