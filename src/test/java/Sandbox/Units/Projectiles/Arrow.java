package Sandbox.Units.Projectiles;

import Engine.Map;
import Engine.Unit;
import Sandbox.Units.Projectile;
import org.jbox2d.common.Vec2;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Arrow extends Projectile{
	public Arrow(Map map, Unit owner){
		super(map,owner,owner.getXPos(),owner.getYPos());

		try{
			image=ImageIO.read(new File("arrow.png"));
		}catch(IOException e){
			e.printStackTrace();
		}

		double rotation=owner.getRotation();
		setRadius(image.getHeight()+image.getWidth()/2);
		life=3;
		setRotation((float) rotation);
		speed=300;
		double xSpeed=Math.cos(rotation)*speed;
		double ySpeed=Math.sin(rotation)*speed;
		bd.setLinearVelocity(new Vec2(((float) xSpeed), ((float) ySpeed)));
	}

	@Override
	public void collide(Unit u){
		if(u!=owner&&!hits.contains(u)&&!(u instanceof Arrow)){
			speed=0;
			life=0.9;
			hits.add(u);
		}
	}
}
