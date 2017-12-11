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
		this.distance = this.centralina.getDistance();
		this.objectTrackedTerminated = false;
		this.buttonOffPressed = false;
		this.centralina.setLedTracking(true);
	
	}
	@Override
	public void doAction() {
		System.out.println("sono in TRACKING");
		this.CheckObjectTrackEnd();
		this.CheckButtonOffPressed();
	}

	@Override
	public CentralinaState nextState() {
	
		if(this.objectTrackedTerminated) {
			this.centralina.setLedTracking(false);
			return new ScanningState(this.centralina);
		}else if(this.buttonOffPressed) {
			this.centralina.setLedTracking(false);
			return new RepositioningState(this.centralina);
		}
		return this;
	}
	
	private void CheckObjectTrackEnd() {
		this.centralina.moveRadar(this.centralina.getDeg());
		this.distance = this.centralina.getDistance();
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
