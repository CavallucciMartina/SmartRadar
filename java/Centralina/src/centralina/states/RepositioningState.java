package centralina.states;

import centralina.CentralinaState;

public class RepositioningState extends CentralinaState{
	
	private int deg ;


	public RepositioningState() {
		super();
		this.deg = this.centralina.getDeg();

	}

	@Override
	public void doAction() {
		this.centralina.resetRadar();
	}

	@Override
	public CentralinaState nextState() {
		
		 if(this.deg == 90 ) {
			 try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new IdleState();
		}
		return this;
	
	}
	


}
