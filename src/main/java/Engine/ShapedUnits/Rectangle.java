package Engine.ShapedUnits;

import Engine.Camera;
import Engine.Map;
import Engine.Unit;

import java.awt.*;

public class Rectangle extends Unit{
	public int lengthX,lengthY;

	public Rectangle(Map map, int startX, int startY, int lengthX, int lengthY){
		super(map,startX,startY);
		this.lengthX=lengthX;
		this.lengthY=lengthY;
	}

	@Override
	public void render(Graphics g, Camera camera){
		final int x2=camera.screenXSize(lengthX);
		final int y2=camera.screenYSize(lengthY);
		g.drawRect(getScreenXPos(camera),getScreenYPos(camera)-y2,x2,y2);
	}

	@Override
	public boolean clickHit(double worldX, double worldY){
		if(worldX>=getXPos()&&worldX<=getXPos()+lengthX
				&&worldY>=getYPos()&&(worldY<=getYPos()+lengthY))return true;
		else return false;
	}
}
