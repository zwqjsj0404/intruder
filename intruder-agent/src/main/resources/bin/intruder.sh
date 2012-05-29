#!/bin/sh
echo "Usage: intruder.sh <confPath> <pid>"
echo
INTRUDER_HOME_1=`dirname "$0"`/..
INTRUDER_HOME=`cd ${INTRUDER_HOME_1} && pwd`
CLIENT=${INTRUDER_HOME}/intruder-agent-0.1.jar
TOOLS_JAR="${JAVA_HOME}/lib/tools.jar"
${JAVA_HOME}/bin/java -cp ${TOOLS_JAR}:${CLIENT} com.alibaba.intruder.agent.core.Client ${CLIENT} $1 $2
