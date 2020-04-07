import java.util.Iterator;
import java.util.Vector;

public class PartyQueue {

    /**
     * Iterate through the available lanes and assign the paties in the wait queue if lanes are available.
     *
     */

    public static void assignLane(ControlDesk controlDesk) {
        Iterator it = controlDesk.lanes.iterator();

        while (it.hasNext() && controlDesk.partyQueue.hasMoreElements()) {
            Lane curLane = (Lane) it.next();

            if (!curLane.isPartyAssigned()) {
                System.out.println("ok... assigning this party");
                ((Party) controlDesk.partyQueue.next()).assignParty(curLane);
            }
        }
        controlDesk.publish(new ControlDeskEvent(PartyQueue.getPartyQueue(controlDesk)));
    }

    /**
     * Creates a party from a Vector of nickNames and adds them to the wait queue.
     *
     * @param partyNicks	A Vector of NickNames
     *
     */

    public static void addPartyQueue(ControlDesk controlDesk, Vector partyNicks) {
        Vector partyBowlers = new Vector();
        for (Object partyNick : partyNicks) {
            Bowler newBowler = BowlerFile.registerPatron(((String) partyNick));
            partyBowlers.add(newBowler);
        }
        Party newParty = new Party(partyBowlers);
        controlDesk.partyQueue.add(newParty);
        controlDesk.publish(new ControlDeskEvent(getPartyQueue(controlDesk)));
    }

    /**
     * Returns a Vector of party names to be displayed in the GUI representation of the wait queue.
     *
     * @return a Vecotr of Strings
     *
     */

    public static Vector getPartyQueue(ControlDesk controlDesk) {
        Vector displayPartyQueue = new Vector();
        for (int i = 0; i < controlDesk.partyQueue.asVector().size(); i++ ) {
            String nextParty =
                    ((Bowler) ((Party) controlDesk.partyQueue.asVector().get( i ) ).getMembers()
                            .get(0))
                            .getNickName() + "'s Party";
            displayPartyQueue.addElement(nextParty);
        }
        return displayPartyQueue;
    }

}
