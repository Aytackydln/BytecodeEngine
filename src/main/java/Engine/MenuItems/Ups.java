package Engine.MenuItems;

import Engine.Ticker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ups extends JMenuItem{
	static ActionListener listener=new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Ticker.ticker.setUps(((Ups) e.getSource()).ups);
		}
	};

	public final int ups;

	public Ups(int ups){
		this.ups=ups;
		setText(""+ups);
		addActionListener(listener);
	}
}
