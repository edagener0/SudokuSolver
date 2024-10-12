package sudokusolver.gui.solver.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JTextField;

import sudokusolver.gui.solver.exception.InvalidDigit;

import javax.swing.BorderFactory;


public class Cell extends JTextField {
    private Color background;

    public Cell(Dimension size, Color backgroundColor, Color borderColor) {
        this.background = backgroundColor;

        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Arial", Font.PLAIN, (int) (size.getWidth() / 1.75)));
        setBorder(BorderFactory.createLineBorder(borderColor));
        setBackground(this.background);
        setPreferredSize(size);
    }

    public void setColor(Color backgroundColor) {
        this.background = backgroundColor;
        setBackground(this.background);
    }

    public void setNumber(int number) {
        if (number == 0) {
            this.setText("");
        } else {
            this.setText(String.format("%d", number));
        }
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
