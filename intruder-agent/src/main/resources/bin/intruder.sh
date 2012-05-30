#!/bin/sh

## Description:
## Usage: 	sh intruder.sh <pid>
## Author: 	zili.dengzl long.caol
## Date: 	2012.05.30
##

echo "Usage: intruder.sh <confPath> <pid>"
echo
INTRUDER_HOME=`dirname "$0"`/..
INTRUDER_HOME=`cd ${INTRUDER_HOME} && pwd`
CLIENT=${INTRUDER_HOME}/intruder-agent-0.1.jar

case "`uname`" in
	Darwin*)
		TOOLS_JAR="${JAVA_HOME}/../Classes/classes.jar"
	;;
	*)
		TOOLS_JAR="${JAVA_HOME}/lib/tools.jar"
	;;
esac

${JAVA_HOME}/bin/java -cp ${TOOLS_JAR}:${CLIENT} com.alibaba.intruder.agent.core.Client ${CLIENT} ${INTRUDER_HOME}/conf/agent.properties $1

