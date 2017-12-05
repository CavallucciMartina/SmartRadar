package centralina.states;

import centralina.CentralinaState;

public class RepositioningState extends CentralinaState{
	
	private int deg ;
	private int direction; /*0->sx 1->dx*/

	public RepositioningState() {
		super();
		this.deg = this.centralina.getDeg();
		this.direction=this.centralina.getDirection();
	}

	@Override
	public void doAction() {
		this.sendDirection();
		this.gotoIdle();
		
		
	}

	@Override
	public CentralinaState nextState() {
		if(this.direction != -1) {
			return this;
		}else if(this.deg == 90 && this.direction == -1) {
			return new IdleState();
		}
		return this;
	
	}
	private void sendDirection() {
		if(this.deg > 90  && this.direction!=-1){
			this.direction = 1;
			/*devo comunicare ad arduino la mia direzione */
		}else if(this.deg < 90 && this.direction!=-1) {
			this.direction = 0;
			/*devo comunicare ad arduino la mia direzione */
		}
		}
		
	
	private void gotoIdle() {
		this.deg = 90;
		this.direction = -1;
	}
}
