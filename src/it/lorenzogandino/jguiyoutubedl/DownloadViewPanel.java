package it.lorenzogandino.jguiyoutubedl;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DownloadViewPanel extends JPanel implements StatusManager , DownloadController{
	
	protected final JLabel info;
	protected final DownloadManager dm;
	
	public DownloadViewPanel(DownloadManager dm) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.dm = dm;
		
		dm.setStatusManager(this);
		
		JButton x = new JButton("X");
		x.addActionListener(e -> {
			stop();
			x.setEnabled(false);
		});
		info = new JLabel("Stato...");
		add(x);
		add(info);
	}

	@Override
	public void setStatus(StatusInfo info) {
		this.info.setText(info.getMessage());
		validate();
	}

	@Override
	public void stop() {
		dm.stopDownload();
	}
}
