package Sandbox.Units;

import Engine.Camera;
import Engine.Map;
import Engine.ShapedUnits.Wall;

import java.awt.*;

public class Box extends Wall{
	int width, height;
	public Box(Map map, int xPos, int yPos, int width, int height){
		super(map, xPos, yPos, width, height);
		this.width=width;
		this.height=height;
	}

	@Override
	public void render(Graphics g, Camera camera){
		g.fillRect(getScreenXPos(camera),
				getScreenYPos(camera),
				camera.screenXSize(width),
				camera.screenYSize(height)
		);
	}
}
