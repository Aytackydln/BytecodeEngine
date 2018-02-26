package Engine;

public abstract class Collision{
	protected final Unit owner;
	public int x;
	public int y;
	protected Collision(Unit unit){
		this.owner=unit;
	}
	public abstract boolean collides(Unit unit);
	public abstract boolean intersects(double x,double y);
	public abstract double xKnockback(Unit unit, double distanceSq, double angle);
	public abstract double yKnockback(Unit unit, double distanceSq, double angle);
	protected void throwException(Collision collision){
		throw new IllegalStateException(collision.getClass()+" collision type is not implemented in "+getClass());
	}
}
