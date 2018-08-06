package Engine.MenuItems;

import Engine.Ticker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fps extends JMenuItem{

	static ActionListener listener=new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Ticker.ticker.setFps(((Fps) e.getSource()).fps);
		}
	};

	public final int fps;

	public Fps(int fps){
		this.fps=fps;
		setText(""+fps);
		addActionListener(listener);
	}
}
