public class Apple {
    private int appleID;     //the serial number of the apple
    private int appleTileID; //the tile number that the apple is on
    private String color;    //the color of the apple (Black or Red)
    private int points;      //how many points the apple gives (-10 if Black or +10 if Red)

    /**
     * The empty constructor of the class
     */
    public Apple() {
        //empty
    }

    /**
     * This function creates an apple with the given values
     * The points of the apples are -10 if the apple is Black and +10 if the apple is Red
     *
     * @param appleID     the serial number of the apple
     * @param appleTileID the tile number that the apple is on
     * @param color       the color of the apple (Black or Red)
     */
    public Apple(int appleID, int appleTileID, String color) {
        this.appleID = appleID;
        this.appleTileID = appleTileID;
        this.color = color;
        if (color == "Black") {
            this.points = -10;
        } else {
            this.points = 10;
        }
    }

    /**
     * This function creates an apple that is a copy of the given apple
     *
     * @param apple is an object of type Apple
     */
    public Apple(Apple apple) {
        this.appleID = apple.getAppleID();
        this.appleTileID = apple.getAppleTileID();
        this.color = apple.getColor();
        this.points = apple.getPoints();
    }

    /**
     * Getters and Setters for the variables of each apple
     */
    public int getAppleID() { return appleID; }
    public int getAppleTileID() { return appleTileID; }
    public int getPoints() { return points; }
    public String getColor() { return color; }

    public void setAppleID(int appleID) { this.appleID = appleID; }
    public void setAppleTileID(int appleTileID) { this.appleTileID = appleTileID; }
    public void setColor(String color) { this.color = color; }
    public void setPoints(int points) { this.points = points; }
}
