package util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.Loader;

import java.io.File;
import java.io.IOException;

public class PDFStripper {
    public PDFStripper() {
        File file = GetFile.returnSelectedFile();
        try (PDDocument document = Loader.loadPDF(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            System.out.println("PDF-Inhalt: \n" + text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
