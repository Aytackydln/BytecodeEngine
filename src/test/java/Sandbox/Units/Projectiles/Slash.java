package Sandbox.Units.Projectiles;

import Engine.Unit;
import Sandbox.Units.Player;
import Sandbox.Units.Projectile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Slash extends Projectile{
	public Slash(Player owner, double time, double rotation){
		super(owner.map,owner,owner.getXPos()+Math.cos(rotation)*owner.getSize(),owner.getYPos()+Math.sin(rotation)*owner.getSize());
		try{
			image=ImageIO.read(new File("slash.png"));
		}catch(IOException e){
			e.printStackTrace();
		}

		setRadius(image.getHeight()+image.getWidth()/2);;
		life=time;
		setRotation(((float) rotation));
		this.owner=owner;
	}

	@Override
	public void collide(Unit u){
		super.collide(u);
	}
}
