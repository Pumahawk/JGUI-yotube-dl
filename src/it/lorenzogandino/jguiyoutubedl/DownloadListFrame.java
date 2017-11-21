package it.lorenzogandino.jguiyoutubedl;

import java.awt.Component;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DownloadListFrame extends JFrame implements Observer{
	protected final DownloadListController controller;
	protected final JPanel content;
	
	protected final Map<DownloadManager, DownloadViewPanel> listViewPanel;
	
	public DownloadListFrame(DownloadListController controller) {
		this.controller = controller;
		this.listViewPanel = Collections.synchronizedMap(new HashMap<>());
		this.setSize(600, 80);
		
		this.content = new JPanel();
		this.content.setLayout(new BoxLayout(this.content, BoxLayout.Y_AXIS));
		
		JScrollPane sp = new JScrollPane(content);
		
		add(sp);
		
	}
	
	public void updateDownloads(DownloadManager[] dms) {
		List<DownloadManager> eliminati = new LinkedList<>();
		synchronized(this.listViewPanel) {
			for(DownloadManager i : this.listViewPanel.keySet()) {
				boolean trovato = false;
				for(DownloadManager j : dms) {
					if(i == j) {
						trovato = true;
						break;
					}
				}
				if(!trovato) {
					eliminati.add(i);
				}
			}
			for(DownloadManager i : eliminati) {
				content.remove(this.listViewPanel.get(i));
				this.listViewPanel.remove(i);
			}
			List<DownloadManager> mancanti = new LinkedList<>();
			for(DownloadManager i : dms) {
				boolean uguali = false;
				for(DownloadManager j : this.listViewPanel.keySet()) {
					if(i == j) {
						uguali = true;
						break;
					}
				}
				if(!uguali)
					mancanti.add(i);
			}
			for(DownloadManager i : mancanti) {
				DownloadViewPanel p = new DownloadViewPanel();
				i.setStatusManager(p);
				this.content.add(p);
				this.listViewPanel.put(i, p);
			}
		}
		validate();
		if(this.listViewPanel.size() == 0)
			setVisible(false);
	}
	@Override
	public void update(Observable o, Object arg) {
		DownloadsList dl;
		if(o instanceof DownloadsList) {
			dl = (DownloadsList) o;
			updateDownloads(dl.getAllDonwloadManager());
		}
	}
}
