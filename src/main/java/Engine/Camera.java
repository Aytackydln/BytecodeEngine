package Engine;

import java.awt.*;

public class Camera{
	public double xPos, yPos;
	public double viewScale=1.0;
	public double viewWidth=200;
	public double viewHeight=200;
	public static Camera cam;
	private final Frame frame;

	public static int width, height;

	Camera(Frame frame,long xPos, long yPos){
		this.frame=frame;
		cam=this;
		this.xPos=xPos;
		this.yPos=yPos;
	}

	public void chanePos(double x, double y){
		this.xPos=x;
		this.yPos=y;
	}

	public void move(double x, double y){
		xPos+=x;
		yPos+=y;
	}

	public void zoom(double delta){
		viewScale+=delta;
		updateScales();
	}


	public double screenXScale(){
		return viewScale;
	}

	public double screenYScale(){
		return viewScale;
	}

	public int screenXSize(double a){
		return (int) (a*viewScale);
	}
	public int screenYSize(double a){
		return (int) (a*viewScale);
	}
	public int screenXPos(double a){
		return (int) ((a-xPos)*viewScale+width/2);
	}
	public int screenYPos(double a){
		return (int) ((a-yPos)*viewScale+height/2);
	}

	public void updateScales(){
		width=Engine.engine.getWidth();
		height=Engine.engine.getHeight();
		double intendedRatio=viewHeight/viewWidth;
		double actualRatio=getFrameHeight()/getFrameWidth();
		if(intendedRatio<actualRatio){
			viewScale=getFrameWidth()/viewWidth;

			System.out.println(frame.getWidth()+" "+frame.getHeight()+" if "+viewScale);
		}else{
			viewScale=getFrameHeight()/(viewHeight+100);

			System.out.println(frame.getWidth()+" "+frame.getHeight()+" else "+viewScale);
		}
		System.out.println(viewHeight+" "+viewWidth);
		if(Engine.debug) System.out.println("Zoom is now: "+viewScale);
	}

	public int getFrameWidth(){
		int width=Engine.engine.getWidth();
		if(width>0) return width;
		else return 1;
	}
	public int getFrameHeight(){
		int height=Engine.engine.getHeight();
		if(height>0) return height;
		else return 1;
	}

	public double screenToWorldXPos(int mouseX){
		return (2*mouseX-width)/viewScale+xPos;
	}

	public double screenToWorldYPos(int mouseY){
		return (2*mouseY-width)/viewScale+xPos;
	}
}
