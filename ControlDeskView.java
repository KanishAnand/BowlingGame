/* ControlDeskView.java
 *
 *  Version:
 *			$Id$
 * 
 *  Revisions:
 * 		$Log$
 * 
 */

/**
 * Class for representation of the control desk
 *
 */

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.*;

public class ControlDeskView  extends ViewComponents implements ActionListener, ControlDeskObserver{

	private final JButton addParty;
	private final JButton finished;
	private final JButton assign;
	private final JFrame win;
	private final JList partyList;
	
	/** The maximum  number of members in a party */
	private final int maxMembers;
	
	private final ControlDesk controlDesk;

	/**
	 * Displays a GUI representation of the ControlDesk
	 *
	 */

	public ControlDeskView(ControlDesk controlDesk, int maxMembers) {

		this.controlDesk = controlDesk;
		this.maxMembers = maxMembers;
		int numLanes = controlDesk.getNumLanes();

		win = MakeWindow("Control Desk");

		JPanel colPanel = MakeMainPanel();

		// Controls Panel
		JPanel controlsPanel = MakePanel(3,1,"Controls");

		//Buttons
		addParty = MakeButtons("Add Party",controlsPanel);
		assign = MakeButtons("Assign Lanes",controlsPanel);
		finished = MakeButtons("Finished",controlsPanel);

		// Lane Status Panel
		JPanel laneStatusPanel = MakePanel(numLanes,1,"Lane Status");

		HashSet lanes=controlDesk.getLanes();
		Iterator it = lanes.iterator();
		int laneCount=0;

		while (it.hasNext()) {
			Lane curLane = (Lane) it.next();
			LaneStatusView laneStat = new LaneStatusView(curLane,(laneCount+1));
//			curLane.subscribe(laneStat);
			LaneSubscriber.subscribe(curLane,laneStat);
//			curLane.setter.subscribe(laneStat);
			PinsetterSubscriber.subscribe(curLane.setter,laneStat);
			JPanel lanePanel = laneStat.showLane();
			lanePanel.setBorder(new TitledBorder("Lane " + ++laneCount ));
			laneStatusPanel.add(lanePanel);
		}

		// Party Queue Panel
		JPanel partyPanel = FlowLayoutPanel();
		partyPanel.setBorder(new TitledBorder("Party Queue"));

		Vector empty = new Vector();
		empty.add("(Empty)");

		partyList = new JList(empty);
		partyList.setFixedCellWidth(120);
		partyList.setVisibleRowCount(10);
		JScrollPane partyPane = new JScrollPane(partyList);
		partyPane.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		partyPanel.add(partyPane);
		//		partyPanel.add(partyList);

		// Clean up main panel
		colPanel.add(controlsPanel, "East");
		colPanel.add(laneStatusPanel, "Center");
		colPanel.add(partyPanel, "West");

		AddContentsToWindow(win,colPanel);

		/* Close program when this window closes */
		win.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Center Window on Screen
		SetWindowPosition(win);
	}

	/**
	 * Handler for actionEvents
	 *
	 * @param e	the ActionEvent that triggered the handler
	 *
	 */

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addParty)) {
			AddPartyView addPartyWin = new AddPartyView(this, maxMembers);
		}
		if (e.getSource().equals(assign)) {
			PartyQueue.assignLane(controlDesk);
		}
		if (e.getSource().equals(finished)) {
			win.setVisible(false);
			System.exit(0);
		}
	}

	/**
	 * Receive a new party from andPartyView.
	 *
	 * @param addPartyView	the AddPartyView that is providing a new party
	 *
	 */

	public void updateAddParty(AddPartyView addPartyView) {
		PartyQueue.addPartyQueue(controlDesk,addPartyView.getParty());
	}

	/**
	 * Receive a broadcast from a ControlDesk
	 *
	 * @param ce	the ControlDeskEvent that triggered the handler
	 *
	 */
	
	public void receiveControlDeskEvent(ControlDeskEvent ce) {
		partyList.setListData(ce.getPartyQueue());
	}
}
