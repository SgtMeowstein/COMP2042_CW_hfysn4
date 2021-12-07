package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;


/** Represents the ClayBrick class
 * @author Siti Khadijah
 * @version 2.0
 * @since 1.0
 */
/*class for the ClayBrick which connects to Brick*/
public class ClayBrick extends Brick {

    /** String to store the name of the brick
     */
    private static final String NAME = "Clay Brick";

    /** the inner colour for the clay brick
     */
    private static final Color DEF_INNER = new Color(132, 58, 29).darker();

    /** the border colour for the clay brick
     */
    private static final Color DEF_BORDER = Color.GRAY;

    /** the strength of the brick
     */
    private static final int CLAY_STRENGTH = 1;


    /** Arrangements and size of the brick
     * @param point The placements of the bricks
     * @param size The length of the bricks
     */
/*method to arrange the ClayBrick in the game*/
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    /**
     * @param pos  position of the bricks
     * @param size size of the bricks
     * @return will return the size and position of the brick in a rectangle shape
     */
/*a subclass that provide a specific implementation of makeBrickFace that is already in Brick*/
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * @return will return the design of the brick
     */
/*a subclass that provide a specific implementation of getBrick that is already in Brick*/
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
