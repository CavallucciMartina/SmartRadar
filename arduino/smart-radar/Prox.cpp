#include "Sonar.h"
#include "Arduino.h"

/*
 * Because frequently the sensor sends huge unrealistic data (33.XX instead of 4.YY)
 * it's necessary to define un upper bound.
 */
#define MAX_DIST 10

Sonar::Sonar(int pinEcho, int pinTrig) : pinEcho(pinEcho), pinTrig(pinTrig){
  pinMode(pinTrig, OUTPUT);
  pinMode(pinEcho, INPUT);  

}

float Sonar::getDistance(){
    digitalWrite(pinTrig,LOW);
    delayMicroseconds(3);
    digitalWrite(pinTrig,HIGH);
    delayMicroseconds(5);
    digitalWrite(pinTrig,LOW);
    
    float tUS = pulseIn(pinEcho, HIGH);
    float t = tUS / 1000.0 / 1000.0 / 2;
    float d = t*vs;
    return d > MAX_DIST ? MAX_DIST : d;
}

