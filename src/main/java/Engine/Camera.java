package Engine;

import org.jbox2d.common.OBBViewportTransform;

public class Camera{

	public double xPos, yPos;
	public double viewScale=1.0;
	//private final BPanel panel;
	//public static Camera cam;
	int width, height;
	int mouseX, mouseY;
	private double viewWidth=200;
	private double viewHeight=200;

	Camera(long xPos, long yPos){
		Input.camera=this;
		Map.camera=this;
		this.xPos=xPos;
		this.yPos=yPos;
	}

	public void changePos(double x, double y){
		this.xPos=x;
		this.yPos=y;
		setDebugTransform((float)( xPos-getPanelWidth()/2), ((float) yPos-getPanelHeight()/2), ((float) viewScale));
	}

	public void move(double x, double y){
		xPos+=x;
		yPos+=y;
	}

	public void zoom(double delta){
		viewScale+=delta;
		updateScales();
	}

	public void setView(double width, double height){
		viewWidth=width;
		viewHeight=height;
		updateScales();
	}


	public double screenXScale(){
		return viewScale;
	}

	public double screenYScale(){
		return viewScale;
	}

	public int screenXSize(double worldSize){
		return (int) (worldSize*viewScale);
	}
	public int screenYSize(double worldSize){
		return (int) (worldSize*viewScale);
	}
	public int screenXPos(double worldPos){
		return (int) ((worldPos-xPos)*viewScale+getPanelWidth()/2);
	}
	public int screenYPos(double worldPos){
		return (int) -((worldPos-yPos)*viewScale-getPanelHeight()/2);
	}

	private void updateScales(){
		updateScales(width,height);
	}
	public void updateScales(int width, int height){
		this.width=width;
		this.height=height;
		double intendedRatio=viewHeight/viewWidth;
		double actualRatio=(double)getPanelHeight()/getPanelWidth();
		if(intendedRatio<actualRatio){
			viewScale=getPanelWidth()/viewWidth;

			if(Engine.debug)System.out.println("Panel sizes "+getPanelWidth()+" "+getPanelHeight()+" if "+viewScale);
		}else{
			viewScale=getPanelHeight()/viewHeight;

			if(Engine.debug)System.out.println("Panel sizes "+getPanelWidth()+" "+getPanelHeight()+" else "+viewScale);
		}
		setDebugTransform((float)( xPos-getPanelWidth()/2), ((float) yPos-getPanelHeight()/2), ((float) viewScale));
	}

	public double screenToWorldXPos(int mouseX){
		//return (2*mouseX-getPanelWidth())/viewScale+xPos;
		return (mouseX-getPanelWidth()/2)/viewScale+xPos;
	}
	public double screenToWorldYPos(int mouseY){
		//return (2*mouseY-getPanelHeight())/viewScale+yPos;
		return (mouseY-getPanelHeight()/2)/(-viewScale)+yPos;
	}
	public int getPanelWidth(){/*
		int width=panel.getWidth();
		if(width>0) return width;
		else return 1;*/
		return width;
	}
	public int getPanelHeight(){/*
		int height=panel.getHeight();
		if(height>0) return height;
		else return 1;*/
		return height;
	}
	private void setDebugTransform(float x, float y, float scale){
		OBBViewportTransform view=new OBBViewportTransform();
		view.setCamera(x,y,scale);
		view.setYFlip(false);
		JoglDebugDraw.debugDraw.setViewportTransform(view);
	}
	float getXPos(){
		//return transform.getCenter().x;
		return 0f;
	}
	float getYPos(){
		//return transform.getCenter().y;
		return 0f;
	}
}
