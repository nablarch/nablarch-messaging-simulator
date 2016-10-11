@echo off

java -Drequest-id="RM11AC0203" -cp lib\\*;src\\main\\resources;messaging-simu.jar nablarch.fw.launcher.Main -diConfig incoming-mom-simulator-component-configuration.xml -requestPath xxx -userId xxx

pause