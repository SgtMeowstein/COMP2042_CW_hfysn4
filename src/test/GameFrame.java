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
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/** represents the GameFrame
 * @author Siti Khadijah
 * @version 2.0
 * @since 1.0
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    /** string represents the title
     */
    private static final String DEF_TITLE = "Brick Destroy";
    private final InfoPage infoPage;


    private GameBoard gameBoard;
    private HomeMenu homeMenu;
    private GameOver gameOver;
    private LeaderBoard leaderBoard;
    private int player_score;
    private String username;
    private String[] user = {"","","","","",""};
    private int[] score = {0,0,0,0,0,0};

    private boolean gaming;

    /**the method to call the other methods
     */
    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        gameBoard = new GameBoard(this);

        homeMenu = new HomeMenu(this,new Dimension(450,300));

        infoPage = new InfoPage(this, new Dimension(450, 300));

        gameOver = new GameOver(this, new Dimension(450, 300));

        leaderBoard = new LeaderBoard(this, new Dimension(450, 300));



        this.add(homeMenu,BorderLayout.CENTER);

        this.setUndecorated(true);


    }

    /** this method will get the score
     * @return  it will return the player_score
     */
    public int getScore(){return player_score;}

    /** this method will display the score in the game
     * @param score player score in the game
     */
    public void setScore(int score){this.player_score = score;}

    /** to initialize the control of the mouse
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /** this is to transition from home to the game
     */
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /** this is to transition from home to the info page
     */
    public void enableInfoScreen(){
        this.dispose();
        this.remove(homeMenu);
        this.add(infoPage,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /** this is to transition from game to the game over page
     */
    public void enableGameOver(){
        ReadFile();
        this.dispose();
        this.remove(gameBoard);
        this.add(gameOver,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /** this is to transition from game to the home page
     * @param from to give them numbers to call back
     */
    public void enableHomeMenu(int from){
        if(from==1) {
            this.dispose();
            this.remove(gameBoard);
            this.add(homeMenu, BorderLayout.CENTER);
            this.setUndecorated(false);
            initialize();
            /*to avoid problems with graphics focus controller is added here*/
            this.addWindowFocusListener(this);
        }
        else if (from==2){
            this.dispose();
            this.remove(gameOver);
            this.add(homeMenu, BorderLayout.CENTER);
            this.setUndecorated(false);
            initialize();
            /*to avoid problems with graphics focus controller is added here*/
            this.addWindowFocusListener(this);
        }
        else if(from==3){
            this.dispose();
            this.remove(infoPage);
            this.add(homeMenu, BorderLayout.CENTER);
            this.setUndecorated(false);
            initialize();
            /*to avoid problems with graphics focus controller is added here*/
            this.addWindowFocusListener(this);
        }
        else if(from==4){
            this.dispose();
            this.remove(leaderBoard);
            this.add(homeMenu, BorderLayout.CENTER);
            this.setUndecorated(false);
            initialize();
            /*to avoid problems with graphics focus controller is added here*/
            this.addWindowFocusListener(this);
        }

    }

    /** this is to transition from game over page to the game page
     */
    public void enableretry(){
        this.dispose();
        this.remove(gameOver);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /** this is to transition from game over page to the leaderboard page
     */
    public void enableleaderboard(){
        ReadFile();
        this.dispose();
        this.remove(gameOver);
        this.add(leaderBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();

        this.addWindowFocusListener(this);

    }

    /** to get the score after the game
     * @return it will fetch the score
     */
    public int[] getplayerscore(){return score;}


    /** to get the name of the player after the game
     * @return it will fetch the name
     */
    public String[] getplayeruser(){return user;}

    /** to read the file for the leaderscore and username
     */
    public void ReadFile(){
        try{
            File leaderboard = new File("src/leaderboard.txt");
            File username = new File("src/username.txt");
            Scanner scanner1 = new Scanner(leaderboard);
            Scanner scanner2 = new Scanner(username);
            int x =0;
            while (scanner1.hasNextLine()) {
                score[x]= scanner1.nextInt();
                user[x++]= scanner2.nextLine();
            }
            scanner1.close();
            scanner2.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("txt files failed to open");
            e.printStackTrace();
        }
    }

    /** to write in the file
     * @param score the array to store the score
     * @param name the array to store the name
     */
    public void WriteFile(int[] score, String[] name){
        try {
            System.out.println("Save Succeeded");
            FileWriter leaderboardScore = new FileWriter("src/leaderboard.txt",false);
            FileWriter leaderboardName = new FileWriter("src/username.txt",false);
            int x=0;
            while (x<5) {
                leaderboardScore.write(String.format("%d\n", score[x]));
                leaderboardName.write(String.format("%s\n", name[x++]));
            }
            leaderboardScore.write(String.format("%d", score[5]));
            leaderboardName.write(String.format("%s", name[5]));
            leaderboardScore.close();
            leaderboardName.close();
        }
        catch (IOException e)
        {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    /** to position the window
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    /** when the user is in the game it will start
     * @param windowEvent the user is in the game
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /** when the user is out of the game
     * @param windowEvent when the cursor or the player is not in the game
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }
}
