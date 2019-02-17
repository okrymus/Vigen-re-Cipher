import javax.swing.*;
// driver
// Last Modified: 3/5/16

public class driver extends JApplet{
    public static void main(String[] args) {
        
        GUI gui = new GUI();

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(300, 200);
        gui.setVisible(true);
    }
}
