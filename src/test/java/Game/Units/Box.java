package Game.Units;

import Engine.Collisions.RectangleCollision;
import Engine.Map;
import Engine.Unit;
import Game.Main;

import java.awt.*;

public class Box extends Unit{
	int width, height;
	public Box(Map map, double xPos, double yPos, int width, int height){
		super(map,xPos,yPos);
		this.width=width;
		this.height=height;
		collision=new RectangleCollision(this,width,height);
	}

	@Override
	public void render(Graphics g){
		g.fillRect(getScreenXPos()-Main.engine.camera.screenXSize(width/2),
				getScreenYPos()-Main.engine.camera.screenYSize(height/2),
				Main.engine.camera.screenXSize(width),
				Main.engine.camera.screenXSize(height));
	}
}
