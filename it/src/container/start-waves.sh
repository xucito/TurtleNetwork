#!/bin/bash
trap 'kill -TERM $PID' TERM INT
echo Options: $TN_OPTS
java $TN_OPTS -jar /opt/TN/TN.jar /opt/TN/template.conf &
PID=$!
wait $PID
trap - TERM INT
wait $PID
EXIT_STATUS=$?
>>>>>>> d6fc9c1852d38efd766c0e4c855b24458edcc1a2
