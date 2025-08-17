package ui;
import util.Development;

import javax.swing.*;


public class Window {

    public static JFrame frame = new JFrame("MLScan" + Development.isInDevelopment());
    static ButtonGenerate button = new ButtonGenerate();
    static FileField fileField = new FileField();
    static Console console = new Console();

    public static void run_window() {
        // Development development = new Development();

        frame.setSize(870, 600);

        frame.add(button.generate);
        frame.add(fileField.filePathField);
        frame.add(fileField.browseButton);
        frame.add(console.label);

        frame.setLayout(null);
        frame.setVisible(true);
    }
}
