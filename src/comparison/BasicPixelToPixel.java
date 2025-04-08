package comparison;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Returns TRUE OR FALSE
public class BasicPixelToPixel {

	public static void main(String[] args) throws IOException {
		
		File fileA = new File("resources/screenshots/v1/Source-Samsung.png");
		File fileB = new File("resources/screenshots/v2/Target-Samsung.png");
				
		boolean result = compareImages(fileA, fileB);
		System.out.println("Comparison result OF 2 different images:"+result);
		
		//Comparing same image
		System.out.println("Comparison result OF 2 same images:"+compareImages(fileA, fileA));
	}

	
	public static boolean compareImages(File fileA, File fileB) throws IOException {
	    BufferedImage imgA = ImageIO.read(fileA);
	    BufferedImage imgB = ImageIO.read(fileB);

	    // Check dimensions first
	    if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
	        return false;
	    }

	    for (int y = 0; y < imgA.getHeight(); y++) {
	        for (int x = 0; x < imgA.getWidth(); x++) {
	            if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
	                return false;
	            }
	        }
	    }
	    return true;
	}
}
