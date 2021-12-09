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
import java.awt.geom.Point2D;
import java.util.Random;


/** Represents the steelbrick class
 * @author Siti Khadijah Binti Norzafri
 * @version 2.0
 * @since 1.0
 *
 */
public class SteelBrick extends Brick {


    /** string to name the brick
     */
    private static final String NAME = "Steel Brick";


    /** define the inner colour of the brick
     */
    private static final Color DEF_INNER = new Color(202, 204, 206);

    /** define the border colour of the brick
     */
    private static final Color DEF_BORDER = Color.BLACK;

    /** define the strength of the brick
     */
    private static final int STEEL_STRENGTH = 1;

    /** define the probability of the ball hitting the brick
     */
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    /**Determine the size and position of the bricks
     * @param point the position of the brick
     * @param size the size of the brick
     */
    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }

    /**
     * @param pos  position of the bricks
     * @param size size of the bricks
     * @return the shape that will be for the bricks which is Rectangle
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /** Creates the shape of the brick
     * @return it will return the shape and design of the brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /** Represents the impact when the ball hits the brick
     * @param point if the ball hits the brick in a certain point it will break
     * @param dir the direction of the ball hitting the brick
     * @return if the ball hits the brick it will crack and when it hits the 2nd time it will go break
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }


    /**the impact when the ball hits the steel
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

}
