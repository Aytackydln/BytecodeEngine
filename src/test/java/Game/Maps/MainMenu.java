package Game.Maps;

import Engine.Camera;
import Engine.Map;
import Engine.ShapedUnits.Button;
import Game.Main;

public class MainMenu extends Map{
	public MainMenu(){
		super(300, 400);
		Main.engine.camera.viewHeight=300;
		Main.engine.camera.viewWidth=400;
		Main.engine.camera.updateScales();
		name="Main menu";
		new Button(this,-100,-140,200,120,"Load Tutorial Map"){
			@Override
			public void onClick(){
				Main.main.loadMap(new TutorialMap());
			}
		};
		new Button(this,-100,-10,200,120,"print NOTHING"){
			@Override
			public void onClick(){
				System.out.println("NOTHING");
			}
		};

		Camera.cam.chanePos(0,0);
	}
}
