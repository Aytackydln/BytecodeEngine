package Engine;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Ticker extends Thread{
	private static final double maxDelta=0.03191;    //as seconds
	private final static Set<Integer> pressed=new TreeSet<>();
	public static Ticker ticker;
	static int gameHertz=128;
	static int target_fps=64;
	final ArrayList<Input> preInputs=new ArrayList<>();
	final ArrayList<Input> postInputs=new ArrayList<>();
	private final Engine engine;
	private final BPanel panel;
	private final boolean debug;
	double delta;    		//as seconds
	private long timeBetweenUpdates=1000000000/gameHertz;
	private long targetTimeBetweenRenders=1000000000/target_fps;
	private int frameCount, updateCount;
	Ticker(Engine engine,BPanel panel, boolean debug){
		this.debug=debug;
		this.engine=engine;
		this.panel=panel;
		ticker=this;
	}

	public void run(){
		engine.initialize();

		long lastUpdateTime=System.nanoTime();
		long previousUpdateTime;
		long lastRenderTime=0;

		new TimedEvent(500){
			@Override
			public void run(){
				while(engine.run){
					super.run();
					final int fps=frameCount*2;
					final int ups=updateCount*2;
					panel.ups=ups;
					updateCount=0;
					panel.fps=fps;
					frameCount=0;
					panel.getGraphics().dispose();
					if(debug){
						if(DebugController.controller!=null){/*
							DebugController.values.put("fps",String.valueOf(fps));
							DebugController.values.put("ups",String.valueOf(ups));*/
							Platform.runLater(()->{
								DebugController.controller.setUps(ups);
								DebugController.controller.setFps(fps);
							});
						}
					}
				}
			}
		}.start(); //fps and ups updater
		ArrayList<Input> inputs=new ArrayList<>();
		// Game loop
		while(engine.run){
			if(System.nanoTime()-lastUpdateTime>= timeBetweenUpdates){
				previousUpdateTime=lastUpdateTime;
				lastUpdateTime=System.nanoTime();
				delta=(System.nanoTime()-(double) previousUpdateTime)/1e9;
				//delta=TimeUnit.SECONDS.convert(System.nanoTime()-previousUpdateTime, TimeUnit.NANOSECONDS);
				if(DebugController.controller!=null){
					Platform.runLater(()->{
						DebugController.controller.setDelta(delta);
					});
				}else System.out.println("debug controller not found");
				if(delta>maxDelta){
					System.out.println("latency detected "+delta);
					delta=maxDelta;
				}
				long timeBeforeCode=System.nanoTime();

				inputs.clear();
				inputs.addAll(preInputs);
				preInputs.clear();
				for(Input i:inputs)i.function();
				engine.gameCodes(delta, pressed);
				inputs.clear();
				inputs.addAll(postInputs);
				postInputs.clear();
				for(Input i:inputs)i.function();

				//as nanoseconds
				long codeTime=System.nanoTime()-timeBeforeCode;
				updateCount++;
				if(System.nanoTime()-lastRenderTime>= targetTimeBetweenRenders){
					panel.repaint(10);
					lastRenderTime=lastUpdateTime;
					frameCount++;
				}

				//as miliseconds, dynamically change depending on code intensity
				long sleepTime=(timeBetweenUpdates-codeTime)/1000000;
				try{
					TimeUnit.MILLISECONDS.sleep(sleepTime);
				}catch(Exception ignored){
				}
			}
		}
		engine.saveConf();
		System.exit(0);
	}

	public void setFps(int fps){
		target_fps=fps;
		targetTimeBetweenRenders=1000000000/target_fps;
		System.out.println(fps+" fps");
	}
	public void setUps(int ups){
		gameHertz=ups;
		timeBetweenUpdates=1000000000/gameHertz;
		System.out.println(ups+" ups");
		if(engine.debug)System.out.println("wait time: "+timeBetweenUpdates);
	}
	void insertKey(Integer key){
		pressed.add(key);
	}
	void removeKey(Integer key){
		pressed.remove(key);
	}
	//void setClicked(boolean clicked){this.clicked=clicked;}
}
