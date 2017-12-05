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
	
	
}
