/*
 * Party.java
 *
 * Version:
 *   $Id$
 *
 * Revisions:
 *   $Log: Party.java,v $
 *   Revision 1.3  2003/02/09 21:21:31  ???
 *   Added lots of comments
 *
 *   Revision 1.2  2003/01/12 22:23:32  ???
 *   *** empty log message ***
 *
 *   Revision 1.1  2003/01/12 19:09:12  ???
 *   Adding Party, Lane, Bowler, and Alley.
 *
 */

/**
 *  Container that holds bowlers
 *
 */

import java.util.*;

public class Party {

	/** Vector of bowlers in this party */	
    private final Vector myBowlers;
	
	/**
	 * Constructor for a Party
	 * 
	 * @param bowlers	Vector of bowlers that are in this party
	 */
		
    public Party( Vector bowlers ) {
		myBowlers = new Vector(bowlers);
    }

	/**
	 * Accessor for members in this party
	 * 
	 * @return 	A vector of the bowlers in this party
	 */

    public Vector getMembers() {
		return myBowlers;
    }

	/** assignParty()
	 *
	 * assigns a party to this lane
	 *
	 * @pre none
	 * @post the party has been assigned to the lane
	 *
	 *
	 */
	public void assignParty( Lane curLane ) {
		curLane.party = this;
		curLane.resetBowlerIterator();
		curLane.partyAssigned = true;

		curLane.curScores = new int[myBowlers.size()];
		curLane.cumulScores = new int[myBowlers.size()][10];
		curLane.finalScores = new int[myBowlers.size()][128]; //Hardcoding a max of 128 games, bite me.
		curLane.gameNumber = 0;

		curLane.calculateScore.resetScores(this);
		curLane.gameFinished = false;
		curLane.frameNumber = 0;
	}

}
