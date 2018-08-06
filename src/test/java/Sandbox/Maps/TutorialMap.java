package Sandbox.Maps;

import Engine.Map;
import Sandbox.Units.Box;
import Sandbox.Units.Monsters.Skeleton;
import Sandbox.Units.Player;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public class TutorialMap extends Map{
	Body m_body;
	Body m_bullet;
	float m_x;
	public TutorialMap(){
		super(500,500);
		camera.setView(500,500);
		name="Tutorial Map";
		new Skeleton(this, 40,60);
		new Player(this,25,25);
		new Box(this,-50,-200,75,30);
		setGravity(new Vec2(0f,-10f));
	}
}
