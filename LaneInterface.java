import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public interface LaneInterface {

        void run() ;
        void receivePinsetterEvent(PinsetterEvent pe);
        void assignParty( Party theParty );
        boolean isPartyAssigned();
        boolean isGameFinished();
        void subscribe( LaneObserver adding );
        void unsubscribe( LaneObserver removing );
        void publish( LaneEvent event );
        Pinsetter getPinsetter();
        void pauseGame();
        void unPauseGame();
}
