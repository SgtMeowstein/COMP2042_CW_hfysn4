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

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;


/** represents the DebugPanel class
 * @author Siti Khadijah
 * @version 2.0
 * @since 1.0
 */
public class DebugPanel extends JPanel {

    /** the BackGround colour for the DebugPanel
     */
    private static final Color DEF_BKG = Color.WHITE;


    /** the speed of the level
     */
    private JButton skipLevel;

    /** the speed of the ball
     */
    private JButton resetBalls;

    /** the variable for the ball speed horizontally
     */
    private JSlider ballXSpeed;

    /** the variable for the ball speed vertically
     */
    private JSlider ballYSpeed;

    /** to create a wall for the sliders
     */
    private Wall wall;

    /** Interface of the panel
     * @param wall this makes a new wall where the slider will allocated
     */
    public DebugPanel(Wall wall){

        this.wall = wall;

        initialize();

        skipLevel = makeButton("Skip Level",e -> wall.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> wall.resetBallCount());

        ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    /**
     *
     */
    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /** The Button in the Debug Panel
     * @param title To put the title on the debug panel
     * @param e For the actionlistener to see if the user click the button or not
     * @return will close the window
     */
    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    /** The slider to control the speed of the ball
     * @param min the minimum value it will go
     * @param max the maximum value it can go
     * @param e when the value changes
     * @return will close the window
     */
    private JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    /** Represents the value for the speed of the ball
     * @param x the speed of the ball moving horizontally
     * @param y the speed of the ball moving vertically
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

}
