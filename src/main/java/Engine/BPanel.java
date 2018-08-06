package Engine;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

class BPanel extends GLJPanel implements GLEventListener{
	public final Camera camera=new Camera(0, 0);
	final Engine engine;
	boolean showStats=true;
	int fps, ups;
	private Image splash;

	BPanel(Engine engine){
		super(defaultProfile());
		/*super(new GLCapabilities(GLProfile.getMaximum(true)));
		if(Engine.debug){
			GLCapabilities capabilities=new GLCapabilities(GLProfile.getDefault());
			System.out.println("Hardware acceleration: "+capabilities.getHardwareAccelerated());
			System.out.println("Auto swap buffer: "+getAutoSwapBufferMode());
		}*/
		this.engine=engine;
		setBackground(Color.BLACK);
		setFocusable(true);
		addGLEventListener(this);

		try{
			splash=ImageIO.read(getClass().getResource("/b.png"));
		}catch(IOException ignored){
			System.out.println("couldnt load splash");
		}
	}

	private static GLCapabilities defaultProfile(){
		final GLCapabilities profile=new GLCapabilities(GLProfile.getDefault());
		return profile;
	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if(showStats){
			g.drawString("FPS: "+fps+"    UPS:"+ups, 5, 10);
		}
		getGL().getGL2().glClear(GL2.GL_COLOR_BUFFER_BIT);

		engine.render(g,camera);
		try {
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		} catch (AWTError e) {
			System.out.println("Graphics context error");
		}
	}

	void drawSplash(){
		if(splash!=null)
			getGraphics().drawImage(splash,0,0,null);
	}
	public double getMouseWorldY(){
		return camera.screenToWorldYPos(camera.mouseY);
	}
	public double getMouseWorldX(){
		return camera.screenToWorldXPos(camera.mouseX);
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		getGL().getGL2().glLineWidth(1f);
		getGL().getGL2().glEnable(GL2.GL_BLEND);
		getGL().getGL2().glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		System.out.println("GLPanel initialized");
	}

	@Override
	public void dispose(GLAutoDrawable drawable){

	}

	@Override
	public void display(GLAutoDrawable drawable){
		getGL().getGL2().glClear(GL2.GL_COLOR_BUFFER_BIT);
		engine.drawDebug();
		getGL().glFlush();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){
		GL2 gl2 = drawable.getGL().getGL2();

		gl2.glMatrixMode(GL2.GL_PROJECTION);
		gl2.glLoadIdentity();

		// coordinate system origin at lower left with width and height same as the window
		GLU glu = new GLU();
		glu.gluOrtho2D(0.0f, getWidth(), 0.0f, getHeight());

		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();

		gl2.glViewport(0, 0, getWidth(), getHeight());
		camera.updateScales(width, height);
	}
}
