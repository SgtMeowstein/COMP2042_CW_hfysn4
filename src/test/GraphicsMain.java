
package test;

import java.awt.*;


/** This class wil start the whole game
 * @author Siti Khadijah Binti Norzafri
 * @version 2.0
 * @since 1.0
 *
 */
public class GraphicsMain {

    public static void main(String[] args){
        EventQueue.invokeLater(() -> new GameFrame().initialize());
    }

}
