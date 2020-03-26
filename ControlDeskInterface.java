import java.util.HashSet;
import java.util.Vector;

public interface ControlDeskInterface {
    public void run();
    public void assignLane();
    public Bowler registerPatron(String nickName);
    public void viewScores(Lane ln);
    public void addPartyQueue(Vector partyNicks);
    public Vector getPartyQueue();
    public int getNumLanes();
    public void subscribe(ControlDeskObserver adding);
    public void publish(ControlDeskEvent event);
    public HashSet getLanes();
}