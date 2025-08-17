

## Import PDFbox into Intellij IDEA 
1. Open Module Settings
2. Left: Libraries 
3. Add -> Java -> Downloaded File (pdfbox-app-2.0.xx.jar) on (https://pdfbox.apache.org/download.html)
4. ~~~ 
    import org.apache.pdfbox.pdmodel.PDDocument; 
    import org.apache.pdfbox.text.PDFTextStripper;
    import org.apache.pdfbox.Loader
   
    File file = GetFile.returnSelectedFile();
    try (PDDocument document = Loader.loadPDF(file)) {
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);

        System.out.println("PDF-Inhalt: \n");
    } catch (IOException e) {
        e.printStackTrace();
    }
   ~~~