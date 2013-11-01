import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Reads the picture data to prepare for storage in a PictureDetail object
 * @author Alec Lombardo
 * @version 1.0
 */
public class PictureReader {
	/**
	 * Opens the picture at the given file path and finds its height and width.
	 * It then returns a PictureDetail object containing the needed information.
	 * @param filePath The path of the picture
	 * @return A picture detail object with all of its data entered
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static PictureDetail readPicture(String filePath)
	throws IOException, FileNotFoundException {
	    BufferedImage buff;
	    buff = ImageIO.read(new File(filePath));       
    
        int width = buff.getWidth();
        int height = buff.getHeight();
        
        PictureDetail det = new PictureDetail(filePath, width, height);
		return det;
		
	}
}