package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;


/** represents the brick class
 * @author Siti Khadijah
 * @version 2.0
 * @since 1.0
 */
//class for brick
abstract public class Brick  {

    /** The variables for the minimum crack on the Cement Brick
     */
    public static final int MIN_CRACK = 1;

    /** The variables for the depth of the crack
     */
    public static final int DEF_CRACK_DEPTH = 1;

    /** The variable for amount of bricks
     */
    public static final int DEF_STEPS = 35;


    /** The variable and value for the impact of the ball to the brick from above
     */
    public static final int UP_IMPACT = 100;

    /** The variable and value for the impact of the ball to the brick from below
     */
    public static final int DOWN_IMPACT = 200;

    /** The variable and value for the impact of the ball to the brick from the left
     */
    public static final int LEFT_IMPACT = 300;

    /** The variable and value for the impact of the ball to the brick from right
     */
    public static final int RIGHT_IMPACT = 400;


    /** Make the ball hit in random
     * @return A random returns
     */
    public static Random getRnd() {
        return rnd;
    }

    /** Variable for the Random function
     */
    private static Random rnd;

    /** String for the name of the Bricks
     */
    private String name;
    Shape brickFace;

    /** Color for the borders of the bricks
     */
    private Color border;

    /** Color for the inner part of the bricks
     */
    private Color inner;

    /** the maximum strength when the ball hits the brick
     */
    private int fullStrength;

    /** strength of the brick
     */
    private int strength;

    /** to check if the brick is broken or not
     */
    private boolean broken;

    /** Sets the bricks Position, colour and size
     * @param name Type of bricks
     * @param pos the position of the bricks
     * @param size size of the bricks
     * @param border the border colour for the bricks
     * @param inner the inner colour for the bricks
     * @param strength the strength of the bricks
     */
//method for the brick
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /** Size and shape of the brick
     * @param pos position of the bricks
     * @param size size of the bricks
     * @return Shape returns the position and size of the bricks
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * @param point if the ball hits the brick in a certain point it will break
     * @param dir the direction of the ball hitting the brick
     * @return if the brick is broken it will be 0
     */
//method when the ball hit brick
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /** Sets the shape for the bricks
     * @return the shape of the brick
     */
//method for the shape of the brick
    public abstract Shape getBrick();


    /** Set the colour for border part the Bricks
     * @return A color represents the border colour for the bricks
     */
//method to colour the border of the brick
    public Color getBorderColor(){
        return  border;
    }

    /** Set the colour for the inner part of bricks
     * @return A color represents the inner colour for the bricks
     */
//method to colour the inner border of the brick
    public Color getInnerColor(){
        return inner;
    }

    /**  Represents which direction will give an impact to the brick when the ball hits
     * @param b when the ball hits the brick
     * @return the broken bricks will be gone
     */
//method what happens when the ball hits the brick and it cracks
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    /** Represents the broken bricks
     * @return boolean represents the broken bricks
     */
//when the brick is broken
    public final boolean isBroken(){
        return broken;
    }


    /** When the game restarts the strength will go back to
     *
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     *
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }



}





