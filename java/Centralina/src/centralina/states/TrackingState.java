package centralina.states;

import centralina.Centralina;
import centralina.CentralinaState;

public class TrackingState extends CentralinaState {
	
	private float distance;
	private boolean objectTrackedTerminated;
	private boolean buttonOffPressed;

	public TrackingState(Centralina centralina) {
		super();
		super.centralina = centralina;
		this.distance = -1f;
		this.objectTrackedTerminated = false;
		this.buttonOffPressed = false;
	
	}
	@Override
	public void doAction() {
		this.CheckObjectTrackEnd();
		this.CheckButtonOffPressed();
	}

	@Override
	public CentralinaState nextState() {
	
		if(this.objectTrackedTerminated) {
			return new ScanningState(this.centralina);
		}else if(this.buttonOffPressed) {
			return new RepositioningState(this.centralina);
		}
		return this;
	}
	
	private void CheckObjectTrackEnd() {
		if(this.distance > this.centralina.MIN_DIST) {
			this.objectTrackedTerminated = true;
			this.centralina.setLedTracking(false);
		}
	}
	private void CheckButtonOffPressed() {
		if(this.centralina.IsButtonOffPressed()) {
			this.buttonOffPressed = true;
			this.centralina.radarOff();
		}
	}

}
