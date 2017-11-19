package it.lorenzogandino.jguiyoutubedl;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.swing.JOptionPane;

public class AppController {

	final DownloadFrame downloadFrame;
	
	public AppController() {
		downloadFrame = new DownloadFrame(this);
	}
	
	public void openDownloadFrame() {
		this.downloadFrame.setVisible(true);
	}
	
	public void downloadVideoAction() {
		String url = this.downloadFrame.getUrl();
		File dest = this.downloadFrame.getDestinationFolder();
		if(dest != null) {
			ProcessBuilder youtubedl = new ProcessBuilder("youtube-dl", "--no-playlist", "-o", dest.getAbsolutePath() + "/%(title)s-%(id)s.%(ext)s", url);
			youtubedl.redirectInput(Redirect.INHERIT);
			youtubedl.redirectError(Redirect.INHERIT);
			try {
				Process yp = youtubedl.start();
				DownloadManager dm = new DownloadManager(this.downloadFrame, yp.getInputStream());
				dm.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(this.downloadFrame, "Selezionare una cartella di destinazione.");
		}
	}
	
	public static void main(String[] args) {
		AppController app = new AppController();
		app.openDownloadFrame();
	}

}
