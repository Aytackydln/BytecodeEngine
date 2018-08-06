package Sandbox.MenuButtons;

import Engine.Input;
import Engine.Map;
import Engine.ShapedUnits.Rectangle;
import Sandbox.Main;
import Sandbox.Maps.TutorialMap;

public class Tutorial extends Rectangle{
	public Tutorial(Map map, int startX, int startY, int lengthX, int lengthY){
		super(map, startX, startY, lengthX, lengthY);
	}

	@Override
	public boolean onClick(){
		new Input(){
			@Override
			public void function(){
				Main.main.loadMap(new TutorialMap());
			}
		};
		return true;
	}
}
