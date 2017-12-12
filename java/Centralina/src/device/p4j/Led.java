package device.p4j;

import java.io.IOException;

import com.pi4j.io.gpio.*;

import device.*;


public class Led implements Light {
	private GpioPinDigitalOutput pin;
	
	public Led(int pinNum){
		try {
		    GpioController gpio = GpioFactory.getInstance();
		    pin = gpio.provisionDigitalOutputPin(Config.pinMap[pinNum]);		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void switchOn() throws IOException {
		pin.high();		
		//System.out.println("LIGHT ON - pin "+pin);
	}

	public synchronized void switchOff() throws IOException {
		pin.low();
		//System.out.println("LIGHT OFF - pin "+pin);		
	}
	
}
