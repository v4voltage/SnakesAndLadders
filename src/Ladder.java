public class Ladder {
    private int ladderID;   //the serial number of the ladder
    private int upStepID;   //the tile number that the up   step of the ladder is on
    private int downStepID; //the tile number that the down step of the ladder is on
    private boolean broken; //true of the ladder is broken or false if the ladder is not broken

    /**
     * The empty constructor of the class
     */
    public Ladder() {
        //empty
    }

    /**
     * This function creates a ladder with the given values
     *
     * @param ladderID   the serial number of the ladder
     * @param upStepID   the tile number that the up   step of the ladder is on
     * @param downStepID the tile number that the down step of the ladder is on
     */
    public Ladder(int ladderID, int upStepID, int downStepID) {
        this.ladderID = ladderID;
        this.upStepID = upStepID;
        this.downStepID = downStepID;
        this.broken = false;
    }

    /**
     * This function creates a ladder that is a copy of the given ladder
     *
     * @param ladder is an object of type Ladder
     */
    public Ladder(Ladder ladder) {
        this.ladderID = ladder.getLadderID();
        this.upStepID = ladder.getUpStepID();
        this.downStepID = ladder.getDownStepID();
        this.broken = false;
    }

    /**
     * @return true if the ladder is broken or false if the ladder is not broken
     */
    public boolean isBroken() { return broken; }

    /**
     * Getters and Setters for the variables of each ladder
     */
    public int getDownStepID() { return downStepID; }
    public int getLadderID() { return ladderID; }
    public int getUpStepID() { return upStepID; }

    public void setBroken(boolean broken) { this.broken = broken; }
    public void setDownStepID(int downStepID) { this.downStepID = downStepID; }
    public void setLadderID(int ladderID) { this.ladderID = ladderID; }
    public void setUpStepID(int upStepID) { this.upStepID = upStepID; }
}
