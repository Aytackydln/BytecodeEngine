package Engine;

import Engine.ShapedUnits.Wall;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Map extends World{
	final public static int WALL_THICKNESS=50;
	protected static Camera camera;
	public String name="";
	public int width=250, height=250;
	public final ArrayList<Unit> units=new ArrayList<>();
	final private TreeSet<Integer> emptyInput=new TreeSet<>();

	public Map(int width, int height){
		super(new Vec2(0,0));
		this.width=width;
		this.height=height;
		//top wall
		new Wall(this,
				-width/2-WALL_THICKNESS,
				-height/2-WALL_THICKNESS,
				width+WALL_THICKNESS*2,
				WALL_THICKNESS);
		//left wall
		new Wall(this,
				-width/2-WALL_THICKNESS,
				-height/2-WALL_THICKNESS,
				WALL_THICKNESS,
				height+WALL_THICKNESS*2);

		//right wall
		new Wall(this,
				width/2,
				-height/2-WALL_THICKNESS,
				WALL_THICKNESS,
				height+WALL_THICKNESS*2);

		//bottom wall
		new Wall(this,
				-width/2-WALL_THICKNESS,
				height/2,
				width+WALL_THICKNESS*2,
				WALL_THICKNESS);
		setAllowSleep(true);
		setWarmStarting(true);
		setSubStepping(true);
		setContinuousPhysics(true);
	}

	public void tick(double delta, Set<Integer> inputs){
		int vIterations=(int)(delta*500);
		if(vIterations<0)vIterations=1;
		step( ((float)10/Ticker.gameHertz),200/Ticker.gameHertz,200/Ticker.gameHertz);
		for(Unit u:units)u.tick(delta,inputs);
		clearForces();
	}

	public final void tick(double delta){
		tick(delta,emptyInput);
	}
	public final void tick(){tick(0);}

	public void render(Graphics g, Camera camera){
		try{
			for(Unit u:units)u.render(g,camera);
		}catch(NullPointerException e){
		}
	}
}
