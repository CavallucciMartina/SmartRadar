package centralina;
import centralina.states.*;

public class Centralina {


	public final static float MIN_DIST = 0f;

	private CentralinaState currentState;
	
	private float distance;
	
	//Variables that give information on the state of the centralina
	
	public Centralina() {
		distance = -1f;
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
	public void moveRadar() {
		//check if direction should be changed
		//move servo by sending msg
		//store distance in variable
	}
	
	
	public float getDistance() {
		return distance;
	}

	
	/**
	 * Checks if the angle is 0 or 180
	 * @return true if angle is 0 or 180, false otherwise
	 */
	public boolean shouldChangeDirection() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 * @return how much time passes from one execution and another of one state of the fsm
	 */
	
	public float getStateExecutionInterval() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/**
	 * Sets the value of the LedOn led
	 * @param b true if the led has to be turned on, false to turn it off
	 */
	public void setLedOn(boolean b) {
		// TODO Auto-generated method stub
		
	}
	public void setLedTracking(boolean b) {
		// TODO Auto-generated method stub
		
	}
	public boolean IsBUttonOffPressed() {
		//TODO
		return false;
	}
	public int getDeg() {
		//TODO
		return 0;
	}
	
}
