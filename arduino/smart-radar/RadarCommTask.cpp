#include "RadarCommTask.h"
#include "Logger.h"

#define MSG_ENTER_REQUEST "ON"
#define MSG_STOP_REQUEST "OFF"
#define SERVO_MIN 750
#define SERVO_MAX 2250

RadarCommTask::RadarCommTask(int ledPin, int servoPin, int echoPin, int trigPin){
  led = new Led(ledPin);
  prox = new Sonar(echoPin, trigPin);
  servo = new ServoTimer2();
  servo->attach(servoPin);
}
  
void RadarCommTask::init(int period){
  Task::init(period);
  Logger.log("RADAR:Init");
}
  
void RadarCommTask::tick(){
  if (MsgService.isMsgAvailable()){
    Msg* msg = MsgService.receiveMsg(); 
    const String& content = msg->getContent();
    if (content == MSG_ENTER_REQUEST){
      led->switchOn();
      Logger.log("RADAR: enter request.");
    } else if (content == MSG_STOP_REQUEST){
      led->switchOff();
      Logger.log("RADAR: stop request.");
    } else {
      if (content.toInt() <= 180 && content.toInt() >= 0) {
        servo->write(map(content.toInt(), 0, 180, SERVO_MIN, SERVO_MAX));
        Logger.log(String(prox->getDistance()));
      } else {
        Logger.log("RADAR: ERROR - wrong message sent");
      }
    }
    delete msg;    
  }
}


