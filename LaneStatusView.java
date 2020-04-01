/**
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LaneStatusView extends ViewComponents implements ActionListener, LaneObserver, PinsetterObserver {

	private final JPanel jp;

	private final JLabel curBowler;
	private final JLabel pinsDown;
	private final JButton viewLane;
	private final JButton viewPinSetter;
	private final JButton maintenance;

	private final PinSetterView psv;
	private final LaneView lv;
	private final Lane lane;
	final int laneNum;

	boolean laneShowing;
	boolean psShowing;

	public LaneStatusView(Lane lane, int laneNum ) {

		this.lane = lane;
		this.laneNum = laneNum;

		laneShowing=false;
		psShowing=false;

		psv = new PinSetterView( laneNum );
		Pinsetter ps = lane.getPinsetter();
//		ps.subscribe(psv);
		lane.PinsetterSubscribe(psv);

		lv = new LaneView( lane, laneNum );
		lane.subscribe(lv);


		jp = FlowLayoutPanel();
		JLabel cLabel = new JLabel( "Now Bowling: " );
		curBowler = new JLabel( "(no one)" );
		JLabel fLabel = new JLabel( "Foul: " );
		JLabel foul = new JLabel(" ");
		JLabel pdLabel = new JLabel( "Pins Down: " );
		pinsDown = new JLabel( "0" );

		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		Insets buttonMargin = new Insets(4, 4, 4, 4);

		viewLane = MakeButtons("View Lane",buttonPanel);
		viewPinSetter = MakeButtons("Pinsetter",buttonPanel);

		maintenance = new JButton("     ");
		maintenance.setBackground( Color.GREEN );
		JPanel maintenancePanel = new JPanel();
		maintenancePanel.setLayout(new FlowLayout());
		maintenance.addActionListener(this);
		maintenancePanel.add(maintenance);

		viewLane.setEnabled( false );
		viewPinSetter.setEnabled( false );

		buttonPanel.add(maintenancePanel);

		jp.add( cLabel );
		jp.add( curBowler );
		jp.add( pdLabel );
		jp.add( pinsDown );
		jp.add(buttonPanel);

	}

	public JPanel showLane() {
		return jp;
	}

	public void actionPerformed( ActionEvent e ) {
		if (lane.isPartyAssigned()) {
			if (e.getSource().equals(viewPinSetter)) {
				if (psShowing) {
					psv.hide();
					psShowing = false;
				} else {
					psv.show();
					psShowing = true;
				}
			}

			if (e.getSource().equals(viewLane)) {
				if (laneShowing) {
					lv.hide();
					laneShowing = false;
				} else {
					lv.show();
					laneShowing = true;
				}
			}
		}

		if (e.getSource().equals(maintenance)) {
			if ( lane.isPartyAssigned() ) {
				lane.unPauseGame();
				maintenance.setBackground( Color.GREEN );
			}
		}
	}

	public void receiveLaneEvent(LaneEvent le) {
//		curBowler.setText( le.getBowler().getNickName() );
		curBowler.setText( le.getBowler().getNickName() );
		if ( le.isMechanicalProblem() ) {
			maintenance.setBackground( Color.RED );
		}
		if (lane.isPartyAssigned()) {
			viewLane.setEnabled(true);
			viewPinSetter.setEnabled(true);
		} else {
			viewLane.setEnabled(false);
			viewPinSetter.setEnabled(false);
		}
	}

	public void receivePinsetterEvent(PinsetterEvent pe) {
		pinsDown.setText( ( new Integer(pe.totalPinsDown()) ).toString() );
//		foul.setText( ( new Boolean(pe.isFoulCommited()) ).toString() );

	}

}
