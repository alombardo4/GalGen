import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class GalGenFrame extends JFrame {
	private JTextField inputFolderBox, outputFolderBox;
	private JButton inputButton, outputButton, process;
	private File openFolder;
	private File saveFolder;
	public GalGenFrame() {
		super("Gallery Generator");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3,1));
        setPreferredSize(new Dimension(400, 400));
        inputFolderBox = new JTextField();
        outputFolderBox = new JTextField();
        inputButton = new JButton("Input Folder");
        inputButton.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
				    JFileChooser openChooser = new JFileChooser();
	                try {
	                    openChooser.showOpenDialog(this);
	                    openFolder = openChooser.getSelectedFile();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
			}       	
        });
        outputButton = new JButton("Output Folder");
        process = new JButton("Process!");
        add(inputButton);
        add(outputButton);
        add(process);
        pack();
        setVisible(true);
	}
}


        // saveas.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         JFileChooser saveChooser = new JFileChooser();
        //         try {
        //             saveChooser.showSaveDialog(gui);
        //             File saveFile = saveChooser.getSelectedFile();
        //             ImageIO.write(currentPic.getImage(), "png", saveFile);
        //         } catch (Exception ex) {
        //             ex.printStackTrace();
        //         }
        //     }
        // });