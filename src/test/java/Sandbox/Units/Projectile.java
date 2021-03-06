package Sandbox.Units;

import Engine.Map;
import Engine.Unit;
import org.jbox2d.dynamics.BodyType;

import java.util.ArrayList;
import java.util.Set;

public class Projectile extends Unit{
	public Unit owner;
	public double life;
	public ArrayList<Unit> hits=new ArrayList<>();

	public Projectile(Map map,Unit owner, double xPos, double yPos){
		super(map,xPos,yPos);
		this.owner=owner;
		pierces=true;
		setBodyType(BodyType.DYNAMIC);
	}

	@Override
	public void tick(double delta, Set<Integer> inputs){
		life-=delta;
		if(life<0)die();
	}
	public void collide(Unit u){
		if(!hits.contains(u)){
			hits.add(u);{
				System.out.println("Projectile hit "+u.getClass());
			}
		}else return;
	}
}
