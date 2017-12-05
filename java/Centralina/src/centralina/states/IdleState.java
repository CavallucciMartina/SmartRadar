package centralina.states;
import centralina.*;

public class IdleState extends CentralinaState{

	private boolean onButtonPressed;
	
	public IdleState() {
		this.onButtonPressed=false;		
	}
	
	@Override
	public void doAction() {
		checkOnButtonPressed();		
	}

	@Override
	public CentralinaState nextState() {
		if(onButtonPressed) {
			return new ScanningState();
		}
		else {
			return this;
		}
	}
	
	private void checkOnButtonPressed() {
		if(this.centralina.isOnButtonPressed()) {
			this.onButtonPressed = true;
			this.centralina.setLedOn(true);
			this.centralina.setLedConnected(true);
		}
	}

}
