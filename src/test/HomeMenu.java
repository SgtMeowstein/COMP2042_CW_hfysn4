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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


/** represents the home menu class
 * @author Siti Khadijah
 * @version 2.0
 * @since 1.0
 */
public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener
{
    /** string to store the greetings for the game
     */
    private static final String GREETINGS = "Welcome to:";

    /** string to store the game title for the game
     */
    private static final String GAME_TITLE = "Brick Destroy Master";

    /** string to store the credits for the game
     */
    private static final String CREDITS = "Version 2.0";

    /** string to store the start text for the button in the game
     */
    private static final String START_TEXT = "Start";

    /** string to store the info text for the button in the game
     */
    private static final String INFO_TEXT = "Instructions";

    /** string to store the exit text for the button in  the game
     */
    private static final String EXIT_TEXT = "Exit";


    /** for the background colour of the home menu
     */
    private static final Color BG_COLOR = new Color(0, 0, 0);

    /** for the border colour of the home menu
     */
    private static final Color BORDER_COLOR = new Color(0, 0, 0);//black

    /** for the dash border colour of the home menu
     */
    private static final Color DASH_BORDER_COLOR = new  Color(255, 228, 0);//school bus yellow

    /** for the text colour of the home menu
     */
    private static final Color TEXT_COLOR = new Color(255, 255, 255);//Lemon Yellow

    /** for the button colour of the home menu
     */
    private static final Color BUTTON_COLOR = new Color(0, 0, 0);

    /** for the clicked button colour of the home menu
     */
    private static final Color CLICKED_BUTTON_COLOR = new Color(0, 0, 0);

    /** for the clicked text colour of the home menu
     */
    private static final Color CLICKED_TEXT = Color.BLACK;

    /** for the border size of the home menu
     */
    private static final int BORDER_SIZE = 5;

    /** for the dashes on the border of the home menu
     */
    private static final float[] DASHES = {12,6};

    /* For the Buttons on the Home Page*/
    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle exitButton;
    private Rectangle infoButton;

    /*The borders for the buttons */
    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    /*The fonts for buttons */
    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean startClicked;
    private boolean exitClicked;
    private boolean infoClicked;


    /** For the dimension and to call
     * @param owner to call the game frame
     * @param area the size of the homeMenu
     */
    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;



        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        exitButton = new Rectangle(btnDim);
        infoButton = new Rectangle(btnDim);


        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Arial Rounded MT Bold",Font.PLAIN,25);
        gameTitleFont = new Font("Arial Rounded MT Bold",Font.BOLD,40);
        creditsFont = new Font("Consolas",Font.PLAIN,10);
        buttonFont = new Font("Arial Rounded MT Bold",Font.PLAIN,startButton.height-2);



    }


    /** to draw the 2d in the game
     * @param g carries the colour for the menu
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

    /** To design the menu interface
     * @param g2d the colour for the menu interface
     */
    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);

    }

    /** To design the button container menu interface
     * @param g2d the colour for the button
     */
    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        //the bg image
        Image picture = Toolkit.getDefaultToolkit().getImage("brick2.png");
        g2d.drawImage(picture, 0, 0, this);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);


    }

    /** To design the menu interface
     * @param g2d the colour for the text interface
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);


    }

    /** To design the button in the game
     * @param g2d the colour for the button
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D iTxtRect = buttonFont.getStringBounds(INFO_TEXT,frc);
        Rectangle2D eTxtRect = buttonFont.getStringBounds(EXIT_TEXT,frc);


        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.6);

        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);


        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(startButton);
            g2d.drawString(START_TEXT,x,y);
        }
        x = startButton.x;
        y = startButton.y + 40;

        infoButton.setLocation(x,y);

        x = (int)(infoButton.getWidth() - iTxtRect.getWidth()) / 2;
        y = (int)(infoButton.getHeight() - iTxtRect.getHeight()) / 2;

        x += infoButton.x;
        y += infoButton.y + (infoButton.height * 0.9);

        if(infoClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(infoButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(INFO_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(infoButton);
            g2d.drawString(INFO_TEXT,x,y);
        }
        x = infoButton.x;
        y = infoButton.y + 40;


        exitButton.setLocation(x,y);


        x = (int)(exitButton.getWidth() - eTxtRect.getWidth()) / 2;
        y = (int)(exitButton.getHeight() - eTxtRect.getHeight()) / 2;

        x += exitButton.x;
        y += exitButton.y + (exitButton.height * 0.9);

        if(exitClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(exitButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(EXIT_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(exitButton);
            g2d.drawString(EXIT_TEXT,x,y);
        }

    }
    /** when the mouse move
     * @param mouseEvent when the mouse click a button
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
           owner.enableGameBoard();

        }
        else if (infoButton.contains(p)){
            owner.enableInfoScreen();
        }
        else if(exitButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }
    /** when the mouse move
     * @param mouseEvent when the mouse is pressed
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            startClicked = true;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);

        }
        else if(exitButton.contains(p)){
            exitClicked = true;
            repaint(exitButton.x,exitButton.y,exitButton.width+1,exitButton.height+1);
        }
    }
    /** when the mouse move
     * @param mouseEvent when the mouse is release
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        }
        else if(exitClicked){
            exitClicked = false;
            repaint(exitButton.x,exitButton.y,exitButton.width+1,exitButton.height+1);
        }
    }

    /** when the mouse move
     * @param mouseEvent when the mouse entered nothing happens
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }
    /** when the mouse move
     * @param mouseEvent when the mouse exited nothing happens
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }
    /** when the mouse move
     * @param mouseEvent when the mouse hover over the buttons it will show the hand cusor
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p) || exitButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
