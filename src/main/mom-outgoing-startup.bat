@echo off

java -Drequests-to-send="mom-RequestsToSend-103.csv" -cp lib\\*;src\\main\\resources;messaging-simu.jar nablarch.fw.launcher.Main -diConfig outgoing-simulator-component-configuration.xml -requestPath xxx -userId xxx

pause