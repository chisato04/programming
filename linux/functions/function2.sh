#!/bin/bash

function fnDisplay(){
	tstrMessage=$1
	echo ${tstrMessage}
	echo "fnDisplay() function being called.."
}

function fnShowFullName(){
	tstrLast=$1
	tstrFirst=$2
	tstrMiddle=$3

	echo "Name: ${tstrFirst} ${tstrMiddle} ${tstrLast}"
	echo "fnShowFullName() function being called.."
}

fnDisplay Konnichiwa!

fnShowFullName MALINAO JAMES MONSALUD
