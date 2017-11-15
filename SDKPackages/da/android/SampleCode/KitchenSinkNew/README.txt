###############################################################################
# Licensed Materials - Property of IBM
# (C) Copyright IBM Corp. 2015
# US Government Users Restricted Rights - Use, duplication or disclosure
# restricted by GSA ADP Schedule Contract with IBM Corp.
###############################################################################

There are two folders created for the Digital Analytics Android release.
  
DigitalAnalytics folder - Contains all the artifacts required for your Android app. 
SampleCode folder - Have a sample app which demonstrates how to integrate and use
the library. 

1) To use DigitalAnalytics Android SDK in your app

    a)  Copy below files under DigitalAnalytics folder to your project libs/ folder
            DigitalAnalyticsMod.jar 
            EOCore.jar

    b)  Copy below files to assets/ folder
            DigitalAnalyticsAdvancedConfig.json         
            EOCoreAdvancedConfig.json
            DigitalAnalyticsBasicConfig.properties
            EOCoreBasicConfig.properties

2) To try a sample app with the artifacts configured 

    a) Import below projects under SampleCode folder
        KitchenSinkNew          
        android-support-v7-appcompat
        
    b) Run KitchenSinkNew app

3) To try another sample app with artifacts configured

    a) Import below projects under SampleCode folder
        Aurora          
        android-support-v7-appcompat
        
    b) Run Aurora app
