#!/bin/sh
INTRUDER_HOME=`dirname "$0"`/..
INTRUDER_HOME=`cd ${INTRUDER_HOME} && pwd`
java -cp ${INTRUDER_HOME}/intruder-agent-0.1.jar sample.test.InstrumentTest