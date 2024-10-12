package sudokusolver.gui.solver.window;

import javax.swing.JOptionPane;

public class MsgBox extends JOptionPane{
    public static void error(String text) {
        showMessageDialog(null, text);
    }
}
