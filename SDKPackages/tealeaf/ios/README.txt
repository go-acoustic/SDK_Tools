/********************************************************************************************
# Copyright (C) 2021 Acoustic, L.P. All rights reserved.
# 
# NOTICE: This file contains material that is confidential and proprietary to
# Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
# industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
# Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
# prohibited.
********************************************************************************************/

There are three folders created for the Tealeaf iOS release.
  
EOCore folder - Contains Debug and Release of base library needed for Tealeaf and Digital Analytics to communicate to server.
Tealeaf folder - Contains Debug and Release of library that will collect Tealeaf data that passed to EOCore.
TLFImageTool folder - TLFImageTool extracts all the local image resources and generates .png assets for replay server to use with Native Replay.

Hosted at https://github.com/acoustic-analytics
SampleCode folder              - Has several sample applications which demonstrates how to integrate and use the library.
                                 It can be found at https://github.com/acoustic-analytics. 
KillSwitch folder              - Has a basic kill switch implementation for JSP, ASP .Net and PHP servers.
                                 It can be found at https://github.com/acoustic-analytics/SDK_Tools/tree/master/KillSwitch. 
TealeafTargetSimulator folder  - Tool used to convert sessions into images which are used for Native Replay.
                                 It can be found at https://github.com/acoustic-analytics/SDK_Tools/tree/master/TealeafTargetSimulator
Templates folder               - It has the latest native templates compatible with libray.
                                 It can be found at https://github.com/acoustic-analytics/SDK_Tools/tree/master/NativeTemplates

Libraries are compatible from 9.0 to 14.3 using simulator or device on 32 bit or 64 bit with bitcode enabled on release package.

Documentation links:

https://developer.goacoustic.com/acoustic-exp-analytics/docs
