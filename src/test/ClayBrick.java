package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;


/**
 * Created by filippo on 04/09/16.
 *
 */
/*class for the ClayBrick which connects to Brick*/
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(132, 58, 29).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;





/*method to arrange the ClayBrick in the game*/
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }
/*a subclass that provide a specific implementation of makeBrickFace that is already in Brick*/
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

/*a subclass that provide a specific implementation of getBrick that is already in Brick*/
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
