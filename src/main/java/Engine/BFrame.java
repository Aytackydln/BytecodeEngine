package Engine;

import Engine.MenuItems.EngineButton;
import Engine.MenuItems.GameButton;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BFrame extends JFrame{
	BPanel panel;
	JMenuBar menuBar;
	DebugFrame debugger;
	private JMenu menu1; //Game
	private JMenu menu2;    //Engine
	private JMenuItem m11; //reset
	private JMenuItem m21; //Show stats

	BFrame(String title, BPanel panel){
		super(title);
		this.panel=panel;

		setLocation(0, 0);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		add(panel);
		setSize(300, 300);
		setVisible(true);
		panel.drawSplash();

		menuBarimiz();
		setJMenuBar(menuBar);
		menuBar.setVisible(false);
		panel.drawSplash();

		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent windowEvent){
				Engine.run=false;
			}
		});
		debugger=new DebugFrame();
		debugger.launch();
	}

	private void menuBarimiz(){
		menuBar=new JMenuBar();
		menu1=new JMenu("Game");
		menu2=new JMenu("Engine");

		EngineButton.menu=menu2;
		GameButton.menu=menu1;

		m11=new GameButton("Reset"){
			@Override
			public void buttonAction(){
				new Input(){
					@Override
					public void function(){
						engine.reset();
					}
				};
			}
		};
		m21=new EngineButton("Show Stats"){
			@Override
			public void buttonAction(){
				panel.showStats=!panel.showStats;
			}
		};
		m21=new EngineButton("Show Panel"){
			@Override
			public void buttonAction(){
				if(debugger.isVisible())debugger.setVisible(false);
				else debugger.setVisible(true);
			}
		};

		menu1.add(m11);
		menu2.add(m21);
		/*
		menu2.addSeparator();
		menu2.add("Game speed:");
		menu2.add(new Ups(128));
		menu2.add(new Ups(64));
		menu2.add(new Ups(32));
		menu2.add("FPS:");
		menu2.add(new Fps(128));
		menu2.add(new Fps(64));
		menu2.add(new Fps(52));
		*/

		menuBar.add(menu1);
		menuBar.add(menu2);
	}

	public void setFrame(int x, int y){
		setSize(x, y);
	}

	BPanel getPanel(){
		return panel;
	}

	public Camera getCamera(){
		return panel.camera;
	}
}
