#!/bin/bash

# This script demonstrates the use of the if-else statement

strUser=$(whoami)

if [ ${strUser} != 'root' ];
then
	echo "You are logged in as ${strUser}.."
else
	echo "You are logged in as administrator."
	echo "You can log in as another user besides root later."
fi
