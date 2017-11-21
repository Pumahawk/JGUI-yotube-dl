package it.lorenzogandino.jguiyoutubedl;

import java.io.InputStream;
import java.util.Scanner;

public class DownloadManager extends Thread {
	protected StatusManager sm;
	protected final InputStream inStatus;
	protected final DownloadsList listD;
	public DownloadManager(DownloadsList downloadList, StatusManager sm, InputStream inStatus) {
		this.sm = sm;
		this.inStatus = inStatus;
		this.listD = downloadList;
		this.setDaemon(true);
	}
	
	public DownloadManager(StatusManager sm, InputStream inStatus) {
		this(null, sm, inStatus);
	}
	
	@Override
	public void run() {
		sm.setStatus(new DownloadInfo("Download avviato"));
		Scanner sc = new Scanner(inStatus);
		String s = "";
		while(sc.hasNextLine()) {
			s = sc.nextLine();
			sm.setStatus(new DownloadInfo(s));
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
}
