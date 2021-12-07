package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


/** represents the ball class
 * @author Siti Khadijah
 * @version 2.0
 * @since 1.0
 */
abstract public class Ball {

//variable for the ball shape
    /** Represents the ball shape
     *
     */
    private Shape ballFace;


    /** Represents the position of the ball
     * @param center The direction of the ball
     */
    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

//variable for the ball colour
    /** Represents the colour of the ball
     * @param border The border colour of the ball
     * @param inner The inner colour of the ball
     */
    private Color border;
    private Color inner;

//variable for the speed of the ball
    /** Represents the speed of the ball
     * @param speedX The speed of the ball moving horizontal
     */
    private int speedX;

    /** Represents the speed of the ball
     * @param speedY The speed of the ball moving vertical
     */
    private int speedY;

    //method for the ball position on the player

    /**
     * @param center The direction of the ball
     * @param radiusA The radius of the ball
     * @param radiusB The radius of the ball
     * @param inner  The inner colour of the ball
     * @param border The border colour of the ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * @param center The direction of the ball
     * @param radiusA The radius of the ball
     * @param radiusB The radius of the ball
     * @return
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /** Represents the movement of the ball
     *
     */
//method to make the ball move
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }
//method to set up the speed of the ball

    /** Represents the speed of the ball moving
     * @param x control the speed of the ball horizontal
     * @param y control the speed of the ball vertically
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /** Represents the speed of the ball moving horizontally
     * @param s controls the speed of the ball moving horizontally
     */
    //method to set the speed of the ball moving horizontal
    public void setXSpeed(int s){
        speedX = s;
    }

    /** Represents the speed of the ball moving vertically
     * @param s controls the speed of the ball moving vertically
     */
    //method to set up the speed of the ball moving vertical direction
    public void setYSpeed(int s){
        speedY = s;
    }

    /** Represents the speed of the ball when it bounce back horizontally
     */
    //method to set up the ball to bounce back in a horizontal direction
    public void reverseX(){
        speedX *= -1;
    }

    /** Represents the speed of the ball when it bounce back vertically
     */
    //method to set up the ball to bounce back in a vertical direction
    public void reverseY(){
        speedY *= -1;
    }

    /** Represents the border colour of the ball
     * @return  will return the colour of the border for the ball
     */
    //method to set up the ball border colour
    public Color getBorderColor(){
        return border;
    }

    /** Represents the inner colour of the ball
     * @return  will return the inner colour of the ball
     */
    //method to set up the ball inner border colour
    public Color getInnerColor(){
        return inner;
    }

    /** Represents the position of th ball
     * @return call back the position method for the ball
     */
    //method to set up the ball position on center
    public Point2D getPosition(){
        return center;
    }

    /** Represents the call back function for the shape for the ball shape
     * @return  call back the shape of the ball
     */
    //method to set up the speed of the ball
    public Shape getBallFace(){
        return ballFace;
    }

    /** Represents the movement of the ball
     * @param p holds the value of the ball movement
     */
    //method to make the ball move
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /** This positions the ball to the center
     * @param width the position of the ball horizontally
     * @param height the position of the ball vertically
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * @return the speed of the ball moving horizontally
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * @return the speed of the ball moving vertically
     */
    public int getSpeedY(){
        return speedY;
    }


}
