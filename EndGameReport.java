/**
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

import java.awt.*;
import java.awt.event.*;
import javax.print.attribute.standard.PrinterMakeAndModel;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;
import java.text.*;

public class EndGameReport extends ViewComponents implements ActionListener, ListSelectionListener {

	private JFrame win;
	private JButton printButton, finished;
	private JList memberList;
	private Vector myVector;
	private Vector retVal;

	private int result;

	private String selectedMember;

	public EndGameReport( String partyName, Party party ) {
	
		result =0;
		retVal = new Vector();

		win = MakeWindow("End Game Report for " + partyName + "?" );

		JPanel colPanel = GridLayoutPanel(1,2);

		// Member Panel
		JPanel partyPanel = FlowLayoutPanel();
		partyPanel.setBorder(new TitledBorder("Party Members"));
		
		Vector myVector = new Vector();
		Iterator iter = (party.getMembers()).iterator();
		while (iter.hasNext()){
			myVector.add( ((Bowler)iter.next()).getNick() );
		}

		memberList = new JList(myVector);
		memberList.setFixedCellWidth(120);
		memberList.setVisibleRowCount(5);
		memberList.addListSelectionListener(this);
		JScrollPane partyPane = new JScrollPane(memberList);
		//        partyPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		partyPanel.add(partyPane);
		partyPanel.add( memberList );

		// Button Panel
		JPanel buttonPanel = GridLayoutPanel(2,1);
		Insets buttonMargin = new Insets(4, 4, 4, 4);

		printButton = MakeButtons("PrintReport",buttonPanel);
		finished = MakeButtons("Finished",buttonPanel);

		// Clean up main panel
		colPanel.add(partyPanel);
		colPanel.add(buttonPanel);

		AddContentsToWindow(win,colPanel);

		// Center Window on Screen
		SetWindowPosition(win);
		win.show();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(printButton)) {		
			//Add selected to the vector.
			retVal.add(selectedMember);
		}
		else if (e.getSource().equals(finished)) {
			win.hide();
			result = 1;
		}

	}

	public void valueChanged(ListSelectionEvent e) {
		selectedMember =
			((String) ((JList) e.getSource()).getSelectedValue());
	}

	public Vector getResult() {
		while ( result == 0 ) {
			try {
				Thread.sleep(10);
			} catch ( InterruptedException e ) {
				System.err.println( "Interrupted" );
			}
		}
		return retVal;	
	}
	
	public void destroy() {
		win.hide();
	}

	public static void main( String args[] ) {
		Vector bowlers = new Vector();
		for ( int i=0; i<4; i++ ) {
			bowlers.add( new Bowler( "aaaaa", "aaaaa", "aaaaa" ) );
		}
		Party party = new Party( bowlers );
		String partyName="wank";
		EndGameReport e = new EndGameReport( partyName, party );
	}
	
}

