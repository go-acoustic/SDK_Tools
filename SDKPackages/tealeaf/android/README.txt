###############################################################################
# Licensed Materials - Property of IBM
# (C) Copyright IBM Corp. 2017
# US Government Users Restricted Rights - Use, duplication or disclosure
# restricted by GSA ADP Schedule Contract with IBM Corp.
###############################################################################

There are five folders created for the Tealeaf Android release.

AndroidImageCaptureTool folder - Tool used to collect all embedded images for Native Replay.
EOCore folder                  - Contains base jar and aar library needed for Tealeaf and Digital Analytics to communicate to server.
TealeafMod folder              - Contains jar and aar library that will collect Tealeaf data that passed to EOCore.
TeaCuts folder                 - Contains jar library that will help auto instrument Tealeaf into an Android application.
TealeafInstaller folder        - It has the ruby rake installer for Android Studio projects. It will add library to Android
                                 application in seconds with base defaults.

Hosted at https://github.com/ibm-watson-cxa
SampleCode folder              - Has several sample applications which demonstrates how to integrate and use the library.
                                 It can be found at https://github.com/ibm-watson-cxa. 
KillSwitch folder              - Has a basic kill switch implementation for JSP, ASP .Net and PHP servers.
                                 It can be found at https://github.com/ibm-watson-cxa/SDK_Tools/tree/master/KillSwitch. 
TealeafTargetSimulator folder  - Tool used to convert sessions into images which are used for Native Replay.
                                 It can be found at https://github.com/ibm-watson-cxa/SDK_Tools/tree/master/TealeafTargetSimulator
Templates folder               - It has the latest native templates compatible with libray.
                                 It can be found at https://github.com/ibm-watson-cxa/SDK_Tools/tree/master/NativeTemplates

Libraries are compatible from 4.1.x (16) to 9.0 (28).

Documentation links:

UIC: https://developer.ibm.com/customer-engagement/docs/watson-marketing/ibm-watson-customer-experience-analytics/tealeaf-ui-capture/
iOS SDK: https://developer.ibm.com/customer-engagement/docs/watson-marketing/ibm-watson-customer-experience-analytics/ibm-watson-customer-experience-analytics-mobile-basic-edition/
Android SDK: https://developer.ibm.com/customer-engagement/docs/watson-marketing/ibm-watson-customer-experience-analytics/ibm-watson-customer-experience-analytics-mobile-android-basic-edition/
