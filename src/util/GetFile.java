package util;

import ui.Window;

import javax.swing.*;
import java.io.File;

public class GetFile {
    public static File selectedFile;

    public GetFile(JFileChooser fileChooser, JTextField filePathField) {
        fileChooser.setDialogTitle("Choose pdf");

        int result = fileChooser.showOpenDialog(Window.frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    public static File returnSelectedFile() {
        return selectedFile;
    }
}
