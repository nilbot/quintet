#!/usr/bin/env bash
#Again, I know my environment; so whatev;
kill -9 $(ps aux | grep '[b]ash run' | awk '{print $2}')
./gradlew clean jar
bash run.sh &>run.log &
disown
