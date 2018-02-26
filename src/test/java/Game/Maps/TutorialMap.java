package Game.Maps;

import Engine.Map;
import Game.Main;
import Game.Units.Box;
import Game.Units.Monsters.Skeleton;
import Game.Units.Player;

public class TutorialMap extends Map{
	public TutorialMap(){
		super(500,500);
		Main.engine.camera.viewHeight=500;
		Main.engine.camera.viewWidth=500;
		Main.engine.camera.updateScales();
		name="Tutorial Map";
		new Skeleton(this, 40,60);
		new Player(this,25,25);
		new Box(this,-50,-200,75,30);
	}
}
