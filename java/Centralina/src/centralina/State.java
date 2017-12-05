package centralina;

public interface State {

	void doAction();
	State nextState();
	
}
