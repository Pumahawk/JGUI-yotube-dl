package it.lorenzogandino.jguiyoutubedl;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class DownloadListFrame extends JFrame implements Observer{
	protected final DownloadListController controller;
	public DownloadListFrame(DownloadListController controller) {
		this.controller = controller;
	}
	
	public void addDownloads(DownloadManager[] dms) {
		System.out.println(dms.length);
	}
	@Override
	public void update(Observable o, Object arg) {
		DownloadsList dl;
		if(o instanceof DownloadsList) {
			dl = (DownloadsList) o;
			addDownloads(dl.getAllDonwloadManager());
		}
	}
}
