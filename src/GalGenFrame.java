/**
* Generates and runs the GUI for GalGen
* @author Alec Lombardo
* @version 1.0
*/
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.IOException;

public class GalGenFrame extends JFrame {
	private JTextField width, padding, ppr;
    private JLabel inputFolderBox, outputFolderBox;
	private JButton inputButton, outputButton, process;
	private File openFolder;
	private File saveFolder;
    private JLabel tableWidth, pixelPadding, picsPerRow, status;

    /**
    * Constructor for GalGenFrame. Sets up the GUI with
    * Grid layout and 6,2 size. Contains text boxes,
    * labels, and buttons as well as button listeners.
    */
	public GalGenFrame() {
		super("Gallery Generator");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLayout(new GridLayout(6,2));
                setPreferredSize(new Dimension(400, 400));
                inputButton = new JButton("Input Folder");
                inputFolderBox = new JLabel();
                inputButton.addActionListener(new ActionListener() {
        	 		public void actionPerformed(ActionEvent e) {
        				    JFileChooser openChooser = new JFileChooser();
        	                try {
                                    openChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	                    openChooser.showOpenDialog(null);
        	                    openFolder = openChooser.getSelectedFile();
                                    inputFolderBox.setText(openFolder.getAbsolutePath());
        	                } catch (Exception ex) {
        	                    ex.printStackTrace();
        	                }
        			}       	
                });
                outputButton = new JButton("Output Folder");
                outputFolderBox = new JLabel();
                outputButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                JFileChooser saveChooser = new JFileChooser();
                                try {
                                        saveChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                        saveChooser.showSaveDialog(null);
                                        saveFolder = saveChooser.getSelectedFile();
                                        outputFolderBox.setText(saveFolder.getAbsolutePath());
                                } catch (Exception ex) {
                                        ex.printStackTrace();
                                }
                        }
                });
                process = new JButton("Process!");
                process.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                status.setText("Processing...");
                                try {
                                        checkInputs();
                                        Runnable ptask = new ProcessorRunnable();
                                        Thread worker = new Thread(ptask);
                                        worker.start();
                                } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(null, "Bad input!");
                                        status.setText("Error!");
                                }
                        }
                });
                tableWidth = new JLabel("Table Width");
                pixelPadding = new JLabel("Pixel Padding");
                picsPerRow = new JLabel("<html>Pictures Per Row<br>(0 is random)</html>");
                status = new JLabel("Fill out the form and click Process");
                width = new JTextField("980");
                padding = new JTextField("5");
                ppr = new JTextField("0");
                add(inputButton);
                add(inputFolderBox);
                add(outputButton);
                add(outputFolderBox);
                add(tableWidth);
                add(width);
                add(pixelPadding);
                add(padding);
                add(picsPerRow);
                add(ppr);
                add(process);
                add(status);
                pack();
                setVisible(true);
	}
    
    /**
    * Runs the input checker to ensure that no exceptions will occur
    * during processing.
    * @throws NullPointerException When files may not exist.
    * @throws NumberFormatException When input is not numeric
    * @throws IOException When filesystem cannot be accessed
    *
    */
    private void checkInputs() throws NullPointerException, NumberFormatException, IOException {
            File[] files = openFolder.listFiles();
            int pageWidth = Integer.parseInt(width.getText());
            int margins = Integer.parseInt(padding.getText());
            int perRow = Integer.parseInt(ppr.getText());
            File output = new File(saveFolder.getAbsolutePath() + "/table.html");
            if (!output.exists()) {
                    output.createNewFile();
            }
            FileWriter fw = new FileWriter(output.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
    }

    /**
    * This class is for threading the processing so as not to lock up GUI
    * @author Alec Lombardo and Alec Li
    * @version 1.0
    */
    private class ProcessorRunnable implements Runnable {
        /**
        * Runs the thread. Opens pictures, reads them, sends them for processing.
        * When done, it writes the output file.
        */
        public void run() {
            try {
                File[] files = openFolder.listFiles();
                ArrayList<PictureDetail> picList = new ArrayList<PictureDetail>();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile() && !files[i].isHidden()) {
                        PictureDetail temp = PictureReader.readPicture(files[i].getPath());
                        String oldpath = openFolder.getAbsolutePath();
                        String setpath = temp.getName();
                        setpath = setpath.replace(oldpath, "");
                        temp.setName(setpath);
                        picList.add(temp);
                    }
                    System.gc();
                }
                int pageWidth = Integer.parseInt(width.getText());
                int margins = Integer.parseInt(padding.getText());
                int perRow = Integer.parseInt(ppr.getText());
                
                Random rand = new Random(System.currentTimeMillis());
                
                String page = "";
                while(!picList.isEmpty()) {
                    System.gc();
                    int num = 0;
                    if (perRow == 0)
                    {
	                    do {
	                        num = rand.nextInt(8);
	                    }while(num < 3);
                    }
                    else
                    {
                    	num = perRow;
                    }
                    
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
                File output = new File(saveFolder.getAbsolutePath() + "/table.html");
                if (!output.exists()) {
                    output.createNewFile();
                }
                FileWriter fw = new FileWriter(output.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(page);
                bw.close();
                status.setText("Done!");                                
            } catch (Exception e) {}

        } 
    }
}
