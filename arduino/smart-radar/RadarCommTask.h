#ifndef __CAR_COMM_TASK__
#define __CAR_COMM_TASK__

#include "Task.h"
#include "Led.h"
#include "Sonar.h"
#include "MsgService.h"
#include <ServoTimer2.h>

class RadarCommTask: public Task {

public:

  RadarCommTask(int ledPin, int servoPin, int echoPin, int trigPin);
  void init(int period);  
  void tick();

private:
  Light * led;
  ProximitySensor * prox;
  ServoTimer2 * servo;
};

#endif

