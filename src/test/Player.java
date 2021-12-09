/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package test;

import java.awt.*;


/** Represents the player class
 * @author Siti Khadijah Binti Norzafri
 * @version 2.0
 * @since 1.0
 *
 */
public class Player {


    /** the border colour for the player rectangle
     */
    public static final Color BORDER_COLOR = new Color(0, 0, 0, 255);

    /** the inner colour for the player rectangle
     */
    public static final Color INNER_COLOR = new Color(128, 107, 175, 255);


    /** define the movement amount
     */
    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;


    /** the design for the player
     * @param ballPoint the position of the ball
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param container the container for the rectangle
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /** to design the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @return will return the rectangle
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /** the impact of the ball when it hits the brick
     * @param b when the ball hit the brick
     * @return return the ball to its position
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
    }

    /** movement of the player rectangle
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /** for the player to move left
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /** for the player to move right
     */
    public void movRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /** when the player stop
     */
    public void stop(){
        moveAmount = 0;
    }

    /** the shape for the player
     * @return will return the shape of the players rectangle
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /** for the movement of the player
     * @param p the movement of the player
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}
