package it.lorenzogandino.jguiyoutubedl;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.LinkedList;
import java.util.List;

public class YoutubeDL {
	public static class Options{
		protected boolean video = true;
		protected boolean playlist = true;
		protected String outFormat = null;
		protected boolean ignore = true;
		
		public boolean isIgnore() {
			return ignore;
		}
		public void setIgnore(boolean ignore) {
			this.ignore = ignore;
		}
		public void setVideo(boolean video) {
			this.video = video;
		}
		public boolean isVideo() {
			return this.video;
		}
		public boolean isPlaylist() {
			return playlist;
		}
		public void setPlaylist(boolean playlist) {
			this.playlist = playlist;
		}
		public String getOutFormat() {
			return outFormat;
		}
		public void setOutFormat(String outFormat) {
			this.outFormat = outFormat;
		}
		
		public List<String> toList() {
			LinkedList<String> cm = new LinkedList<>();
			if(!video)
				cm.add("-x");
			if(ignore)
				cm.add("-i");
			if(!playlist)
				cm.add("--no-playlist");
			if(outFormat != null) {
				cm.add("-o");
				cm.add(outFormat);
			}
			return cm;
		}
	}

	protected final ProcessBuilder processBuilder;
	
	public YoutubeDL() {
		this.processBuilder = new ProcessBuilder();
	}

	public void redirectInput(Redirect r) {
		this.processBuilder.redirectInput(r);
	}
	public void redirectOutput(Redirect r) {
		this.processBuilder.redirectOutput(r);
	}
	public void redirectError(Redirect r) {
		this.processBuilder.redirectError(r);
	}
	
	protected void prepare(Options opt) {
		prepare(opt, null);
	}
	protected void prepare(Options opt, String urlVideo) {
		List<String> cm = new LinkedList<>();
		cm.add("youtube-dl");
		cm.addAll(opt.toList());
		if(urlVideo != null)
			cm.add(urlVideo);
		this.processBuilder.command(cm);
			
	}
	
	public Process startYDL(Options opt, String urlVideo) throws IOException {
		prepare(opt, urlVideo);
		return this.processBuilder.start();
	}
}
