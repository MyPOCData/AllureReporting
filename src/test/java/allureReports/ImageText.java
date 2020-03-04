package allureReports;

import java.io.File;

public class ImageText {
	
	static String PATH = System.getProperty("user.dir");
	static File imageLocation = new File(PATH + File.separator + "source/images");
	static File textLocation = new File(PATH + File.separator + "source/textFiles");
	
	public static String getTextFromImage(String pathOfTesseract, File imageFileName, File textFileName) {	
		try {
			Runtime.getRuntime().exec(String.format("%stesseract %s %s -l eng", pathOfTesseract, imageLocation + File.separator + imageFileName, textLocation + File.separator + textFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textLocation + File.separator + textFileName;
	}
	
	public static void main(String args[]) {	
		String path = "/usr/local/Cellar/tesseract/4.1.1/bin/";
		File image = new File("Image.png");
		File text = new File("Image");
		getTextFromImage(path,image,text);
	}
}
