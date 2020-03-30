import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.util.Vector;

public interface AddPartyViewInterface {
    void actionPerformed(ActionEvent e);
    void valueChanged(ListSelectionEvent e);
    Vector getNames();
    void updateNewPatron(NewPatronView newPatron);
    Vector getParty();
}
