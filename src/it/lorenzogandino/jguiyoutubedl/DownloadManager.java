package it.lorenzogandino.jguiyoutubedl;

import java.io.InputStream;
import java.util.Scanner;

public class DownloadManager extends Thread {
	final StatusManager sm;
	final InputStream inStatus;
	public DownloadManager(StatusManager sm, InputStream inStatus) {
		this.sm = sm;
		this.inStatus = inStatus;
		this.setDaemon(true);
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
	}
}
