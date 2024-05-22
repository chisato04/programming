#!/bin/bash

intCounter=15

until [[ intCounter -lt 1 ]]
	do
		echo ${intCounter}
		(( intCounter-- ))
done
