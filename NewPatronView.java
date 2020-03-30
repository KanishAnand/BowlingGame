/* AddPartyView.java
 *
 *  Version
 *  $Id$
 * 
 *  Revisions:
 * 		$Log: NewPatronView.java,v $
 * 		Revision 1.3  2003/02/02 16:29:52  ???
 * 		Added ControlDeskEvent and ControlDeskObserver. Updated Queue to allow access to Vector so that contents could be viewed without destroying. Implemented observer model for most of ControlDesk.
 * 		
 * 
 */

/**
 * Class for GUI components need to add a patron
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NewPatronView extends ViewComponents implements ActionListener {

	private int maxSize;

	private JFrame win;
	private JButton abort, finished;
	private JTextField nickField, fullField, emailField;
	private String nick, full, email;

	private boolean done;

	private String selectedNick, selectedMember;
	private AddPartyView addParty;

	public NewPatronView(AddPartyView v) {

		addParty=v;	
		done = false;

		win = MakeWindow("Add Patron");

		JPanel colPanel = MakeMainPanel();

		// Patron Panel
		JPanel patronPanel = MakePanel(3,1,"Your Info");

		// Controls Panel
		nickField = MakeField("Nick Name",patronPanel);
		fullField = MakeField("Full Name",patronPanel);
		emailField = MakeField("E-Mail",patronPanel);

		// Button Panel
		JPanel buttonPanel = MakePanel(4,1,"Buttons");
		Insets buttonMargin = new Insets(4, 4, 4, 4);

		finished = MakeButtons("Add Patron",buttonPanel);
		abort = MakeButtons("Abort",buttonPanel);

		// Clean up main panel
		colPanel.add(patronPanel, "Center");
		colPanel.add(buttonPanel, "East");

		AddContentsToWindow(win,colPanel);

		// Center Window on Screen
		SetWindowPosition(win);
		win.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(abort)) {
			done = true;
			win.setVisible(false);
		}

		if (e.getSource().equals(finished)) {
			nick = nickField.getText();
			full = fullField.getText();
			email = emailField.getText();
			done = true;
			addParty.updateNewPatron( this );
			win.setVisible(false);
		}

	}

	public boolean done() {
		return done;
	}

	public String getNick() {
		return nick;
	}

	public String getFull() {
		return full;
	}

	public String getEmail() {
		return email;
	}

}
