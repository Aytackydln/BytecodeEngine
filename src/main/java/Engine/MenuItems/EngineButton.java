package Engine.MenuItems;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class EngineButton extends JMenuItem{
	public static JMenu menu;
	private static ActionListener listener=new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			((EngineButton) e.getSource()).buttonAction();
		}
	};

	public EngineButton(String text){
		setText(text);
		addActionListener(listener);
		menu.add(this);
	}
	public abstract void buttonAction();
}
