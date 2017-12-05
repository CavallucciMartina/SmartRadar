package centralina;
import centralina.states.*;

public class Centralina {

	private CentralinaState currentState;
	
	//Variables that give information on the state of the centralina
	
	public Centralina() {
		setCurrentState(new IdleState());
	}
	
	public CentralinaState getCurrentState() {
		return currentState;
	}
	public void setCurrentState(CentralinaState newState) {
		this.currentState = newState;
	}

	/**
	 * Rotates the servo and then reads the distance from the prox.
	 * @return Distance detected from prox and transmitted via serial
	 */
	public float getDistance() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
