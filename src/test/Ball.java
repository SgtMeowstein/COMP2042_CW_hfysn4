package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 *
 */
abstract public class Ball {

//variable for the ball shape
    private Shape ballFace;


    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

//variable for the ball colour
    private Color border;
    private Color inner;

//variable for the speed of the ball
    private int speedX;
    private int speedY;

    //method for the ball position on the player
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

    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

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
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    //method to set the speed of the ball moving horizontal
    public void setXSpeed(int s){
        speedX = s;
    }

    //method to set up the speed of the ball moving vertical direction
    public void setYSpeed(int s){
        speedY = s;
    }

    //method to set up the ball to bounce back in a horizontal direction
    public void reverseX(){
        speedX *= -1;
    }

    //method to set up the ball to bounce back in a vertical direction
    public void reverseY(){
        speedY *= -1;
    }

    //method to set up the ball border colour
    public Color getBorderColor(){
        return border;
    }

    //method to set up the ball inner border colour
    public Color getInnerColor(){
        return inner;
    }

    //method to set up the ball position on center
    public Point2D getPosition(){
        return center;
    }

    //method to set up the speed of the ball
    public Shape getBallFace(){
        return ballFace;
    }

    //method to make the ball move
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }


}
