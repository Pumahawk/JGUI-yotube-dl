package it.lorenzogandino.jguiyoutubedl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import it.lorenzogandino.jguiyoutubedl.YoutubeDL.Options;

public class DownloadsList extends Observable{
	
	protected List<DownloadingInfo> list;
	
	public DownloadsList() {
		list = Collections.synchronizedList(new LinkedList<>());
	}
	
	public void addDownloadManager(DownloadManager dm, Options opt) {
		list.add(new DownloadingInfo(dm, opt));
		setChanged();
		notifyObservers();
	}
	
	public DownloadManager[] getAllDonwloadManager() {
		DownloadingInfo[] dfs = this.list.toArray(new DownloadingInfo[0]);
		DownloadManager[] dms = new DownloadManager[dfs.length];
		int i = 0;
		for(DownloadingInfo df : dfs) {
			dms[i++] = df.dm;
		}
		return dms;
	}
	
	public void removeDownloadManager (DownloadManager dm) {
		if(list.remove(getDownloadingInfo(dm))) {
			setChanged();
			notifyObservers();
		}
	}
	
	public DownloadingInfo getDownloadingInfo(DownloadManager dm) {
		synchronized(list) {
			for(DownloadingInfo info : list) {
				if(info.dm == dm)
					return info;
			}
		}
		return null;
	}
}
