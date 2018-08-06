package Sandbox.Units;

import Engine.Camera;
import Engine.Input;
import Engine.Map;
import Engine.Unit;
import Sandbox.Main;
import Sandbox.Units.Projectiles.Arrow;
import Sandbox.Units.Projectiles.Slash;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Player extends Unit{
	public String name;
	float movementSpeed=75;
	double attackTime=1.3;
	double attackWait;


	public Player(Map map, int x, int y){
		super(map,x,y);
		Main.player=this;
		name=Main.playerName;
		setBodyType(BodyType.DYNAMIC);
		setRadius(50);
		bd.setFixedRotation(true);

		try{
			image=ImageIO.read(new File("eyes.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void collide(Unit u){
		super.collide(u);

		try{

			//Thread.sleep(1000);
		}catch(Exception e){

		}
	}

	@Override
	public void tick(double delta, Set<Integer> inputs){
		//double rotation=Math.atan2(Main.engine.getMouseY()-getScreenYPos(),Main.engine.getMouseX()-getScreenXPos());	//TODO fix
		//setRotation(((float) rotation));
		byte x=0;
		byte y=0;
		boolean move=false;
		if(inputs.contains(KeyEvent.VK_S)){
			move=true;
			y--;
		}
		if(inputs.contains(KeyEvent.VK_W)){
			move=true;
			y++;
		}
		if(inputs.contains(KeyEvent.VK_D)){
			move=true;
			x++;
		}
		if(inputs.contains(KeyEvent.VK_A)){
			move=true;
			x--;
		}
		if(move){
			double moveRotation=Math.atan2(y,x);
			double xSpeed=movementSpeed*Math.cos(moveRotation);
			double ySpeed=movementSpeed*Math.sin(moveRotation);
			bd.setLinearVelocity(new Vec2(((float) xSpeed), ((float) ySpeed)));
		}

		if(inputs.contains(KeyEvent.VK_SPACE)&&attackWait<=0){
			new Slash(this, 2, getRotation());
			attackWait=attackTime;
		}

		new Input(true){
			@Override
			public void function(){
				camera.changePos(getXPos(),getYPos());
			}
		};

		attackWait-=delta;

		//if(Main.clicked&&attackWait<=0){
		if(inputs.contains(MouseEvent.BUTTON1)&&attackWait<=0){
			System.out.println();
			for(int i:inputs) System.out.print(i+" ");
			System.out.println(MouseEvent.BUTTON1);
			new Arrow(map,this);
			attackWait=attackTime;
		}

	}

	@Override
	public void render(Graphics g, Camera camera){
		super.render(g,camera);

		g.drawOval(camera.screenXPos(getXPos()), camera.screenYPos(getYPos()), camera.screenXSize(getSize()), camera.screenYSize(getSize()));

		g.drawString(name, camera.screenXPos( getXPos()-name.length()*3.5),camera.screenYPos(getYPos()+getSize()));
	}
}
