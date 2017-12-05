package centralina.states;

import centralina.CentralinaState;

public class DetectedState extends CentralinaState{

	private float previousDistance;
	private float newDistance;
	//maximum distance between two reads that still identifies the same object
	public float delta;
	private boolean objectTerminated;
	
	public DetectedState() {
		previousDistance = -1f;
		newDistance = -1f;
		objectTerminated = false;
	}
	
	@Override
	public void doAction() {
		
		if (previousDistance == -1) {
			this.previousDistance = this.centralina.getDistance();
			this.newDistance = this.previousDistance;
		}
		else {
			this.newDistance = this.centralina.getDistance();
			if (Math.abs(this.previousDistance-this.newDistance) > this.delta) {
				this.objectTerminated = true;
			}
			else {
				this.previousDistance = this.newDistance;
			}
		}
		
	}

	@Override
	public CentralinaState nextState() {
		if (this.objectTerminated) {
			return new ScanningState();
		}
		else {
			return this;
		}
	}

}
