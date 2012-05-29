#! /bin/sh
echo "Usage: intruder.sh <confPath> <pid>"
echo 
INTRUDER_HOME=`dirname "$0"`/..
INTRUDER_HOME=`cd "${INTRUDER_HOME}" && pwd`
CLIENT=${INTRUDER_HOME}/intruder-agent-0.1.jar
TOOLS_JAR="${JAVA_HOME}/lib/tools.jar"
${JAVA_HOME}/bin/java -cp ${CLIENT}:${TOOLS_JAR} com.alibaba.intruder.agent.core.Client ${CLIENT} $1 $2