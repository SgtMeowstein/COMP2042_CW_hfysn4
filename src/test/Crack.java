package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/** Represents the Crack class for Cement Brick
 * @author Siti Khadijah
 * @version 2.0
 * @since 1.0
 */
//class to make the crack on the cement brick
public class Crack {

//the amount of cracks on the cement brick for it to break
    /** the maximum amount of Cracks on the brick
     */
    private static final int CRACK_SECTIONS = 3;

    /** how far the ball will bounce back after hitting the cement brick
     */
    private static final double JUMP_PROBABILITY = 0.7;

//the direction of the crack on the cement brick
    /** the integer will appear in the brick in the left
     */
    public static final int LEFT = 10;

    /** the integer will appear in the brick in the right
     */
    public static final int RIGHT = 20;

    /** the integer will appear in the brick in the up
     */
    public static final int UP = 30;

    /** the integer will appear in the brick in the down
     */
    public static final int DOWN = 40;

    /** the integer will appear in the brick in the vertical
     */
    public static final int VERTICAL = 100;

    /** the integer will appear in the brick in the horizontal
     */
    public static final int HORIZONTAL = 200;

//variables for the bricks

    private final Brick brick;
    private GeneralPath crack;
    private int crackDepth;
    private int steps;

    /** represent the crack on the brick
     * @param brick the type of bricks
     * @param crackDepth how many times it will crack
     * @param steps how many steps it takes for it crack
     */
//method for the crack
    public Crack(Brick brick, int crackDepth, int steps) {
        this.brick = brick;

        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;

    }


    /** Controls the line of the crack
     * @return will call back the crack method
     */
    public GeneralPath draw() {

        return crack;
    }

    /** When the game restarts it will reset
     */
    public void reset() {
        crack.reset();
    }

    /** Create the crack on the brick
     * @param point The point where the crack will appear
     * @param direction The direction where the crack will appear
     */
//method to make the crack appear in specific sides on the brick
    protected void makeCrack(Point2D point, int direction) {
        Rectangle bounds = brick.brickFace.getBounds();

        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point start = new Point();
        Point end = new Point();


        switch (direction) {
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);

                break;

        }
    }

    /** This control the how the crack will appear
     * @param start when the crack will appear
     * @param end the max length of the crack
     */
//how the crack will appear
    protected void makeCrack(Point start, Point end) {

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x, start.y);

        double w = (end.x - start.x) / (double) steps;
        double h = (end.y - start.y) / (double) steps;

        int bound = crackDepth;
        int jump = bound * 5;

        double x, y;

        for (int i = 1; i < steps; i++) {

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if (inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump, JUMP_PROBABILITY);

            path.lineTo(x, y);

        }

        path.lineTo(end.x, end.y);
        crack.append(path, true);
    }

    /** this control when the crack appear when the ball hits it randomly
     * @param bound when the ball hits the brick
     * @return return the Brick
     */
//
    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return Brick.getRnd().nextInt(n) - bound;
    }

    /** When the ball hits in them middle
     * @param i
     * @param steps
     * @param divisions
     * @return
     */
//when the ball hits in the middle
    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return (i > low) && (i < up);
    }

    /** When the ball will jump back after hitting the brick
     * @param bound will be the cause of the ball to bounce back
     * @param probability the probability of it happening
     * @return will return the value of the random bounce back
     */
//
    private int jumps(int bound, double probability) {

        if (Brick.getRnd().nextDouble() > probability)
            return randomInBounds(bound);
        return 0;

    }

    /** Creates the crack line at random places on the brick
     * @param from Where the line of crack will start
     * @param to how far it will stretch
     * @param direction the direction of the crack pointing
     * @return it will stop after it has reached its max number of crack
     */
    private Point makeRandomPoint(Point from, Point to, int direction) {

        Point out = new Point();
        int pos;

        switch (direction) {
            case HORIZONTAL:
                pos = Brick.getRnd().nextInt(to.x - from.x) + from.x;
                out.setLocation(pos, to.y);
                break;
            case VERTICAL:
                pos = Brick.getRnd().nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, pos);
                break;
        }
        return out;
    }

}
