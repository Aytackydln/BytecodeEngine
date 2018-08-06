package Engine;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class DebugFrame extends JFrame{
	final JFXPanel jfxPanel=new JFXPanel();
	private Parent parent;
	DebugFrame(){
		super("Debugger");
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent windowEvent){
				setVisible(false);
			}
		});
		setLocation(0,0);
		setSize(400,300);
		setPreferredSize(new Dimension(600,450));
		Platform.runLater(()->{
			try{
				parent=FXMLLoader.load(getClass().getResource("/Debug.fxml"));
				Scene scene=new Scene(parent);
				System.out.println("Setting scene...");
				jfxPanel.setScene(scene);
				System.out.println("Scene set!!!");
			}catch(IOException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		});
		add(jfxPanel);
	}
	void launch(){
		System.out.println("launching debugger");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		System.out.println("launched debugger");
	}
}
