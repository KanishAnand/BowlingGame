/*
 * PinSetterView/.java
 *
 * Version:
 *   $Id$
 *
 * Revision:
 *   $Log$
 */

/**
 *  constructs a prototype PinSetter GUI
 *
 */

import java.awt.*;
import javax.swing.*;
import java.util.Vector;


public class PinSetterView implements PinsetterObserver {


    private final Vector pinVect = new Vector ( );
	private final JPanel secondRoll;

    /**
     * Constructs a Pin Setter GUI displaying which roll it is with
     * yellow boxes along the top (1 box for first roll, 2 boxes for second)
     * and displays the pins as numbers in this format:
     *
     *                7   8   9   10
     *                  4   5   6
     *                    2   3
     *                      1
     *
     */

	private final JFrame frame;
    public PinSetterView ( int laneNum ) {
	
	frame = new JFrame ( "Lane " + laneNum + ":" );
	Container cpanel = frame.getContentPane ( );
	JPanel pins = new JPanel ( );
	pins.setLayout ( new GridLayout ( 4, 7 ) );
	
	//********************Top of GUI indicates first or second roll
	
	JPanel top = new JPanel();

		JPanel firstRoll = new JPanel();
	firstRoll.setBackground( Color.yellow );
	
	secondRoll = new JPanel();
	secondRoll.setBackground ( Color.black );
	
	top.add (firstRoll, BorderLayout.WEST );
	top.add ( secondRoll, BorderLayout.EAST );

	//**********************Grid of the pins**************************
	SetFourthRow(pins);
	SetThirdRow(pins);
	SetSecondRow(pins);
	SetFirstRow(pins);

	top.setBackground ( Color.black );
	cpanel.add ( top, BorderLayout.NORTH );
	
	pins.setBackground ( Color.black );
	pins.setForeground ( Color.yellow );
	
	cpanel.add ( pins, BorderLayout.CENTER );
	
	frame.pack();
//	frame.show();
    }

    public void SetFourthRow(JPanel pins){
    	for (int i=7;i<=10;i++){
			JPanel value = new JPanel();
			JLabel label = new JLabel(Integer.toString(i));
			value.add(label);
			pinVect.add(label);
			pins.add(value);
			if(i != 10) {
				pins.add(new JPanel());
			}
		}
	}

	public void SetThirdRow(JPanel pins){
		for (int i=4;i<=6;i++){
			JPanel value = new JPanel();
			JLabel label = new JLabel(Integer.toString(i));
			value.add(label);
			pinVect.add(label);
			pins.add(new JPanel());
			pins.add(value);
		}
	}

	public void SetSecondRow(JPanel pins){
    	pins.add(new JPanel());
		pins.add(new JPanel());
		for (int i=2;i<=3;i++){
			JPanel value = new JPanel();
			JLabel label = new JLabel(Integer.toString(i));
			value.add(label);
			pinVect.add(label);
			pins.add(new JPanel());
			pins.add(value);
		}
		pins.add(new JPanel());
		pins.add(new JPanel());
	}

	public void SetFirstRow(JPanel pins){
		pins.add(new JPanel());
		pins.add(new JPanel());
		pins.add(new JPanel());
		for (int i=1;i<=1;i++){
			JPanel value = new JPanel();
			JLabel label = new JLabel(Integer.toString(i));
			value.add(label);
			pinVect.add(label);
			pins.add(value);
		}
		pins.add(new JPanel());
		pins.add(new JPanel());
		pins.add(new JPanel());
	}


    /**
     * This method receives a pinsetter event.  The event is the current
     * state of the PinSetter and the method changes how the GUI looks
     * accordingly.  When pins are "knocked down" the corresponding label
     * is grayed out.  When it is the second roll, it is indicated by the
     * appearance of a second yellow box at the top.
     *
     * @param e    The state of the pinsetter is sent in this event.
     */
    

    public void receivePinsetterEvent(PinsetterEvent pe){
	if ( !(pe.isFoulCommited()) ) {
	    	JLabel tempPin;
	    	for ( int c = 0; c < 10; c++ ) {
				boolean pin = pe.pinKnockedDown ( c );
				tempPin = (JLabel)pinVect.get ( c );
				if ( pin ) {
		    		tempPin.setForeground ( Color.lightGray );
				}
	    	}
    	}
		if ( pe.getThrowNumber() == 1 ) {
	   		 secondRoll.setBackground ( Color.yellow );
		}
	if ( pe.pinsDownOnThisThrow() == -1) {
		for ( int i = 0; i != 10; i++){
			((JLabel)pinVect.get(i)).setForeground(Color.black);
		}
		secondRoll.setBackground( Color.black);
	}
    }
    
    public void show() {
    	frame.setVisible(true);
    }

    public void hide() {
    	frame.setVisible(false);
    }
    
    public static void main (String[] args) {
		PinSetterView pg = new PinSetterView ( 1 );
    }
    
}
