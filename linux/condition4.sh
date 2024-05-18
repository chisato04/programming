#!/bin/bash

# This demonstrates the use of the switch (case) statement

read -p "Enter a value between one and five (1 - 5): " intValue

case ${intValue} in 
   1) echo "Crewzer one's pilot is Steve (Kenichi) Armstrong.";;
   2) echo "Bomber two's pilot is Mark Gordon (Ippei)..";;
   3) echo "Panzer three's pilot is Big Bert..";;
   4) echo "Frigate four's pilot is Little Jon (Hiroshi)..";;
   5) echo "Lander five's pilot is Jamie Robinson (Megumi Oka)..";;
   *) echo "You are a Bozanian spy!";;
esac
