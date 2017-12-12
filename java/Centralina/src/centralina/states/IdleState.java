package centralina.states;
import centralina.*;

public class IdleState extends CentralinaState{

	private boolean onButtonPressed;
	
	public IdleState(Centralina centralina) {
		this.onButtonPressed=false;	
		this.centralina = centralina;
	}
	
	@Override
	public void doAction() {
		checkOnButtonPressed();		
	}

	@Override
	public CentralinaState nextState() {
		if(onButtonPressed) {
			return new ScanningState(this.centralina);
		}
		else {
			return this;
		}
	}
	
	private void checkOnButtonPressed() {
		if(this.centralina.isOnButtonPressed()) {
			this.onButtonPressed = true;
			this.centralina.setLedOn(true);
			this.centralina.radarOn();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
