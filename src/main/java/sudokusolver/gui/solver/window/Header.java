package main.java.sudokusolver.gui.solver.window;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Header extends JPanel {
    private JLabel labelTitle;
    public Header(String title) {
        super(new BorderLayout());
        this.labelTitle = new JLabel(title);
        this.labelTitle.setFont(new Font("Arial", Font.PLAIN, 40));
        this.labelTitle.setHorizontalAlignment(JTextField.CENTER);
        add(this.labelTitle, BorderLayout.CENTER);
    }
}
