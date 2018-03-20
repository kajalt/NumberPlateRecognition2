import java.io.File;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCR {
        public static String scan() throws TesseractException{
                String OCRpath = "current.png";
                    Tesseract tess = new Tesseract();
                    tess.setDatapath("src\\tessdata");
                    tess.setLanguage("eng");
                    String output = tess.doOCR(new File(OCRpath));
                    return output.replaceAll("\\s","");
                }
}