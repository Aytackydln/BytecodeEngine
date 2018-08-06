package Engine;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

public class DebugController implements Initializable{

	static DebugController controller;
	@FXML
	private Label fpsShower;
	@FXML
	private Label upsShower;
	private int ups;
	@FXML
	private TextField fpsChanger;
	@FXML
	private TextField upsChanger;
	@FXML
	private HBox outputContainer;
	@FXML
	private CheckBox debufCheck;
	@FXML
	private TextArea output;
	@FXML
	private Label mouseXP;

	@FXML
	private Label mouseYP;

	@FXML
	private Label mouseXW;

	@FXML
	private Label mouseYW;

	@FXML
	private Label deltShower;

	@FXML
	private Label mesTimeScaShower;

	@Override
	public void initialize(URL location, ResourceBundle resources){
		controller=this;
		debufCheck.setSelected(Engine.debug);
		fpsChanger.setText(String.valueOf(Ticker.target_fps));
		upsChanger.setText(String.valueOf(Ticker.gameHertz));
		/*
		SwingNode text=new SwingNode();
		text.prefWidth(150);
		JTextArea textArea=new JTextArea();
		textArea.setAutoscrolls(true);
		//textArea.setEnabled(false);
		textArea.setEditable(false);
		textArea.setPreferredSize(new Dimension(200,150));
		text.setContent(textArea);
		outputContainer.getChildren().add(text);
		*/
		System.setOut(new PrintStream(new OutputStream(){
			PrintStream console=System.out;
			@Override
			public void write(int b) throws IOException{
				console.write(b);
				Platform.runLater(()->{
					output.appendText(String.valueOf((char) b));
				});
			}
		}));
		System.out.println("initialized debug controller");
	}

	void setFps(int fps){
		//if(controller!=null)controller.fpsShower.setText(String.valueOf(fps));
		fpsShower.setText(String.valueOf(fps));
	}
	void setUps(int ups){
		//if(controller!=null)controller.upsShower.setText(String.valueOf(ups));
		this.ups=ups;
		upsShower.setText(String.valueOf(ups));
	}
	@FXML
	void setDebug(ActionEvent event) {
		Engine.debug=debufCheck.isSelected();
	}
	@FXML
	void updateSettings(ActionEvent event) {
		Ticker.ticker.setUps(Integer.valueOf(upsChanger.getText()));
		Ticker.ticker.setFps(Integer.valueOf(fpsChanger.getText()));
	}

	@FXML
	void clearConsole(ActionEvent event) {
		output.setText("");
	}

	void setMouseP(int x, int y){
		mouseXP.setText(String.valueOf(x));
		mouseYP.setText(String.valueOf(y));
	}
	void setMouseW(int x, int y){
		mouseXW.setText(String.valueOf(x));
		mouseYW.setText(String.valueOf(y));
	}
	void setDelta(double delta){
		deltShower.setText(String.valueOf(delta));
		mesTimeScaShower.setText(String.valueOf(delta*(double)ups));
	}
}
