package system;
import centralina.*;

public class TestCentralinaController {

	public static void main(String[] args) {
		System.out.println("Testing Centralina controller");
		Centralina centralina = new Centralina(args[0], args[1]);
		
		while (true) {
			centralina.getCurrentState().doAction();
			centralina.setCurrentState(centralina.getCurrentState().nextState());
			try {
				Thread.sleep((long)centralina.getStateExecutionInterval());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
