package it.lorenzogandino.jguiyoutubedl;

public class DownloadInfo implements StatusInfo {
	protected final String message;
	protected final boolean status;
	
	public DownloadInfo(String message, boolean status) {
		this.message = message;
		this.status = status;
	}
	
	public DownloadInfo(String message) {
		this(message, false);
	}
	
	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public boolean complete() {
		return this.status;
	}

}
