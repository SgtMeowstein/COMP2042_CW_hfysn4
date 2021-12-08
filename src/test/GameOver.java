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


public class GameOver extends JComponent implements MouseListener, MouseMotionListener
{
    private static final String HEADER = "GAME OVER";
    private String SCORE;
    private String[] user;
    private int[] score;
    private String username;
    private int player_score;
    private static final String RETRY_TEXT = "RETRY";
    private static final String HOME_TEXT = "HOME";
    private static final String LEADERBOARD_TEXT = "BOARD";
    private static final String SAVE_TEXT = "SAVE";




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
    private Rectangle retryButton;
    private Rectangle boardButton;
    private Rectangle saveButton;


    /*The fonts for buttons */
    private Font buttonFont;
    private Font titleFont;
    private Font textFont;



    private GameFrame owner;

    private boolean homeClicked;
    private boolean retryClicked;
    private boolean boardClicked;
    private boolean saveClicked;




    public GameOver(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;



        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        homeButton = new Rectangle(btnDim);
        retryButton = new Rectangle(btnDim);
        saveButton = new Rectangle(btnDim);
        boardButton = new Rectangle(btnDim);






        textFont = new Font("Arial Rounded MT Bold",Font.BOLD,16);
        titleFont= new Font("Arial Rounded MT Bold",Font.BOLD,18);
        buttonFont = new Font("Arial Rounded MT Bold",Font.BOLD,18);





    }


    public void paint(Graphics g){

        SCORE = String.format("%d", player_score=owner.getScore());
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

        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);


    }

    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D titleRect = titleFont.getStringBounds(HEADER,frc);
        Rectangle2D scoreRect = titleFont.getStringBounds(SCORE,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - titleRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 5);

        g2d.setFont(titleFont);
        g2d.drawString(HEADER,sX,sY);

        sX = (int)(menuFace.getWidth() - scoreRect.getWidth()) / 2;
        sY +=30;
        g2d.drawString(SCORE,sX,sY);


    }

    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(HOME_TEXT,frc);
        Rectangle2D txtRetry = buttonFont.getStringBounds(RETRY_TEXT,frc);
        Rectangle2D txtBoard = buttonFont.getStringBounds(LEADERBOARD_TEXT,frc);
        Rectangle2D txtSave = buttonFont.getStringBounds(SAVE_TEXT,frc);



        g2d.setFont(buttonFont);

        int x = (menuFace.width - homeButton.width) / 2;
        int y = ((menuFace.height - homeButton.height) * 1/4 + 50);

        homeButton.setLocation(x,y);
        x = (int)(homeButton.width- txtRect.getWidth()) / 2;
        y = (int)(homeButton.height- txtRect.getHeight()) / 2;

        x += homeButton.x;
        y +=(int) homeButton.y + (homeButton.height * 0.8);


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

        x = (menuFace.width - retryButton.width)/2;
        y = (homeButton.y + 40);

        retryButton.setLocation(x,y);

        x = (int)(retryButton.width - txtRetry.getWidth()) / 2;
        y = (int)(retryButton.height - txtRetry.getHeight()) / 2;

        x += retryButton.x;
        y +=(int) retryButton.y + (retryButton.height * 0.7);



        if(retryClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(retryButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(RETRY_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(retryButton);
            g2d.drawString(RETRY_TEXT,x,y);
        }

        x = (menuFace.width - boardButton.width)/2;
        y = retryButton.y + 40;

        boardButton.setLocation(x,y);

        x = (int)(boardButton.getWidth() - txtBoard.getWidth()) / 2;
        y = (int)(boardButton.getHeight() - txtBoard.getHeight()) / 2;

        x += boardButton.x;
        y +=(int) boardButton.y + (boardButton.height * 0.7);

        if(boardClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(boardButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(LEADERBOARD_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(boardButton);
            g2d.drawString(LEADERBOARD_TEXT,x,y);
        }

        x = (menuFace.width - saveButton.width)/2;
        y = boardButton.y + 40;

        saveButton.setLocation(x,y);

        x = (int)(saveButton.getWidth() - txtSave.getWidth()) / 2;
        y = (int)(saveButton.getHeight() - txtSave.getHeight()) / 2;

        x += saveButton.x;
        y +=(int) saveButton.y + (saveButton.height * 0.7);

        if(saveClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(saveButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(SAVE_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(saveButton);
            g2d.drawString(SAVE_TEXT,x,y);
        }


    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeButton.contains(p)){
            owner.enableHomeMenu(2);

        }
        else if(retryButton.contains(p)){
            owner.enableretry();

        }
        /*else if(boardButton.contains(p)){
            owner.enableleaderboard();

        }*/
        else if(saveButton.contains(p)){
            user = owner.getplayeruser();
            score = owner.getplayerscore();
            username = JOptionPane.showInputDialog("Please Input Name:", "My Name");
            if (user != null) {
                int x;
                for (x = 0; x < 6; x++) {
                    if (player_score > score[x])
                        break;
                }

                for (int y = 4 ; y >= x; y--) {
                    score[y + 1] = score[y];
                    user[y + 1] = user[y];
                }
                user[x] = username;
                score[x] = player_score;
                owner.WriteFile(score, user);
                repaint();
            }
        }
        else{
            return;
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

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
        if(homeButton.contains(p)||retryButton.contains(p)|| boardButton.contains(p)||saveButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
