package system;

import java.io.IOException;

import common.*;
import device.*;

public class ButtonLedController extends BasicEventLoopController {
	
	private Light ledOn;
	private Light ledDetected;
	private Light ledTracking;
	private ObservableButton OnButton;
	private ObservableButton OffButton;
	private ObservableTimer timer;
	private enum State {ON, IDLE};
	private State currentState;

	public ButtonLedController(ObservableButton Onbutton, 
								ObservableButton OffButton,
								Light ledOn, 
								Light ledDetected,
								Light ledTracking){
		this.ledOn = ledOn;
		this.ledTracking = ledTracking;
		this.ledDetected = ledDetected;
		this.OnButton = Onbutton;
		this.OffButton = OffButton;
		this.timer = new ObservableTimer();	
		this.startObserving(Onbutton);
		this.startObserving(timer);
		currentState = State.IDLE;
	}
	
	protected void processEvent(Event ev){
		switch (currentState){
		case IDLE:
			try {
				if (ev instanceof ButtonPressed){
					ledOn.switchOn();
					ledDetected.switchOn();
					
					timer.start(500);
					currentState = State.ON;
					/*QUI DOVREMMO DIRE CON LA SERIALE AD ARDUINO 
					 * CHE IL SERVO PUÒ PARTIRE 
					 */
				}
			} catch (IOException ex){
				ex.printStackTrace();
			}
			break;
		case ON:
			break;
		default:
			break;
	
		}
	}
}
