package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/** Represents the CementBricks class
 * @author Siti Khadijah Binti Norzafri
 * @version 2.0
 * @since 1.0
 *
 */
public class CementBrick extends Brick {


    /** String to store the name of the brick
     */
    private static final String NAME = "Cement Brick";

    /** the inner colour for the cement brick
     */
    private static final Color DEF_INNER = new Color(103, 103, 103);

    /** the border colour for cement brick
     */
    private static final Color DEF_BORDER = new Color(217, 199, 175);

    /** the strength of the brick
     */
    private static final int CEMENT_STRENGTH = 2;


    /**Represents the variable for the method crack
     * @param crack the variable for Crack on the brick
     */
    private Crack crack;

    /**Represents the variable for the method crack
     * @param brickFace the variable for the shape of the brick
     */
    private Shape brickFace;

    /**Determine the size and position of the bricks
     * @param point the position of the brick
     * @param size the size of the brick
     */
//method for the placements of the cement bricks
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(CementBrick.this, DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.brickFace;
    }

    /**
     * @param pos  position of the bricks
     * @param size size of the bricks
     * @return the shape that will be for the bricks which is Rectangle
     */
/*a subclass that provide a specific implementation of makeBrickFace that is already in Brick*/
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /** Represents the impact when the ball hits the brick
     * @param point if the ball hits the brick in a certain point it will break
     * @param dir the direction of the ball hitting the brick
     * @return if the ball hits the brick it will crack and when it hits the 2nd time it will go break
     */
/*a subclass that provide a specific implementation of setImpact that is already in Brick*/
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }

    /** Creates the shape of the brick
     * @return it will return the shape and design of the brick
     */
/*a subclass that provide a specific implementation of getBrick that is already in Brick*/
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /** Update the brick
     *
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    /** Repair the bricks when restarts
     *
     */
/*a method to reset the bricks when the game restarts*/
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
