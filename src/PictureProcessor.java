/**
* This class contains the table creation algorithm.
* @author Alec Li
* @version 1.0
*/
public class PictureProcessor {
	/**
	* Processes a list of pictures into a single row table
	* @param pw Page width
	* @param spaceDist The distance between the pictures
	* @param picture An array of pictures to be put in the table
	* @param linksOn Whether or not pictures should be links
	* @return The String representation of the HTML code for the table
	*/
	public static String getTable(int pw, int spaceDist,
			PictureDetail[] picture, boolean linksOn) {
		int imagesdimension = pw - spaceDist*(picture.length-1);
		double ratiosum = 0;

		for(int i = 0; i<picture.length; i++)
		{
			int width = picture[i].getWidth();
			int height = picture[i].getHeight();
			ratiosum += (double)width/height;
		}
		double desiredheight = Math.round(100*imagesdimension/ratiosum)/100;
		//int desiredheight = (int) desiredheightpre;

		// Print <table border="0" cellpadding = "0" style="margin:auto; height:100px; border-spacing:10px 5px">
		double tableheight = desiredheight+spaceDist;
		int halfspaceDist = spaceDist/2;

		String returnVal = "<table border=\"0\" cellpadding = \"0\" style=\"margin:auto; height:";
		returnVal += tableheight + "px; width:" + pw;
		returnVal += "; border-spacing:" + spaceDist + "px " + halfspaceDist + "px\">";
		returnVal += "\n     <tr>\n";
		//for loop for all the image to print
		for(int i = 0; i<picture.length; i++)
		{
			//Print <td>
			returnVal += "    <td>\n               ";
			//Print link a href
			if (linksOn) {
				returnVal += "<a href=\"_assets" + picture[i].getName() + "\" target=\"_blank\">";
				returnVal += "\n               ";
			}
			//Print image src
			returnVal += "<img src=\"_assets" + picture[i].getName() + "\" ";
			returnVal += "height=\"" + desiredheight + "\">\n               ";
			if (linksOn) {
				returnVal += "</a>\n          </td>";
			}
			else {
				returnVal += "\n          </td>";
			}

		}
		//Close tr and table tags
		returnVal += "     </tr>\n";
		returnVal += "</table>";
		return returnVal;
	}
}
