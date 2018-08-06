package Engine;

import com.jogamp.opengl.GL2;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Set;

public class Unit{
	public Body bd;
	public Map map;
	public long speed=0;
	protected BufferedImage image;
	public AffineTransform transform=new AffineTransform();
	public boolean pierces=false;
	private float size;

	public Unit(Map map, double xPos, double yPos){
		BodyDef bdef=new BodyDef();
		bdef.position.set(((float) xPos), ((float) yPos));
		bd = map.createBody(bdef);


		this.map=map;
		Unit thisUnit=this;
		new Input(){
			@Override
			public void function(){
				map.units.add(thisUnit);
			}
		};
		bd.m_xf.set(new Vec2((float) xPos, ((float) yPos)), 0.0f);
		bd.setTransform(new Vec2((float)xPos, (float) yPos), (float) getRotation());

	}

	public void tick(double delta, Set<Integer> inputs){
	}

	public void render(Graphics g,Camera camera){
		transform.setToScale(1,1);
		transform.setToRotation(getRotation(), camera.screenXPos(getXPos()+image.getWidth()/2), camera.screenYPos(getYPos()+image.getHeight()/2));
		transform.translate(camera.screenXPos(getXPos()),camera.screenYPos(getYPos()));
		transform.scale(camera.screenXScale(),camera.screenYScale());

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, transform, null);
	}

	public void render(GL2 gl, Camera camera){
	}

	public void die(){
		Unit thisUnit=this;
		new Input(true){
			@Override
			public void function(){
				map.units.remove(thisUnit);
				map.destroyBody(bd);
			}
		};
	}

	public void collide(Unit u){}

	public int getScreenXPos(Camera camera){
		return camera.screenXPos(getXPos());
	}
	public int getScreenYPos(Camera camera){
		return camera.screenYPos(getYPos());
	}
	public boolean onClick(){
		if(Engine.debug)System.out.println("Clicked on "+getClass());
		return false;
	}
	public boolean clickHit(double worldX, double worldY){
		return false;//collision.intersects(Engine.engine.getMouseWorldX(),Engine.engine.getMouseWorldY());
	}
	public double getXPos(){return bd.m_xf.p.x;}

	public void setXPos(float x){bd.setTransform(new Vec2(x, ((float) getYPos())), ((float) getRotation()));}

	public double getYPos(){return bd.m_xf.p.y;}

	public void setYPos(float y){bd.setTransform(new Vec2(((float) getXPos()),y), ((float) getRotation()));}

	public double getRotation(){
		return bd.m_xf.q.getAngle();
	}

	public void setRotation(float rot){
		bd.setTransform(new Vec2(((float) getXPos()), ((float) getYPos())),rot);
		//bd.m_xf.q.set(rot);
	}

	public void setBodyType(BodyType type){bd.setType(type);}

	public void setRadius(float size){
		this.size=size;
		Fixture fixture=bd.getFixtureList();
		if(fixture!=null)
		bd.destroyFixture(bd.getFixtureList());

		CircleShape edge = new CircleShape();
		edge.setRadius(size/2);
		bd.createFixture(edge, 0.0f);
	}

	public float getSize(){return size;}

	public void setPos(float x, float y){ bd.setTransform(new Vec2(x,y), ((float) getRotation()));}
	public void setMass(float m){bd.m_mass=m;}
}
