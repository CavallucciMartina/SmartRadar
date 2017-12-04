#include "Timer.h"
#include "MsgService.h"
#include "Scheduler.h"
#include "RadarCommTask.h"

/*PIN numbers definiton*/
#define PIN_CONNECTED 2
#define PIN_SERVO 5
#define PIN_ECHO 8
#define PIN_TRIG 9

Scheduler sched;

void setup() {
  sched.init(50);
  MsgService.init();
  RadarCommTask* rCommTask = new RadarCommTask(PIN_CONNECTED, PIN_SERVO, PIN_ECHO, PIN_TRIG);
  rCommTask->init(50);
  sched.addTask(rCommTask);
}

void loop() {
  sched.schedule();
}
