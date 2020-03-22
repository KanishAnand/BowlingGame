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
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;
import java.text.*;

public class NewPatronView implements ActionListener {

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

		win = new JFrame("Add Patron");
		win.getContentPane().setLayout(new BorderLayout());
		((JPanel) win.getContentPane()).setOpaque(false);

		JPanel colPanel = new JPanel();
		colPanel.setLayout(new BorderLayout());

		// Patron Panel
		JPanel patronPanel = MakePanel(3,1);
		patronPanel.setBorder(new TitledBorder("Your Info"));

		// Controls Panel
		nickField = MakeField("Nick Name",patronPanel);
		fullField = MakeField("Full Name",patronPanel);
		emailField = MakeField("E-Mail",patronPanel);

		// Button Panel
		JPanel buttonPanel = MakePanel(4,1);
		Insets buttonMargin = new Insets(4, 4, 4, 4);

		finished = MakeButtons("Add Patron",buttonPanel);
		abort = MakeButtons("Abort",buttonPanel);

		// Clean up main panel
		colPanel.add(patronPanel, "Center");
		colPanel.add(buttonPanel, "East");

		win.getContentPane().add("Center", colPanel);

		win.pack();

		// Center Window on Screen
		SetWindowPosition(win);
		win.show();

	}

	public void SetWindowPosition(JFrame win){
		Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
		win.setLocation(
				((screenSize.width) / 2) - ((win.getSize().width) / 2),
				((screenSize.height) / 2) - ((win.getSize().height) / 2));
	}

	public JPanel MakePanel(int row,int col){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(row,col));
		return panel;
	}

	public JTextField MakeField(String st,JPanel patronPanel){
		JPanel obj  = new JPanel();
		obj.setLayout((new FlowLayout()));
		JLabel label = new JLabel(st);
		JTextField text = new JTextField("",15);
		obj.add(label);
		obj.add(text);
		patronPanel.add(obj);
		return text;
	}

	public JButton MakeButtons(String st,JPanel buttonPanel){
		JButton btn = new JButton(st);
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		btn.addActionListener(this);
		btnPanel.add(btn);
		buttonPanel.add(btn);
		return btn;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(abort)) {
			done = true;
			win.hide();
		}

		if (e.getSource().equals(finished)) {
			nick = nickField.getText();
			full = fullField.getText();
			email = emailField.getText();
			done = true;
			addParty.updateNewPatron( this );
			win.hide();
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
