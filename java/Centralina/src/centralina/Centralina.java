package centralina;

public class Centralina {

	private State currentState;
	
	//Variables that give inofrmation on the state of the centralina
	
	public State getCurrentState() {
		return currentState;
	}
	public void setCurrentState(State newState) {
		this.currentState = newState;
	}
	
	
}
