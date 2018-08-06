package Sandbox;

import Engine.Camera;
import Engine.Engine;
import Engine.Input;
import Engine.MenuItems.GameButton;
import Sandbox.Maps.MainMenu;
import Sandbox.Units.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class Main extends Engine{
	public static Player player;
	public static Main main;

	//variables to load from settings.txt
	public static String playerName;

	public static void main(String[] args){
		new Main().run();
	}

	public Main(){
		variables.add("playerName");
		if(playerName==null)playerName="Nameless";
		main=this;
		new Input(){
			@Override
			public void function(){
				frame.setFrame(800,600);
			}
		};
		reset();
		map.tick(0);

		new GameButton("Set name:"){
			@Override
			public void buttonAction(){
				String s=JOptionPane.showInputDialog("name?",playerName);
				if(s!=null&&s.length()>0){
					player.name=s;
					playerName=s;
				}
			}
		};
	}

	@Override
	protected void gameCodes(double delta, Set<Integer> inputs){
		map.tick(delta,inputs);
	}

	@Override
	protected void reset(){
		loadMap(new MainMenu());
	}

	@Override
	protected void initialize(){

	}

	@Override
	protected void draw(Graphics g, Camera camera){
		map.render(g,camera);
	}
}
