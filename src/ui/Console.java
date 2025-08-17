package ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

public class Console {
    JLabel label = new JLabel();

    public Console() {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(30, 190, 800, 30);
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setVisible(true);
        // log("Console label", true, false);
    }

    public void log(String message, boolean error, boolean finish) {
        label.setText(message);

        if (finish) {
            if (error) {
                label.setBackground(Color.RED);
            } else {
                label.setBackground(Color.GREEN);
            }
        } else {
            label.setBackground(Color.LIGHT_GRAY);
        }
    }
}
