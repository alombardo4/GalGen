/**
 * Returns one-row HTML table for an input object array of picture data and the desired number of pictures in that row.
 * Inputs: 
 * pw = Page Width
 * spaceDist = desired number of pixels between each picture and on each side of the picture array
 * Object Array called picture
 * @author Alec Li, Alec Lombardo
 * @version 1.0
 */

int arraylength = picture.length;
int imagesdimension = pw - spaceDist*(arraylength+1);
double ratiosum = 0;

for(i = 0; i<arraylength; i++)
{
	ratiosum += picture.getWidth()/picture.getHeight();
}
int desiredheight = imagesdimension/ratiosum;

// Print <table border="0" cellpadding = "0" style="margin:auto; height:100px; border-spacing:10px 5px">
int tableheight = desiredheight+spaceDist;
int halfspaceDist = spaceDist/2;

String returnVal = "<table border=\"0\" cellpadding = \"0\" style=\"margin:auto; height:";
returnVal += tableheight + "px; width:" + pw;
returnVal += "; border-spacing:" + spaceDist + "px; " + halfspaceDist + "px\">";
returnVal += "\n     <tr>\n";
//for loop for all the image to print
for(i = 0; i<arraylength; i++)
{
	//Print <td>
	returnVal += "    <td>\n               ";
	//Print link a href
	returnVal += "<a href=\"_assets/" + picture[i].getName() + "\" target=\"_blank\">";
	returnVal += "\n               ";
	//Print image src
	returnVal += "<img src=\"_assets/" + picture[i].getName() + "\" ";
	returnVal += "height=\"" + desiredheight + "\">\n               ";
	returnVal += "</a>\n          </td>";

}
//Close tr and table tags
returnVal += "     </tr>\n";
returnVal += "</table>";


