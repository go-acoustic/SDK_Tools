###############################################################################
# Licensed Materials - Property of IBM
# (C) Copyright IBM Corp. 2017
# US Government Users Restricted Rights - Use, duplication or disclosure
# restricted by GSA ADP Schedule Contract with IBM Corp.
###############################################################################

There are six folders created for the Tealeaf iOS release.
  
EOCore folder - Contains Debug and Release of base library needed for Tealeaf and Digital Analytics to communicate to server.
Tealeaf folder - Contains Debug and Release of library that will collect Tealeaf data that passed to EOCore.
TLFImageTool folder - TLFImageTool extracts all the local image resources and generates .png assets for replay server to use with Native Replay.

Hosted at https://github.com/ibm-watson-cxa
SampleCode folder              - Has several sample applications which demonstrates how to integrate and use the library.
                                 It can be found at https://github.com/ibm-watson-cxa. 
KillSwitch folder              - Has a basic kill switch implementation for JSP, ASP .Net and PHP servers.
                                 It can be found at https://github.com/ibm-watson-cxa/SDK_Tools/tree/master/KillSwitch. 
TealeafTargetSimulator folder  - Tool used to convert sessions into images which are used for Native Replay.
                                 It can be found at https://github.com/ibm-watson-cxa/SDK_Tools/tree/master/TealeafTargetSimulator
Templates folder               - It has the latest native templates compatible with libray.
                                 It can be found at https://github.com/ibm-watson-cxa/SDK_Tools/tree/master/NativeTemplates

Libraries are compatible from 8.0 to 11.1 using simulator or device on 32 bit or 64 bit with bitcode enabled on release package.

Documentation links:

UIC: https://developer.ibm.com/customer-engagement/docs/watson-marketing/ibm-watson-customer-experience-analytics/tealeaf-ui-capture/
iOS SDK: https://developer.ibm.com/customer-engagement/docs/watson-marketing/ibm-watson-customer-experience-analytics/ibm-watson-customer-experience-analytics-mobile-basic-edition/
Android SDK: https://developer.ibm.com/customer-engagement/docs/watson-marketing/ibm-watson-customer-experience-analytics/ibm-watson-customer-experience-analytics-mobile-android-basic-edition/