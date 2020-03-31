import java.util.HashSet;
import java.util.Vector;

public interface ControlDeskInterface {
    void run();
    void assignLane();
    Bowler registerPatron(String nickName);
    void addPartyQueue(Vector partyNicks);
    Vector getPartyQueue();
    int getNumLanes();
    void subscribe(ControlDeskObserver adding);
    void publish(ControlDeskEvent event);
    HashSet getLanes();
}