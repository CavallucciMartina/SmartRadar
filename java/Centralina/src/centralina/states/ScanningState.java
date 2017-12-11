package centralina.states;

import centralina.*;

public class ScanningState extends CentralinaState {

	private float distance;
	private boolean objectDetected;
	private boolean tracking;
	private boolean offButtonPressed;

	public ScanningState(Centralina centralina) {
		this.distance = -1f;
		this.centralina = centralina;
		this.objectDetected = false;
		this.tracking = false;
		this.offButtonPressed = false;
	}

	@Override
	public void doAction() {
		System.out.println("sono in SCANNING");
		this.checkNewObject();
		this.checkOffButtonPressed();
	}

	@Override
	public CentralinaState nextState() {
		if(this.objectDetected && !this.tracking) {
			return new DetectedState(this.centralina);
		}
		else if(this.objectDetected && this.tracking) {			
			return new TrackingState(this.centralina);
		}
		else if(offButtonPressed) {
			return new RepositioningState(this.centralina);
		}
		else {
			return this;
		}
	}

	private void checkNewObject() {
		this.centralina.moveRadar();
		this.distance = this.centralina.getDistance();
		if (this.distance >= this.centralina.MIN_DIST && this.distance <= this.centralina.MAX_DIST) {
			this.objectDetected = true;
		} else if (this.distance < this.centralina.MIN_DIST) {
			this.objectDetected = true;
			this.tracking = true;
		}
	}
	
	private void checkOffButtonPressed() {
		if(this.centralina.IsButtonOffPressed()) {
			this.offButtonPressed = true;
		}
	}

}
