package it.lorenzogandino.jguiyoutubedl;

import java.io.InputStream;
import java.util.Scanner;

public class DownloadManager extends Thread implements DownloadController{
	protected StatusManager sm;
	protected final Process pr;
	protected final InputStream inStatus;
	protected final DownloadsList listD;
	protected boolean stop = false;
	
	public DownloadManager(DownloadsList downloadList, StatusManager sm, Process pr) {
		this.pr = pr;
		this.sm = sm;
		this.inStatus = pr.getInputStream();
		this.listD = downloadList;
		this.setDaemon(true);
	}
	
	public DownloadManager(StatusManager sm, Process pr) {
		this(null, sm, pr);
	}
	
	@Override
	public void run() {
		sm.setStatus(new DownloadInfo("Download avviato"));
		Scanner sc = new Scanner(inStatus);
		String s = "";
		while(sc.hasNextLine()) {
			s = sc.nextLine();
			sm.setStatus(new DownloadInfo(s));
			if(stop) {
				this.pr.destroyForcibly();
				break;
			}
		}
		sm.setStatus(new DownloadInfo(s, true));
		sc.close();
		removeByDownloadList();
	}
	
	protected void removeByDownloadList() {
		if(listD != null)
			listD.removeDownloadManager(this);
	}
	
	protected void setStatusManager(StatusManager sm) {
		this.sm = sm;
	}
	
	public void stopDownload() {
		this.stop = true;
	}
}
