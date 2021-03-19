#--------------------------------------------------------------------------------------------
# Copyright (C) 2021 Acoustic, L.P. All rights reserved.
#
# NOTICE: This file contains material that is confidential and proprietary to
# Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
# industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
# Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
# prohibited.
#--------------------------------------------------------------------------------------------

There are five folders created for the Tealeaf Android release.

AndroidImageCaptureTool folder - Tool used to collect all embedded images for Native Replay.
EOCore folder                  - Contains base jar and aar library needed for Tealeaf and Digital Analytics to communicate to server.
TealeafMod folder              - Contains jar and aar library that will collect Tealeaf data that passed to EOCore.
TeaCuts folder                 - Contains jar library that will help auto instrument Tealeaf into an Android application.
TealeafInstaller folder        - It has the ruby rake installer for Android Studio projects. It will add library to Android
                                 application in seconds with base defaults.

Hosted at https://github.com/acoustic-analytics
SampleCode folder              - Has several sample applications which demonstrates how to integrate and use the library.
                                 It can be found at https://github.com/acoustic-analytics. 
KillSwitch folder              - Has a basic kill switch implementation for JSP, ASP .Net and PHP servers.
                                 It can be found at https://github.com/acoustic-analytics/SDK_Tools/tree/master/KillSwitch. 
TealeafTargetSimulator folder  - Tool used to convert sessions into images which are used for Native Replay.
                                 It can be found at https://github.com/acoustic-analytics/SDK_Tools/tree/master/TealeafTargetSimulator
Templates folder               - It has the latest native templates compatible with libray.
                                 It can be found at https://github.com/acoustic-analytics/SDK_Tools/tree/master/NativeTemplates

Libraries are compatible from 4.1.x (16) to 8.0 (26).

Documentation links:

https://developer.goacoustic.com/acoustic-exp-analytics/docs
