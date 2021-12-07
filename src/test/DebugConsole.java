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
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;



/** Represents the DebugConsole class
 * @author Siti Khadijah Binti Norzafri
 * @version 2.0
 * @since 1.0
 *
 */
public class DebugConsole extends JDialog implements WindowListener{

//
    private static final String TITLE = "Debug Console";


    /** the variable for the JFrame
     */
    private JFrame owner;

    /** the variable for the DebugPanel
     */
    private DebugPanel debugPanel;

    /** the variable for the gameBoard
     */
    private GameBoard gameBoard;

    /** the variable for the wall
     */
    private Wall wall;


    /** Represent the DebugConsole where error message will be seen
     * @param owner when owner is called it Game frame will appear
     * @param wall will call the wall
     * @param gameBoard will spawn the gameBoard
     */
    public DebugConsole(JFrame owner,Wall wall,GameBoard gameBoard){

        this.wall = wall;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        debugPanel = new DebugPanel(wall);
        this.add(debugPanel,BorderLayout.CENTER);


        this.pack();
    }

    /**Represents the the interface of the DebugConsole
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }


    /** Where the Debug Console will appear
     */
    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }


    /** Represent the windowEvent
     * @param windowEvent the action of the window
     */
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /** When the Debug Console is closing
     * @param windowEvent the action of the window
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    /** When the Debug Console is Closed
     * @param windowEvent the action of the window
     */
    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /** When Debug Console is activated
     * @param windowEvent the action of the window
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = wall.ball;
        debugPanel.setValues(b.getSpeedX(),b.getSpeedY());
    }

    /** When Debug Console is deactivated
     * @param windowEvent the action of the window
     */
    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
