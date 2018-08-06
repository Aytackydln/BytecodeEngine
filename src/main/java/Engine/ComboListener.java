package Engine;

import javafx.application.Platform;

import java.awt.event.*;

public class ComboListener implements KeyListener, ActionListener, ItemListener, MouseListener, MouseMotionListener{
	final Engine engine;
	private final BFrame frame;
	private final Camera camera;

	ComboListener(Engine engine, BFrame frame){
		this.engine=engine;
		this.frame=frame;
		this.camera=frame.panel.camera;
	}

	@Override
	public void keyPressed(KeyEvent e){
		Integer a=e.getKeyCode();
		new Input(){
			@Override
			public void function(){
				engine.insertKey(a);
			}
		};
		if(a=='K'){
			if(!frame.menuBar.isVisible()){
				frame.menuBar.setVisible(true);
			}
		}
		else if(a=='L'){
			if(frame.menuBar.isVisible()){
				frame.menuBar.setVisible(false);
			}
		}else if(a==KeyEvent.VK_NUMPAD6) camera.move(2, 0);
		else if(a==KeyEvent.VK_NUMPAD4) camera.move(-2, 0);
		if(a==KeyEvent.VK_NUMPAD8) camera.move(0, 2);
		else if(a==KeyEvent.VK_NUMPAD2) camera.move(0, -2);
		else if(a==KeyEvent.VK_NUMPAD9) camera.zoom(5);
		else if(a==KeyEvent.VK_NUMPAD3) camera.zoom(-5);
	}

	@Override
	public void keyReleased(KeyEvent e){
		new Input(true){
			@Override
			public void function(){
				engine.removeKey(e.getKeyCode());
			}
		};
	}

	@Override
	public void keyTyped(KeyEvent e){}

	@Override
	public void itemStateChanged(ItemEvent e){
		//JMenuItem source=(JMenuItem) (e.getSource());
	}

	@Override
	public void mouseClicked(MouseEvent e){
		engine.setClicked(true);
		for(Unit u:engine.map.units){
			if(u.clickHit(camera.screenToWorldXPos(e.getX()),camera.screenToWorldYPos(e.getY()))){
				if(u.onClick())	break;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e){
		engine.setClicked(true);
		frame.panel.camera.mouseX=e.getX();
		frame.panel.camera.mouseY=e.getY();
	}
	@Override
	public void mouseMoved(MouseEvent e){
		frame.panel.camera.mouseX=e.getX();
		frame.panel.camera.mouseY=e.getY();

		Platform.runLater(()->{
			DebugController.controller.setMouseP(e.getX(),e.getY());
			DebugController.controller.setMouseW(((int) camera.screenToWorldXPos(e.getX())), ((int) camera.screenToWorldYPos(e.getY())));
		});
	}

	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}
	@Override
	public void mousePressed(MouseEvent e){
		engine.setClicked(true);
	}
	@Override
	public void mouseReleased(MouseEvent e){
		new Input(true){
			@Override
			public void function(){
				engine.setClicked(false);
			}
		};
		if(Engine.debug)System.out.println("released mouse");
	}

	@Override
	public void actionPerformed(ActionEvent e){

	}
}
