package Engine.ShapedUnits;

import Engine.Camera;
import Engine.Map;

import java.awt.*;

public class Button extends Rectangle{
	String text;
	public Button(Map map, int startX, int startY, int lengthX, int lengthY, String text){
		super(map, startX, startY, lengthX, lengthY);
		this.text=text;
	}

	@Override
	public void render(Graphics g, Camera camera){
		super.render(g,camera);
		g.drawString(text,getScreenXPos(camera)+10,getScreenYPos(camera)+30);
	}
}
