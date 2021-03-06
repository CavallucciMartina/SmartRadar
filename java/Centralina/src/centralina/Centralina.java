package centralina;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import centralina.states.*;
import device.Button;
import device.p4j.Led;
import msg.SerialCommChannel;

public class Centralina {

	public final static float MIN_DIST = 0.10f;
	public final static float MAX_DIST = 2f;

	private CentralinaState currentState;
	private SerialCommChannel serial;

	private float distance;
	private int currentDeg;
	private boolean clockWise;
	private int omega;
	private String port;
	private FileWriter w;
    private BufferedWriter b;
    private int countObject;

	private Button buttonOn = new device.p4j.Button(4);
	private Button buttonOff = new device.p4j.Button(5);
	private device.Light ledOn = new Led(12);
	private device.Light ledDetected = new Led(13);
	private device.Light ledTracked = new Led(14);
	private static int INTERVAL = 20;

	// Variables that give information on the state of the centralina

	public Centralina(String port, String omega) {
		try {
			this.serial = new SerialCommChannel(port, 9600);
		} catch (Exception e) {
			System.out.println("Port not found");
			System.out.println(port);
			e.printStackTrace();
		}
		this.distance = 10f;
		this.omega = Integer.valueOf(omega);
		this.currentDeg = 90;
		try {
			this.w = new FileWriter("Logger.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.b = new BufferedWriter (w);
		this.port = port;
		this.setLedDetected(false);
		this.setLedOn(false);
		this.setLedTracking(false);
		this.countObject = 0;
		this.writeOnLogger("\n\nNEW ACCESS TO CENTRALINA\n");
		
		setCurrentState(new IdleState(this));
	}
	
	public void objectDetected() {
		this.countObject++;
	}
	
	private void resetCounterObject() {
		System.out.println("TOTAL OBJECT DETECTED: " + this.countObject);
		this.countObject = 0;
	}
	
	public void writeOnLogger(String s) {
		try {
			this.b.write(s + "\n");
			this.b.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reset() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.currentDeg = 90;
		this.distance = 10f;
		this.setLedDetected(false);
		this.setLedOn(false);
		this.setLedTracking(false);
		this.clockWise = false;
		try {
			this.serial.receiveMsg();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public CentralinaState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(CentralinaState newState) {
		this.currentState = newState;
	}

	/**
	 * Rotates the servo and then reads the distance from the prox.
	 * 
	 * @return Distance detected from prox and transmitted via serial
	 */
	public void moveRadar() {
		// check if direction should be changed
		// move servo by sending msg
		// store distance in variable
		if (this.shouldChangeDirection()) {
			this.clockWise = !this.clockWise;
			this.resetCounterObject();
		}

		this.currentDeg = (this.currentDeg + (this.clockWise ? 1 : -1)
				* this.omega);

		if (this.currentDeg < 0) {
			this.currentDeg = 0;
		}
		if (this.currentDeg > 180) {
			this.currentDeg = 180;
		}

		this.serial.sendMsg(String.valueOf(this.currentDeg));
		/*try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		while (!this.serial.isMsgAvailable()) {
		}
		try {
			if (this.serial.isMsgAvailable()) {
				distance = Float.parseFloat(this.serial.receiveMsg());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public float getDistance() {
		return distance;
	}

	/**
	 * Checks if the angle is 0 or 180
	 * 
	 * @return true if angle is 0 or 180, false otherwise
	 */
	public boolean shouldChangeDirection() {
		return (this.currentDeg >= 180 || this.currentDeg <= 0);

	}

	/**
	 * 
	 * @return how much time passes from one execution and another of one state
	 *         of the fsm
	 */

	public int getStateExecutionInterval() {

		return INTERVAL;
	}

	/**
	 * Sets the value of the LedOn led
	 * 
	 * @param b
	 *            true if the led has to be turned on, false to turn it off
	 */
	public void setLedOn(boolean b) {
		if (b) {
			try {
				this.ledOn.switchOn();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.ledOn.switchOff();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void setLedTracking(boolean b) {
		if (b) {
			try {
				this.ledTracked.switchOn();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.ledTracked.switchOff();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void setLedDetected(boolean b) {
		if (b) {
			try {
				this.ledDetected.switchOn();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.ledDetected.switchOff();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean IsButtonOffPressed() {
		return this.buttonOff.isPressed();
	}

	public boolean isOnButtonPressed() {

		return this.buttonOn.isPressed();
	}

	public int getDeg() {

		return this.currentDeg;
	}

	/*
	 * public boolean getDirection() {
	 * 
	 * return this.clockWise; }
	 */
	public void radarOn() {
		this.serial.sendMsg("ON");
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void radarOff() {
		this.serial.sendMsg("OFF");
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resetRadar() {
		this.currentDeg = 90;
		this.serial.sendMsg("90");
	}

	public void moveRadar(int deg) {
		this.serial.sendMsg(String.valueOf(deg));
		while (!this.serial.isMsgAvailable()) {
		}
		try {
			if (this.serial.isMsgAvailable()) {
				distance = Float.parseFloat(this.serial.receiveMsg());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
