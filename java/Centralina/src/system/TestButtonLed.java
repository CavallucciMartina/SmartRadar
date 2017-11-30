package system;

import device.*;

public class TestButtonLed {
	

	public static void main(String[] args) {

	
		ObservableButton Onbutton = new device.p4j.ObservableButton(17);
		ObservableButton OffButton = new device.p4j.ObservableButton(17);
		Light ledOn = new device.p4j.Led(4);
		Light ledDetected = new device.p4j.Led(4);
		Light ledTracking = new device.p4j.Led(4);
	
		new ButtonLedController(Onbutton,OffButton,ledOn,ledDetected,ledTracking).start();
	}

}
