import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
class BlackPoints extends JPanel {

	public int t=0;
	
	public static String image = Reader.image;

	public static BufferedImage readImage(String fileLocation) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileLocation));
		} catch (IOException e) {
		}
		return img;
	}

	public static int image() {

		BufferedImage image1 = readImage(image);
		int width = image1.getWidth();

		return width;
	}

	public static int takeNumberOfDesiredPixels(){
		BufferedImage image22 = readImage(image);
		
		int t = 0; // how many desired points existed ?
		int width = image22.getWidth();
		int height = image22.getHeight();
		
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				int pixel = image22.getRGB(i, j);
				int red = (pixel >> 16) & 0x000000FF;
				int green = (pixel >> 8) & 0x000000FF;
				int blue = (pixel) & 0x000000FF;

				if (red <=64 && green <=64 && blue <=64) { // for the taking the dark pixels
					t++;

					
				}
			}
		}
		
		return t;
		
		
	}
	
	/** Take x coordinates of desired pixels  **/
	public static int[] getXCoordinates() {
		BufferedImage image22 = readImage(image);
	
		int width = image22.getWidth();
		int height = image22.getHeight();
		
		int[] ArrayX = new int[takeNumberOfDesiredPixels()];

		int d = -1;
		
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {

				int pixel = image22.getRGB(w, h);
				
				int red = (pixel >> 16) & 0x000000FF;
				/** 
				 * 	Shift all bits to the rigt as 16 bit
				 * then multiply or sth like filter with
				 * 0x000000FF then get the only last 8 bit 
				 * which is the red information 
				 * between 0 and 255
				 * **/
				int green = (pixel >> 8) & 0x000000FF;
				/** 
				 * 	Shift all bits to the right as 8 bit
				 * then multiply or sth like filter with
				 * 0x000000FF then get the only last 8 bit 
				 * which is the green information 
				 * between 0 and 255
				 * **/
				int blue = (pixel) & 0x000000FF;
				/**
				 * multiply or sth like filter with
				 * 0x000000FF then get the only last 8 bit 
				 * which is the blue information 
				 * between 0 and 255
				 * **/
				if (red <=64 && green <=64 && blue <=64) {
					// for the take Black pixels
					// Black pixel has RGB value is zero

					d++;
					ArrayX[d] = w;

				}
			}
		}

		return ArrayX;

	}

	/** Take y coordinates of desired pixels  **/
	public static int[] getYCoordinates() {
		BufferedImage image22 = readImage(image);

		
		int width = image22.getWidth();
		int height = image22.getHeight();

		
		int[] ArrayY = new int[takeNumberOfDesiredPixels()];

		int d = -1;
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {

				int pixel = image22.getRGB(w, h);
				int red = (pixel >> 16) & 0x000000FF;
				int green = (pixel >> 8) & 0x000000FF;
				int blue = (pixel) & 0x000000FF;

				if (red <=64 && green <=64 && blue <=64) {
                                d++;
    				ArrayY[d] = h;
				}
			}
		}
                return ArrayY;

	}

	@Override
	protected void paintComponent(Graphics g) {
            
                BufferedImage x = readImage(image);
                BufferedImage sample = new BufferedImage(x.getWidth(),x.getHeight(), BufferedImage.TYPE_INT_RGB);
                
                Graphics gd = sample.createGraphics();
                
                int c[] =getXCoordinates();
                int d[] =getYCoordinates();
                gd.setColor(Color.white);
                gd.fillRect(0, 0,sample.getWidth(),sample.getHeight());
                gd.setColor(Color.black);
                for (int y = 0; y < c.length; y++) {
                    
                    gd.drawLine(c[y], d[y], c[y], d[y]);
                }
                gd.dispose();
                File file = new File("current.png");
                try {
                    ImageIO.write(sample, "png", file);
                } catch (IOException ex) {
                    Logger.getLogger(BlackPoints.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
}