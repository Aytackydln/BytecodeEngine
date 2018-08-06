package Sandbox.Maps;

import Engine.Input;
import Engine.Map;
import Engine.ShapedUnits.Button;

public class MainMenu extends Map{
	public MainMenu(){
		super(300, 400);
		camera.setView(400,300);
		name="Main menu";
		new Button(this,-100,-140,200,120,"Load Tutorial Map"){
			@Override
			public boolean onClick(){
				new Input(){
					@Override
					public void function(){
						engine.loadMap(new TutorialMap());
					}
				};
				return false;
			}
		};
		new Button(this,-100,-10,200,120,"print NOTHING"){
			@Override
			public boolean onClick(){
				System.out.println("NOTHING");
				return false;
			}
		};

		camera.changePos(0,0);
	}
}
