package centralina;

public abstract class CentralinaState {

	public Centralina centralina;
	
	public abstract void doAction();
	public abstract CentralinaState nextState();
	
}
