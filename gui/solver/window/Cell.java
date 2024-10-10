package gui.solver.window;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import gui.solver.exception.InvalidDigit;

import javax.swing.BorderFactory;


public class Cell extends JTextField {
    private Color background;

    public Cell(Color backgroundColor) {
        this.background = backgroundColor;

        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setBackground(this.background);
    }

    public void setColor(Color backgroundColor) {
        this.background = backgroundColor;
        setBackground(this.background);
    }

    public void setNumber(int number) {
        this.setText(String.format("%d", number));
    }

    public int getNumber() throws InvalidDigit {
        String content = this.getText();

        if (content.isBlank()) {
            return 0;
        }

        int number;

        try {
            number = Integer.parseInt(content);
        } catch (NumberFormatException e) {
            number = -1;
        }

        if (number < 1 || number > 9) {
            throw new InvalidDigit();
        }

        return number;
    }
}
