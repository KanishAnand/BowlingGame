import java.util.HashMap;

public class CalculateScore {
    public final HashMap scores;
    public Party party;
    public boolean partyAssigned;
    public int[] curScores;
    public int[][] cumulScores;
    public int[][] finalScores;


    public CalculateScore(){

        scores = new HashMap();
        partyAssigned = false;
    }

    /** resetScores()
     *
     * resets the scoring mechanism, must be called before scoring starts
     *
     * @pre the party has been assigned
     * @post scoring system is initialized
     */
    public void resetScores(Party party) {

        for (Object o : party.getMembers()) {
            int[] toPut = new int[25];
            for (int i = 0; i != 25; i++) {
                toPut[i] = -1;
            }
            scores.put(o, toPut);
        }
    }

    /** markScore()
     *
     * Method that marks a bowlers score on the board.
     *
     */
     public void markScore(Lane lane,int ball,int score){
        int[] curScore;
        int index =  ( lane.frameNumber * 2 + ball);
        curScore = (int[]) scores.get(lane.currentThrower);
        curScore[ index - 1] = score;
        scores.put(lane.currentThrower, curScore);
        getScore( lane.currentThrower, lane.frameNumber+1 ,cumulScores,lane.bowlIndex,ball);
        LaneSubscriber.publish(lane,lane.lanePublish());
    }

    /** getScore()
     *
     * Method that calculates a bowlers score
     *
     * @param Cur        The bowler that is currently up
     * @param frame    The frame the current bowler is on
     *
     * @param cumulScores
     * @return			The bowlers total score
     */

    public void getScore(Bowler Cur, int frame, int[][] cumulScores, int bowlIndex, int ball) {
        int[] curScore;

        curScore = (int[]) scores.get(Cur);
        for (int i = 0; i != 10; i++){
            cumulScores[bowlIndex][i] = 0;
        }

        int current = 2*frame + ball - 3;
        //Iterate through each ball until the current one.
        for (int i = 0; i != current+2; i++){
            //Spare:
            if(((i % 2) == 1) && ((curScore[i - 1] + curScore[i]) == 10) && (i < (current - 1)) && (i < 19)){
                //This ball was a the second of a spare.
                //Also, we're not on the current ball.
                //Add the next ball to the ith one in cumul.
                cumulScores[bowlIndex][(i/2)] += curScore[i+1] + curScore[i];
            }
            else if((i < current) && ((i % 2) == 0) && (curScore[i] == 10) && (i < 18)){
                //This ball is the first ball, and was a strike.
                //If we can get 2 balls after it, good add them to cumul.
                if (curScore[i+2] != -1) {
                    if(curScore[i+3] != -1 || curScore[i+4] != -1) {
                        //Still got em.
                        //Add up the strike.
                        //Add the next two balls to the current cumulscore.
                        cumulScores[bowlIndex][i/2] += 10;
                        if (curScore[i + 1] == -1) {

                            cumulScores[bowlIndex][i / 2] += i / 2 > 0 ? curScore[i + 2] + cumulScores[bowlIndex][(i / 2) - 1] : curScore[i + 2];
                            if (curScore[i + 3] != -1 && curScore[i+3] != -2) {
                                cumulScores[bowlIndex][(i / 2)] += curScore[i + 3];
//                            }
                            } else {
                                cumulScores[bowlIndex][(i / 2)] += curScore[i + 4];
                            }
                        } else {
                            cumulScores[bowlIndex][i / 2] += curScore[i + 1] + cumulScores[bowlIndex][(i / 2) - 1];
                            if (curScore[i + 2] != -1 && curScore[i+2] != -2) {
                                cumulScores[bowlIndex][(i / 2)] += curScore[i + 2];
                            } else if(curScore[i + 3] != -2){
                                cumulScores[bowlIndex][(i / 2)] += curScore[i + 3];
                            }
                        }
                    }
                    else{
                        break;
                    }
                }
            }

            else {
                //We're dealing with a normal throw, add it and be on our way.
                if( i%2 == 0 && i < 18){
                    if ( i == 0 && curScore[i] != -2) {
                        //First frame, first ball.  Set his cumul score to the first ball
                        cumulScores[bowlIndex][i/2] += curScore[i];
                    }
                    else{
                        //add his last frame's cumul to this ball, make it this frame's cumul.
                        cumulScores[bowlIndex][i / 2] += curScore[i] == -2 ? cumulScores[bowlIndex][i / 2 - 1] : cumulScores[bowlIndex][i / 2 - 1] + curScore[i];
                    }
                }

                else if (i < 18 && i > 2 && curScore[i] != -1 && curScore[i] != -2 ){
                            cumulScores[bowlIndex][i/2] += curScore[i];
                }

                if (i/2 == 9){
                    if (i == 18){
                        cumulScores[bowlIndex][9] += cumulScores[bowlIndex][8];
                    }
                    if(curScore[i] != -2){
                        cumulScores[bowlIndex][9] += curScore[i];
                    }
                }
                else if (i/2 == 10 && curScore[i] != -2) {
                        cumulScores[bowlIndex][9] += curScore[i];
                }
            }
        }
    }
}
