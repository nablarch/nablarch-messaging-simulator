@echo off

java -Drequests-to-send="http-RequestsToSend-102.csv" -cp lib\\*;src\\main\\resources;messaging-simu.jar nablarch.fw.launcher.Main -diConfig outgoing-http-simulator-component-configuration.xml -requestPath xxx -userId xxx -sendCount 1

pause