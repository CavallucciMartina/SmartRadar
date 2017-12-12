package centralina.states;

import centralina.Centralina;
import centralina.CentralinaState;

public class RepositioningState extends CentralinaState{
	
	private int deg ;


	public RepositioningState(Centralina centralina) {
		super();
		this.centralina = centralina;
		this.deg = this.centralina.getDeg();

	}

	@Override
	public void doAction() {
		System.out.println("sono in REPOSITIONING");
		this.centralina.resetRadar();
		this.deg = this.centralina.getDeg();
	}

	@Override
	public CentralinaState nextState() {
		
		 if(this.deg == 90 ) {
			 try {
				 Thread.sleep(100);
				this.centralina.radarOff();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//return new IdleState(new Centralina(this.centralina.getPort()));
			 this.centralina.setLedOn(false);
			 return new IdleState(this.centralina);
		}
		return this;
	
	}
	


}
