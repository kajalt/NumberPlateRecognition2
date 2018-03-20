import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ConvertToGray{
     BufferedImage gray;

     public ConvertToGray(File img) {
         try {
             BufferedImage image = ImageIO.read(img);

             gray = new BufferedImage(image.getWidth(),image.getHeight(),
              BufferedImage.TYPE_BYTE_GRAY);

             ColorConvertOp op = new ColorConvertOp(
              image.getColorModel().getColorSpace(),
              gray.getColorModel().getColorSpace(),null);
             op.filter(image,gray);
			 File out=new File("current.jpg");
				 try{
				 ImageIO.write(gray,"jpg",out);
				 }catch(IOException e)
				 {
				 }
         } catch (IOException ioe) {      }
     }
}