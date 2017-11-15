###############################################################################
# Licensed Materials - Property of IBM
# (C) Copyright IBM Corp. 2017
# US Government Users Restricted Rights - Use, duplication or disclosure
# restricted by GSA ADP Schedule Contract with IBM Corp.
###############################################################################

There are eight folders created for the Tealeaf Android release.
  
EOCore folder - Contains base jar library needed for Tealeaf and Digital Analytics to communicate to server.
TealeafMod folder - Contains jar library that will collect Tealeaf data that passed to EOCore.
TeaCuts folder - Contains jar library that will help auto instrument Tealeaf into an Android application.
SampleCode folder - Has several sample applications which demonstrates how to integrate and use the library. 
KillSwitch folder - Has a basic kill switch implementation for JSP, ASP .Net and PHP servers.
AndroidImageCaptureTool folder - Tool used to collect all embedded images for Native Replay.
TealeafTargetSimulator folder - Tool used to convert sessions into images which are used for Native Replay.
AndroidEclipsePlugin folder - Eclipse plug-in to help inject library into a new application.
Templates folder - It has the latest native templates compatible with libray.
TealeafInstaller folder - It has the ruby rake installer for Android Studio projects. It will add library to Android application in seconds with base defaults.

Libraries are compatible from 4.1.x (16) to 7.1 (25).

Documentation links:

UIC: https://www.ibm.com/support/knowledgecenter/CX_UIC_UG_5.0.0/UIC/UICj2Guide_kc_container.dita?cp=SS2MBL_9.0.2%2F5-3&lang=en
iOS SDK: https://www.ibm.com/support/knowledgecenter/TLSDK/CFs/TLSDK_kc_container.html?cp=SSES6Y
Android SDK: https://www.ibm.com/support/knowledgecenter/TLSDK/CFs/TLSDK_kc_container.html?cp=SSES6Y
