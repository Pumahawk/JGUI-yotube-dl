package it.lorenzogandino.jguiyoutubedl;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.swing.JOptionPane;

import it.lorenzogandino.jguiyoutubedl.YoutubeDL.Options;

public class DefaultAppController implements AppController, DownloadListController {

	final DownloadFrame downloadFrame;
	final DownloadsList downloadList;
	final DownloadListFrame downloadListFrame;
	
	public DefaultAppController() {
		downloadFrame = new DownloadFrame(this);
		downloadList = new DownloadsList();
		downloadListFrame = new DownloadListFrame(this);
		
		downloadList.addObserver(downloadListFrame);
	}
	
	/* (non-Javadoc)
	 * @see it.lorenzogandino.jguiyoutubedl.AppController#openDownloadFrame()
	 */
	@Override
	public void openDownloadFrame() {
		this.downloadFrame.setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see it.lorenzogandino.jguiyoutubedl.AppController#downloadAction()
	 */
	@Override
	public void downloadAction() {
		String url = this.downloadFrame.getUrl();
		File dest = this.downloadFrame.getDestinationFolder();
		if(dest != null) {
			Options opt = new Options();
			opt.setPlaylist(downloadFrame.isPlaylist());
			opt.setVideo(!downloadFrame.isMusic());
			opt.setOutFormat(dest.getAbsolutePath() + "/%(title)s-%(id)s.%(ext)s");
			YoutubeDL youtubedl = new YoutubeDL();
			youtubedl.redirectInput(Redirect.INHERIT);
			youtubedl.redirectError(Redirect.INHERIT);
			try {
				Process yp = youtubedl.startYDL(opt, url);
				DownloadManager dm = new DownloadManager(this.downloadList, this.downloadFrame, yp);
				this.downloadList.addDownloadManager(dm, opt);
				dm.start();
				this.downloadListFrame.setVisible(true);
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
		AppController app = new DefaultAppController();
		app.openDownloadFrame();
	}

	@Override
	public void stopDownload(DownloadManager dm) {
		// TODO Auto-generated method stub
		
	}

}
