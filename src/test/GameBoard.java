package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;


/** represents the Gameboard class
 * @author Siti Khadijah
 * @version 2.0
 * @since 1.0
 */
public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    /**Strings representing the continue for Pause
     */
    private static final String CONTINUE = "Continue";

    /**Strings representing the restart text for pause
     */
    private static final String RESTART = "Restart";

    /**Strings representing the exit text for pause
     */
    private static final String EXIT = "Exit";

    /**Strings representing the pause text for pause
     */
    private static final String PAUSE = "Pause Menu";

    /**Strings representing the Tutorial text for pause
     */
    private static final String INFO = "Tutorial";

    /**The size of the text
     */
    private static final int TEXT_SIZE = 30;

    /** The text colour for the menu
     */
    private static final Color MENU_COLOR = new Color(255, 255, 255);


    /** The width of the rectangle
     */
    private static final int DEF_WIDTH = 600;

    /** The height of the rectangle
     */
    private static final int DEF_HEIGHT = 450;

    /** the timer for the game
     */
    private Timer gameTimer;

    /** the variable for the wall
     */
    private Wall wall;

    /** string for message
     */
    private String message;


    /** variable for the gamefram
     */
    private GameFrame owner;

    /** to show the pause menu
     */
    private boolean showPauseMenu;


    /** the font for the menu text
     */
    private Font menuFont;

    /** rectangle for the continue button
     */
    private Rectangle continueButtonRect;

    /** rectangle for the exit button
     */
    private Rectangle exitButtonRect;

    /** rectangle for the restart button
     */
    private Rectangle restartButtonRect;

    /** rectangle for the scoreboard
     */
    private Rectangle Scoreboard;

    /** for the length of the string
     */
    private int strLen;


    /** the variable for the debugconsole
     */
    private DebugConsole debugConsole;


    /**
     * @param owner to say that the gameframe is the parent
     */
    public GameBoard(GameFrame owner){
        super();

        this.owner = owner;
        strLen = 0;
        showPauseMenu = false;



        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);


        this.initialize();
        message = "";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));

        debugConsole = new DebugConsole(owner,wall,this);
        //initialize the first level
        wall.nextLevel();

        gameTimer = new Timer(10,e ->{
            wall.move();
            wall.findImpacts();
            message = String.format("Bricks: %d Balls %d Player Score: %d",wall.getBrickCount(),wall.getBallCount(),wall.getScore());
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    owner.setScore(wall.getScore());
                    owner.enableGameOver();
                    wall.setScore(0);
                }
                wall.ballReset();
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(wall.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                }
            }

            repaint();
        });

    }


    /** to initialize the control of the mouse
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    /** draws the 2D object in the game
     * @param g the graphics for the game
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.WHITE);
        g2d.drawString(message,250,225);

        drawBall(wall.ball,g2d);

        for(Brick b : wall.bricks)
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.player,g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /** for the background of the gameplay
     * @param g2d
     */
    private void clear(Graphics2D g2d){
        g2d.fillRect(0,0,getWidth(),getHeight());
        Image picture = Toolkit.getDefaultToolkit().getImage("sky.jpg");
        g2d.drawImage(picture, 0, 0, this);

    }

    /** The brick colour and
     * @param brick the bricks
     * @param g2d get the colour for the brick
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    /** The ball colour
     * @param ball the ball
     * @param g2d the colour for the ball
     */
    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /** The colour of the player
     * @param p the variable for player
     * @param g2d colour for the player
     */
    private void drawPlayer(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /** To design the menu interface
     * @param g2d the colour for the pause interface
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**The design for gameboard
     * @param g2d the colour for the gameboard
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /** To design the pause menu interface
     * @param g2d the colour for the pause interface
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);


        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    /** To make the score appear
     */
    private void scoreboard(){
        message = String.format("Player Score: %d",wall.getScore());

    }

    /** this is for the controller
     * @param keyEvent  An event where there is keystroke is part of the component
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /** this is the key the player acn click
     * @param keyEvent used when there are key components
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_LEFT:
                wall.player.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                wall.player.movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                wall.player.stop();
        }
    }

    /** when the key is released
     * @param keyEvent When the key is released the player will stop
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.player.stop();
    }

    /** when the mouse move
     * @param mouseEvent when the mouse click a button
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            wall.ballReset();
            wall.wallReset();
            showPauseMenu = false;
            repaint();
        }
        else if(exitButtonRect.contains(p)){
           owner.enableHomeMenu(1);
        }

    }

    /** when the mouse is pressed
     * @param mouseEvent when the mouse is pressed nothing happens
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    /** when the mouse is released
     * @param mouseEvent when the mouse is released nothing happens
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    /** when the mouse is pressed entered
     * @param mouseEvent when the mouse is entered nothing happens
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    /** when the mouse is exit
     * @param mouseEvent when the mouse is exit nothing happens
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    /** when the mouse is dragged
     * @param mouseEvent when the mouse is dragged nothing happens
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /** When the mouse moved
     * @param mouseEvent when the mouse moved  it will show the hand cursor
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /** Timer stop when the user is out of the game
     */
    public void onLostFocus(){
        gameTimer.stop();
        repaint();
    }

}
