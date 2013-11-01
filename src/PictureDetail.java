/**
 * Holds the details for a picture
 * @author Alec Lombardo, Alec Li
 * @version 1.0
 */
public class PictureDetail {
	private String name;
	private int width, height;
	/**
	 * Constructor for a PictureDetail object
	 * @param name The filename of the picture
	 * @param width The width of the picture
	 * @param height The height of the picture
	 */
	public PictureDetail(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}
	/**
	 * Default no args constructor for a PictureDetail object. Sets the filename
	 * to "" and height and width to 0.
	 */
	public PictureDetail() {
		this("",0,0);
	}
	/**
	 * Gets back the filename of the picture
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the filename of the picture
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the width of the picture
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * Sets the width of the picture
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * Gets the height of the picture
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * Sets the height of the picture
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
}
