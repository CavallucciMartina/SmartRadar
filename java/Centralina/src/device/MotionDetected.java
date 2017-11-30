package device;

import common.*;

public class MotionDetected implements Event {
	private ObservableMotionDetectorSensor source;
	
	public MotionDetected(ObservableMotionDetectorSensor source){
		this.source = source;
	}
	
	public ObservableMotionDetectorSensor getSource(){
		return source;
	}
}
