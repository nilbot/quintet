#!/usr/bin/env bash
#Again, I know my environment; so whatev;
kill -9 $(ps aux | grep '[b]ash cron' | awk '{print $2}')
./gradlew clean jar
while true
do
	java -jar build/libs/quintet-1.2.0.jar
	sleep 60
done
