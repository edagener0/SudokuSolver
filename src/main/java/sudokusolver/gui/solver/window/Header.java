package sudokusolver.gui.solver.window;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Header extends JPanel {
    private JLabel labelTitle;
    public Header(Dimension size, String title) {
        super(new BorderLayout());
        setPreferredSize(size);
        this.labelTitle = new JLabel(title);
        this.labelTitle.setFont(new Font("Arial", Font.PLAIN, (int) (size.getHeight() / 1.5)));
        this.labelTitle.setHorizontalAlignment(JTextField.CENTER);
        add(this.labelTitle, BorderLayout.CENTER);
    }
}
