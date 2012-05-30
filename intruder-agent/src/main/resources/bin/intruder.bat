set INTRUDER_HOME=%~dp0..
cd %INTRUDER_HOME%
java -cp "%JAVA_HOME%/lib/tools.jar;%INTRUDER_HOME%/intruder-agent-0.1.jar" com.alibaba.intruder.agent.core.Client %INTRUDER_HOME%/intruder-agent-0.1.jar %INTRUDER_HOME%/conf/agent.properties %1
