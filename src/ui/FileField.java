package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileField {
    static JTextField filePathField = new JTextField();
    static JButton browseButton = new JButton("Choose...");
    static JFileChooser fileChooser = new JFileChooser();

    public static void fileField() {
        browseButton.setBounds(30, 150, 100, 30);
        filePathField.setBounds(130, 150, 700, 30);

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Choose pdf");

                int result = fileChooser.showOpenDialog(Window.frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePathField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
    }
}
