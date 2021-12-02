package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

//class for the cement brick
public class CementBrick extends Brick {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(103, 103, 103);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

//variable for the crack on the brick and the shpe of the brick
    private Crack crack;
    private Shape brickFace;

//method for the placements of the cement bricks
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(CementBrick.this, DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.brickFace;
    }

/*a subclass that provide a specific implementation of makeBrickFace that is already in Brick*/
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }
/*a subclass that provide a specific implementation of makeBrickFace that is already in Brick*/
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

/*a subclass that provide a specific implementation of makeBrickFace that is already in Brick*/
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
