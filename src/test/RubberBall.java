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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/** Represents the rubberball class
 * @author Siti Khadijah Binti Norzafri
 * @version 2.0
 * @since 1.0
 *
 */
public class RubberBall extends Ball {


    /** to define the radius of the rubber ball
     */
    private static final int DEF_RADIUS = 10;

    /** define the inner colour of the rubber ball
     */
    private static final Color DEF_INNER_COLOR = new Color(212, 227, 0);

    /** define the border colour of the rubber ball
     */
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();


    /** to position the ball
     * @param center to center the ball
     */
    public RubberBall(Point2D center){
        super(center,DEF_RADIUS,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);
    }


    /** to shape the ball
     * @param center  The direction of the ball
     * @param radiusA The radius of the ball
     * @param radiusB The radius of the ball
     * @return it will return the radius for the ball
     */
    @Override
    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {

        double x = center.getX() - (radiusA / 2);
        double y = center.getY() - (radiusB / 2);

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }
}
