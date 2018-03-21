import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.sql.*;
import javax.swing.JFrame;
import net.sourceforge.tess4j.TesseractException;


public class Reader {
    public static String original="sample1.jpg";
    public static String image = "current.jpg";
    public static String path = "src\\";
    
	public static BufferedImage readImage(String fileLocation) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileLocation));
		}
                catch (IOException e){}
		return img;
	}
      
        	public static void Reader() {

		BlackPoints example = new BlackPoints();

		BufferedImage image1 = readImage(image);
                int width = image1.getWidth();
		int height = image1.getHeight()+30;

		JFrame frame = new JFrame("ReDraw");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(example, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setSize(width, height);
		}

                public static void main(String[] args) throws TesseractException, ClassNotFoundException{
                    ConvertToGray ctg = new ConvertToGray(new File(path+original));
                    Reader.init();
                    String input = OCR.scan();
                    System.out.println(input);
                    
                    
/*                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/numberplate","root","");
                        Statement st=con.createStatement();
                        String query="Select * from vehiclenumber where PlateNumber='"+input+"'";
                        ResultSet rs=st.executeQuery(query);
                        if(rs.next())
                        {System.out.println("yess");}
                        else
                        {System.out.println("NOOO");}
                    }
                    catch(SQLException e){
                        System.out.println(e);
                    }*/
                }
        public static void init() {
	java.awt.EventQueue.invokeLater(() -> Reader.Reader());
	}
}