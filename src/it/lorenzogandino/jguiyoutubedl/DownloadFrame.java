package it.lorenzogandino.jguiyoutubedl;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DownloadFrame extends JFrame implements StatusManager {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final AppController controller;
	protected final JTextField urlText;
	protected final JLabel statusDownload;
	protected final JButton download;
	protected final JButton selectFolder;
	protected final JFileChooser folderDestination;
	protected final JCheckBox musicOpt;
	
	public DownloadFrame(AppController controller) {
		this.controller = controller;
		this.folderDestination = new JFileChooser();
		this.musicOpt = new JCheckBox("Audio");
		this.folderDestination.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		this.setTitle("JGUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JLabel urlLabel = new JLabel("URL Video:");
		this.urlText = new JTextField(20);
		
		download = new JButton("Download");
		download.addActionListener(e -> controller.downloadVideoAction());
		
		selectFolder = new JButton("Destinazione");
		selectFolder.addActionListener(e -> folderDestination.showOpenDialog(this));
		
		
		

		this.statusDownload = new JLabel("Stato download:");
		
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		JPanel url = new JPanel();
		JPanel button = new JPanel();
		
		url.add(urlLabel);
		url.add(urlText);
		url.add(musicOpt);
		button.add(download);
		button.add(selectFolder);
		
		center.add(url);
		center.add(button);
		
		this.setLayout(new BorderLayout());

		add(center, BorderLayout.CENTER);
		add(this.statusDownload, BorderLayout.SOUTH);
		
		this.setResizable(false);
		this.pack();
		
	}
	
	public String getUrl() {
		return this.urlText.getText();
	}
	
	public File getDestinationFolder() {
		return this.folderDestination.getSelectedFile();
	}
	
	public void enableData(boolean status) {
		this.urlText.setEditable(status);
		this.download.setEnabled(status);
		this.selectFolder.setEnabled(status);
		this.musicOpt.setEnabled(status);
	}
	
	public boolean isMusic() {
		return this.musicOpt.isSelected();
	}

	@Override
	public void setStatus(StatusInfo info) {
		this.statusDownload.setText(info.getMessage());
		this.enableData(info.complete());
		this.validate();
	}
}
