package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.Window;


public class ButtonGenerate {
    static JButton generate = new JButton("Generate");
    Window window = new Window();

    // TODO: Change x and y to be updated through window / button width and height
    int x = 870 / 2 - 290 / 2;
    int y = 450;

    public ButtonGenerate() {

        generate.setBounds(x, y, 290, 90);
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
