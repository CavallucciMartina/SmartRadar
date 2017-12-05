package system;
import centralina.*;

public class TestCentralinaController {

	public static void main(String[] args) {
		System.out.println("Testing Centralina controller");
		Centralina centralina = new Centralina();
		while (true) {
			centralina.getCurrentState().doAction();
			centralina.setCurrentState(centralina.getCurrentState().nextState());
			//Sleep?
		}
	}

}
