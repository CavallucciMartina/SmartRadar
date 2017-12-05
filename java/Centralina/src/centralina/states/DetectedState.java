package centralina.states;

import centralina.CentralinaState;

public class DetectedState extends CentralinaState{

	private float previousDistance;
	private float newDistance;
	//maximum distance between two reads that still identifies the same object
	public float delta;
	private boolean objectTerminated;
	//time passed since this state has been started. Used for turning off the led after 0.1
	public float timeFromStateStart;
	public float maximumLedOnTime;
	
	public DetectedState() {
		this.previousDistance = this.centralina.getDistance();
		this.newDistance = -1f;
		this.delta = 1f;
		this.objectTerminated = false;
		this.timeFromStateStart = 0;
		this.maximumLedOnTime = 0.1f;
	}
	
	@Override
	public void doAction() {
		this.CheckObjectEnd();
		this.CheckLedOnTime();	
	}

	@Override
	public CentralinaState nextState() {
		if (this.objectTerminated || this.centralina.shouldChangeDirection()) {
			return new ScanningState();
		} else if (this.newDistance <= this.centralina.MIN_DIST) {
			return new TrackingState();
		}
		else {

			return this;
		}
	}
	
	private void CheckObjectEnd() {
		this.centralina.moveRadar();
		this.newDistance = this.centralina.getDistance();
		if (Math.abs(this.previousDistance-this.newDistance) > this.delta) {
			this.objectTerminated = true;
		}
		else {
			this.previousDistance = this.newDistance;
		}
	}
	
	private void CheckLedOnTime() {
		this.timeFromStateStart += this.centralina.getStateExecutionInterval();
		if (this.timeFromStateStart >= this.maximumLedOnTime) {
			this.centralina.setLedOn(false);
		}
	}

}
