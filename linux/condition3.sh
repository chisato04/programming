#!/bin/bash

# This script demonstrates if-else

read -p "Enter an odd number (1, 3, 5, 7, 9): " intNumber

if [ ${intNumber} -eq 1 ];
then
   echo "Value is one.."
elif [ ${intNumber} -eq 3 ];
   then
      echo "Value is three.."
elif [ ${intNumber} -eq 5 ];
   then
      echo "Value is five.."
elif [ ${intNumber} -eq 7 ];
   then
      echo "Value is seven.."
else
   echo "The value that you entered is ${intNumber}.."
fi

