package centralina.states;

import centralina.*;

public class ScanningState extends CentralinaState {

	private float distance;
	private boolean objectDetected;
	private boolean tracking;
	private boolean offButtonPressed;

	public ScanningState() {
		this.distance = -1f;
		this.objectDetected = false;
		this.tracking = false;
		this.offButtonPressed = false;
	}

	@Override
	public void doAction() {
		this.checkNewObject();
		this.CheckOffButtonPressed();
	}

	@Override
	public CentralinaState nextState() {
		if(this.objectDetected && !this.tracking) {
			return new DetectedState();
		}
		else if(this.objectDetected && this.tracking) {			
			return new TrackingState();
		}
		else if(offButtonPressed) {
			return new RepositioningState();
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
			this.centralina.setLedDetected(true);
		} else if (this.distance < this.centralina.MIN_DIST) {
			this.objectDetected = true;
			this.tracking = true;
			this.centralina.setLedTracking(true);
		}
	}
	
	private void CheckOffButtonPressed() {
		if(this.centralina.IsBUttonOffPressed()) {
			this.offButtonPressed = true;
		}
	}

}
