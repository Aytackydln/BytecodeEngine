package Engine;
/*0.3.1

 */

import Engine.MenuItems.GameButton;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.OBBViewportTransform;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;


public abstract class Engine{
	public static boolean run=true;
	public static boolean debug=true;
	protected static ArrayList<String> variables=new ArrayList<>();
	private final Ticker ticker;
	private final JoglDebugDraw debugDraw;
	public Map map;

	public static Random rng=new Random();
	private ArrayList<Text> texts=new ArrayList<>();
	private String settingFile="settings.txt";

	public Engine(){
		Input.engine=this;
		readSett();

		variables.addAll(new ArrayList<>(Arrays.asList("debug")));

		final BPanel panel=new BPanel(this);
		final BFrame frame=new BFrame("Bytecode Engine", panel);
		final ComboListener comboListener=new ComboListener(this,frame);

		//debugDraw=new DebugDrawJ2D(panel,false);
		debugDraw=new JoglDebugDraw(panel);
		debugDraw.appendFlags(DebugDraw.e_shapeBit);
		debugDraw.appendFlags(DebugDraw.e_jointBit);

		Input.frame=frame;
		GameButton.frame=frame;
		ticker=new Ticker(this,panel,debug);
		map=new Map(200,300);

		panel.addKeyListener(comboListener);
		panel.addMouseListener(comboListener);
		panel.addMouseMotionListener(comboListener);
	}

	private void readSett(){
		if(debug)System.out.println("reading settings");
		try{
			List<String> readSettings=Files.readAllLines(Paths.get(settingFile));
			String[] change;
			for(String s : readSettings){

				change=s.split("=");
				String var=change[0];
				try{
					String val=change[1];
					if(var.equals("debug")){
						if(val.equals("true"))debug=true;
						else debug=false;
					}else{
						if(debug)System.out.println("set "+var+" to "+val);
						this.getClass().getDeclaredField(var).set(this, val);
					}
				}catch(Throwable e){
					try{
						this.getClass().getField(var).get(this);
					}catch(Throwable e1){
						if(debug)System.out.println("Could not set saved variable ("+var+")");
					}
				}
			}
		}catch(IOException e){
			try{
				new Formatter(settingFile);
				if(debug)System.out.println("settings.txt created");
			}catch(FileNotFoundException ignored){
				if(debug)System.out.println("Could not create settings.txt");
			}
		}
	}

	void saveConf(){
		String s="";
		for(String a:variables)
			try{
				s+=a+"="+this.getClass().getDeclaredField(a).get(this)+"\n";
			}catch(Exception e){
				try{
					s+=a+"="+this.getClass().getField(a).get(this)+"\n";
				}catch(Exception e1){
					try{
						s+=a+"="+getClass().getField(a).get(this)+"\n";
					}catch(Exception e2){
						if(debug)System.out.println("There was an error saving "+a+e1);
						e1.printStackTrace();
					}
				}
			}
		try(Formatter f=new Formatter(new File(settingFile))){
			f.format(s);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(debug)System.out.println("saved configs");
	}

	public static void main(){
		System.out.println("you must ivoke run() from implemented class");
	}

	public void addText(String text, int xpos, int ypos){
		texts.add(new Text(text, xpos, ypos));
	}
	public void removeText(String identity){
		for(Text t:texts)if(t.identity.equals(identity))texts.remove(t);
	}
	public void loadMap(Map map){
		this.map=map;
		map.tick(0);
		map.setDebugDraw(debugDraw);
		OBBViewportTransform view=new OBBViewportTransform();
		//view.setCamera(0f,0f,1f);
		debugDraw.setViewportTransform(view);
	}

	void render(Graphics g,Camera camera){
		try{
		for(Text t : texts) t.render(g,camera);
		for(Unit u:map.units)u.render(g,camera);
		draw(g,camera);
		}catch(ConcurrentModificationException e){
			if(debug){
				System.out.println("Illegal modification");
				e.printStackTrace();
			}
		}catch(NullPointerException e){
			System.out.println("nothing to draw");
		}
	}
	void drawDebug(){
		try{
			map.drawDebugData();
		}catch(NullPointerException e){

		}
	}

	abstract protected void draw(Graphics g,Camera camera);


	protected abstract void gameCodes(double delta, Set<Integer> pressed);
	protected abstract void reset();
	protected abstract void initialize();
	protected void run(){
		ticker.run();
	}
	//public int getMouseX(){ return panel.mouseX; }
	//public int getMouseY(){ return panel.mouseY; }
	void insertKey(Integer key){
		ticker.insertKey(key);
	}
	void removeKey(Integer key){
		ticker.removeKey(key);
	}
	void setClicked(boolean clicked){
		//ticker.setClicked(clicked);
		if(clicked){
			ticker.insertKey(MouseEvent.BUTTON1);
		}else {
			ticker.removeKey(MouseEvent.BUTTON1);
		}
	}
}