package Engine.Collisions;

import Engine.Collision;
import Engine.Unit;

public class RectangleCollision extends Collision{
	public RectangleCollision(Unit unit,int width,int height){
		super(unit);
		x=width/2;
		y=height/2;
	}

	@Override
	public boolean collides(Unit unit){
		Collision otherCollision=unit.collision;
		if(otherCollision instanceof RectangleCollision){
			return (owner.xPos+owner.xSpeed<unit.xPos+x+otherCollision.x)&&(owner.xPos+owner.xSpeed>unit.xPos-x-otherCollision.x)
					&&(owner.yPos+owner.ySpeed<unit.yPos+y+otherCollision.y)&&(owner.yPos+owner.ySpeed>unit.yPos-y-otherCollision.y);
		}else if(unit.collision instanceof SphereCollision){
			return (owner.xPos<unit.xPos+x+otherCollision.x)&&(owner.xPos>unit.xPos-x-otherCollision.x)
					&&(owner.yPos<unit.yPos+y+otherCollision.y)&&(owner.yPos>unit.yPos-y-otherCollision.y);
		}else throwException(unit.collision);
		return false;
	}

	@Override
	public boolean intersects(double x, double y){
		return (x<owner.xPos+this.x)&&(x>owner.xPos-this.x)
				&&(y<owner.yPos+this.y)&&(y>owner.yPos-this.y);
	}

	@Override
	public double xKnockback(Unit unit, double distanceSq, double angle){
		return 0;
	}

	@Override
	public double yKnockback(Unit unit, double distanceSq, double angle){
		return 0;
	}
}
