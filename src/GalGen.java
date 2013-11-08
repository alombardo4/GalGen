import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import sun.misc.Queue;


public class GalGen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GalGenFrame frame = new GalGenFrame();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Welcome to GalGen. Please enter the file path" +
				"for your images.");
		String folderPath = keyboard.nextLine();
		try {
			File folder = new File(folderPath);
			File[] files = folder.listFiles();
			ArrayList<PictureDetail> picList = new ArrayList<PictureDetail>();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile() && !files[i].isHidden()) {
					PictureDetail temp = PictureReader.readPicture(files[i].getPath());
					temp.setName(temp.getName().replaceAll(folderPath, ""));
					picList.add(temp);
				}
			}
			System.out.println("How wide do you want your table?");
			int pageWidth = keyboard.nextInt();
			keyboard.nextLine();
			System.out.println("What margins do you want between pictures?");
			int margins = keyboard.nextInt();
			keyboard.nextLine();
			Random rand = new Random(System.currentTimeMillis());
			String page = "";
			while(!picList.isEmpty()) {
				int num = 0;
				do {
					num = rand.nextInt(6);
				}while(num < 3);
				System.out.println("Num: " + num + " Size: " + picList.size());
				PictureDetail[] pics;
				if (num < picList.size()) {
					pics = new PictureDetail[num];
					for (int i = 0; i < pics.length; i++) {
						if (!picList.isEmpty()) {
							pics[i] = (PictureDetail) picList.remove(0);
						}
					}
				}
				else {
					pics = new PictureDetail[picList.size()];
					for (int i = 0; i < pics.length; i++) {
						if (!picList.isEmpty()) {
							pics[i] = (PictureDetail) picList.remove(0);
						}
					}
				}

				
				page += PictureProcessor.getTable(pageWidth, margins, pics, false);
			}
			
			System.out.println("Where do you want to save the text?");
			String savePath = keyboard.nextLine();
			
			File output = new File(savePath + "/table.html");
			if (!output.exists()) {
				output.createNewFile();
			}
			FileWriter fw = new FileWriter(output.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(page);
			bw.close();
			System.out.println("Your table has been saved");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}

}
