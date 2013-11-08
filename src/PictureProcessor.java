
public class PictureProcessor {
	public static String getTable(int pw, int spaceDist,
			PictureDetail[] picture, boolean linksOn) {
		int imagesdimension = pw - spaceDist*(picture.length+1);
		double ratiosum = 0;

		for(int i = 0; i<picture.length; i++)
		{
			int width = picture[i].getWidth();
			int height = picture[i].getHeight();
			ratiosum += (double)width/height;
		}
		int desiredheight = imagesdimension/(int)ratiosum;

		// Print <table border="0" cellpadding = "0" style="margin:auto; height:100px; border-spacing:10px 5px">
		int tableheight = desiredheight+spaceDist;
		int halfspaceDist = spaceDist/2;

		String returnVal = "<table border=\"0\" cellpadding = \"0\" style=\"margin:auto; height:";
		returnVal += tableheight + "px; width:" + pw;
		returnVal += "; border-spacing:" + spaceDist + "px; " + halfspaceDist + "px\">";
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
