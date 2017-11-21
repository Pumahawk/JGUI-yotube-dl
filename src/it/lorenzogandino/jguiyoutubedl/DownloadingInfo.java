package it.lorenzogandino.jguiyoutubedl;

import it.lorenzogandino.jguiyoutubedl.YoutubeDL.Options;

class DownloadingInfo{
	DownloadManager dm;
	Options opt;
	public DownloadingInfo(DownloadManager dm, Options opt) {
		super();
		this.dm = dm;
		this.opt = opt;
	}
}