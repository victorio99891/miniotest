#!/bin/bash

i=0;

while true;
do

 echo 'Test' > Test$i.txt;
 
 ((i=i+1))
 
 echo "i=$i";	


done
