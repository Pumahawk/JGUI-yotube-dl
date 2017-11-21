package it.lorenzogandino.jguiyoutubedl;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DownloadViewPanel extends JPanel implements StatusManager {
	
	protected final JLabel info;
	
	public DownloadViewPanel() {
		JLabel t = new JLabel("Stato download: ");
		info = new JLabel("Stato...");
		add(t);
		add(info);
	}

	@Override
	public void setStatus(StatusInfo info) {
		this.info.setText(info.getMessage());
		validate();
	}
}
