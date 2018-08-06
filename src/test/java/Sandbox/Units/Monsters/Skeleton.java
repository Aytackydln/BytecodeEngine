package Sandbox.Units.Monsters;

import Engine.Map;
import Sandbox.Main;
import Sandbox.Units.MonsterBase;
import org.jbox2d.dynamics.BodyType;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Skeleton extends MonsterBase{
	public Skeleton(Map map,double xPos, double yPos){
		super(map,xPos,yPos);
		try{
			image=ImageIO.read(new File("skull.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		setRadius(image.getHeight()/2+image.getWidth()/2);
		setBodyType(BodyType.DYNAMIC);
	}

	@Override
	public void tick(double delta, Set<Integer> inputs){
		super.tick(delta,inputs);
		if(Main.player!=null)setRotation(((float) Math.atan2(Main.player.getYPos()-getYPos(), Main.player.getXPos()-getXPos())));
	}
}
