import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.util.Vector;

public interface AddPartyViewInterface {
    public void actionPerformed(ActionEvent e);
    public void valueChanged(ListSelectionEvent e);
    public Vector getNames();
    public void updateNewPatron(NewPatronView newPatron);
    public Vector getParty();
}
