package Engine.Collisions;

import Engine.Collision;
import Engine.Unit;

public class SphereCollision extends Collision{
	public SphereCollision(Unit unit, int size){
		super(unit);
		x=size/2;
		y=size/2;
	}

	@Override
	public boolean collides(Unit unit){
		Collision otherCollision=unit.collision;
		if(otherCollision instanceof SphereCollision){
			double distanceSq=Math.pow(unit.xPos-(owner.xPos+owner.xSpeed), 2)+Math.pow(unit.yPos-(owner.yPos+owner.ySpeed), 2);
			return distanceSq<Math.pow(otherCollision.x+x, 2);
		}else if(unit.collision instanceof RectangleCollision){
			return (owner.xPos+owner.xSpeed<unit.xPos+x+otherCollision.x)&&(owner.xPos+owner.xSpeed>unit.xPos-x-otherCollision.x)
					&&(owner.yPos+owner.ySpeed<unit.yPos+y+otherCollision.y)&&(owner.yPos+owner.ySpeed>unit.yPos-y-otherCollision.y);
		}else throwException(unit.collision);
		return false;
	}

	@Override
	public boolean intersects(double x, double y){
		return Math.pow(owner.xPos-x,2)+Math.pow(owner.yPos-y,2)<=Math.pow(x,2);
	}

	@Override
	public double xKnockback(Unit unit, double distanceSq, double angle){
		if(unit.collision instanceof SphereCollision){
			double distancetoMove=Math.sqrt(distanceSq)-(unit.size+owner.size)/2;
			return Math.cos(angle)*distancetoMove;
		}else if(unit.collision instanceof RectangleCollision){
			return (Math.sqrt(distanceSq)-x-unit.collision.x)*Math.cos(angle);
		}else return 0;
	}

	@Override
	public double yKnockback(Unit unit, double distanceSq, double angle){
		if(unit.collision instanceof SphereCollision){
			double distancetoMove=Math.sqrt(distanceSq)-(unit.size+owner.size)/2;
			return Math.sin(angle)*distancetoMove;
		}else if(unit.collision instanceof RectangleCollision){
			return -(Math.sqrt(distanceSq)-y-unit.collision.y)*Math.sin(angle);
		}else return 0;
	}
}
