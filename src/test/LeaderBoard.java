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


public class LeaderBoard extends JComponent implements MouseListener, MouseMotionListener
{
    private static final String HEADER = "LEADERBOARD";
    private String[] user;
    private int[] score;
    private static final String HOME_TEXT = "HOME";



    private static final Color BG_COLOR = new Color(0, 0, 0);
    private static final Color BORDER_COLOR = new Color(0, 0, 0);//black
    private static final Color DASH_BORDER_COLOR = new  Color(255, 228, 0);//school bus yellow
    private static final Color TEXT_COLOR = new Color(255, 255, 255);//Lemon Yellow
    private static final Color BUTTON_COLOR = new Color(0, 0, 0);
    private static final Color CLICKED_BUTTON_COLOR = new Color(0, 0, 0);
    private static final Color CLICKED_TEXT = Color.BLACK;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    /* For the Buttons on the Home Page*/
    private Rectangle menuFace;
    private Rectangle homeButton;


    /*The borders for the buttons */
    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    /*The fonts for buttons */
    private Font homeFont;
    private Font titleFont;
    private Font textFont;


    private GameFrame owner;

    private boolean homeClicked;



    public LeaderBoard(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;



        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        homeButton = new Rectangle(btnDim);



        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);


        textFont = new Font("Arial Rounded MT Bold",Font.BOLD,16);
        titleFont= new Font("Arial Rounded MT Bold",Font.BOLD,18);
        homeFont = new Font("Arial Rounded MT Bold",Font.BOLD,18);





    }


    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


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

    private void drawText(Graphics2D g2d){

        user = owner.getplayeruser();
        score = owner.getplayerscore();

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D titleRect = titleFont.getStringBounds(HEADER,frc);


        int sX,sY;

        sX = (int)(menuFace.getWidth() - titleRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(titleFont);
        g2d.drawString(HEADER,sX,sY);


        for (int x=0; x<5; x++) {
            sX = 80;
            sY += 20;
            g2d.setFont(titleFont);
            g2d.drawString(String.format("%d",x+1), sX, sY);
            sX += 30;
            g2d.setFont(titleFont);
            g2d.drawString("| "+user[x], sX, sY);
            sX += 200;
            g2d.setFont(titleFont);
            g2d.drawString(String.format("| %d",score[x]), sX, sY);
        }



    }

    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = homeFont.getStringBounds(HOME_TEXT,frc);


        g2d.setFont(homeFont);

        int x = (menuFace.width - homeButton.width) / 2;
        int y =(int) ((menuFace.height - homeButton.height) * 0.9);

        homeButton.setLocation(x,y);

        x = (int)(homeButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(homeButton.getHeight() - txtRect.getHeight()) / 2;

        x += homeButton.x;
        y += homeButton.y + (homeButton.height * 0.7);


        if(homeClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(homeButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(HOME_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(homeButton);
            g2d.drawString(HOME_TEXT,x,y);
        }
        x = homeButton.x;
        y = homeButton.y + 40;

        y *=1.2;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeButton.contains(p)){
            owner.enableHomeMenu(4);

        } else{
            return;
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeButton.contains(p)){
            homeClicked = true;
            repaint(homeButton.x,homeButton.y,homeButton.width+1,homeButton.height+1);

        }
        else{
            return;
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(homeClicked ){
            homeClicked = false;
            repaint(homeButton.x,homeButton.y,homeButton.width+1,homeButton.height+1);
        }else{
            return;
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
