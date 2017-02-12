# External module imports
import RPi.GPIO as GPIO
import time

#Pin Definitions:
ledOne = 18 #pin 18
ledTwo = 23 #pin 23
vcc = 17 #power pin

dc = 0 #Duty cycle (0-100)

#Pin setup
GPIO.setmode(GPIO.BCM)
GPIO.setup(ledOne, GPIO.OUT)
GPIO.setup(ledTwo, GPIO.OUT)

#Initial led states:
GPIO.output(ledOne, GPIO.LOW)
GPIO.output(ledTwo, GPIO.LOW)


L1 = 0 # The value of lightstatus one from database
L2 = 0 # The value of lightstatus one from database

try:
    while 1:
        if L1>5:
            GPIO.output(ledOne, GPIO.HIGH)
