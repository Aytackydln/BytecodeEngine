package Engine.MenuItems;

import Engine.BFrame;

import javax.swing.*;
import java.awt.event.ActionListener;


public abstract class GameButton extends JMenuItem{
	public static JMenu menu;
	public static BFrame frame;
	private static ActionListener listener=e -> ((GameButton) e.getSource()).buttonAction();

	public GameButton(String text){
		setText(text);
		addActionListener(listener);
		menu.add(this);
	}
	public abstract void buttonAction();
}
