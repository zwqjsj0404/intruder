#! /bin/sh
echo "Usage: intruder.sh <confPath> <pid>"
echo 
INTRUDER_HOME=`dirname "$0"`/..
INTRUDER_HOME=`cd "${INTRUDER_HOME}" && pwd`
CLIENT=${INTRUDER_HOME}/intruder-agent-0.1.jar
java -cp ${CLIENT} com.alibaba.intruder.agent.core.Client ${CLIENT} $1 $2
