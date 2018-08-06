package Engine;

public abstract class Input{
	public static Engine engine;
	public static BFrame frame;
	public static Camera camera;
	public Input(){
		Ticker.ticker.preInputs.add(this);
	}
	public Input(boolean postTick){
		if(postTick)Ticker.ticker.postInputs.add(this);
		else Ticker.ticker.preInputs.add(this);
	}
	public abstract void function();
}
