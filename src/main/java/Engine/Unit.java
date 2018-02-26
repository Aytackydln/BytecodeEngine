package Engine;

import Engine.Collisions.SphereCollision;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Unit{
	public Map map;
	public double xPos,yPos;
	public long speed=0;
	public double xSpeed,ySpeed;
	public double rotation;
	protected BufferedImage image;
	public AffineTransform transform=new AffineTransform();
	public Collision collision;
	public boolean collides=true;
	public boolean pierces=false;
	public int size;

	public void tick(double delta){
		xSpeed=Math.cos(rotation)*speed*delta;
		ySpeed=Math.sin(rotation)*speed*delta;
		map.moveUnit(this);
	}

	public void render(Graphics g){


		transform.setToScale(1,1);
		transform.setToRotation(rotation, Camera.cam.screenXPos(xPos),Camera.cam.screenYPos(yPos));
		transform.translate(Camera.cam.screenXPos(xPos-image.getWidth()/2),Camera.cam.screenYPos(yPos-image.getHeight()/2));
		transform.scale(Camera.cam.screenXScale(),Camera.cam.screenYScale());
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image,transform,null);
	}
	public Unit(Map map, double xPos, double yPos){
		this.map=map;
		map.unitsToAdd.add(this);
		this.xPos=xPos;
		this.yPos=yPos;
		collision=new SphereCollision(this,size);
	}

	public void die(){
		map.unitsToRemove.add(this);
	}

	public void collide(Unit u){}

	public int getScreenXPos(){
		return Camera.cam.screenXPos(xPos);
	}
	public int getScreenYPos(){
		return Camera.cam.screenYPos(yPos);
	}
	public void onClick(){
		System.out.println("Clicked on "+getClass());
	}
	public boolean clickHit(){
		return collision.intersects(Engine.engine.getMouseWorldX(),Engine.engine.getMouseWorldY());
	}
}
