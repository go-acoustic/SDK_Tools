###############################################################################
# Licensed Materials - Property of IBM
# (C) Copyright IBM Corp. 2017
# US Government Users Restricted Rights - Use, duplication or disclosure
# restricted by GSA ADP Schedule Contract with IBM Corp.
###############################################################################

There are six folders created for the Tealeaf iOS release.
  
EOCore folder - Contains Debug and Release of base library needed for Tealeaf and Digital Analytics to communicate to server.
Tealeaf folder - Contains Debug and Release of library that will collect Tealeaf data that passed to EOCore.
Sample_Code folder - Has a sample application which demonstrates how to integrate and use the library with a webview.
KillSwitch folder - Has a basic kill switch implementation for JSP, ASP .Net and PHP servers.
TealeafTargetSimulator folder - Tool used to convert sessions into images which are used for Native Replay.
Xamarin folder - Xamarin library that is used for basic iOS implementation of Tealeaf. Note: This was created with older 10.1.x library.
TLFImageTool folder - TLFImageTool extracts all the local image resources and generates .jpg assets for replay server to use with Native Replay.

Libraries are compatible from 8.0 to 10 using simulator or device on 32 bit or 64 bit with bitcode enabled on release package.

Documentation links:

UIC: https://www.ibm.com/support/knowledgecenter/CX_UIC_UG_5.0.0/UIC/UICj2Guide_kc_container.dita?cp=SS2MBL_9.0.2%2F5-3&lang=en
iOS SDK: https://www.ibm.com/support/knowledgecenter/TLSDK/CFs/TLSDK_kc_container.html?cp=SSES6Y
Android SDK: https://www.ibm.com/support/knowledgecenter/TLSDK/CFs/TLSDK_kc_container.html?cp=SSES6Y