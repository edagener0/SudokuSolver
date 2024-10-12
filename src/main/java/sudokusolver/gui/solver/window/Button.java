package sudokusolver.gui.solver.window;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

public class Button extends JButton {
    public Button(Dimension size, String text) {
        super(text);
        setFont(new Font("Arial", Font.PLAIN, (int) (size.getHeight() / 2.75)));
        setPreferredSize(size);
    }
}
