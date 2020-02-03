//
//  EOPublicHeader.h
//  EOCore
//
// Copyright (C) 2019 Acoustic, L.P. All rights reserved.
//
// NOTICE: This file contains material that is confidential and proprietary to
// Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
// industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
// Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
// prohibited.
//

#define EOCORE_FRAMEWORK_VER @"2.2.6"

// Constants Config Items
#define kEOConfig_CriticalBatteryLevel @"CriticalBatteryLevel"
#define kEOConfig_PersistLocalCache @"HasToPersistLocalCache"
#define kEOConfig_PostMessageLevelCellular @"PostMessageLevelCellular"
#define kEOConfig_PostMessageLevelWiFi @"PostMessageLevelWiFi"
#define kEOConfig_DoPostAppComesFromBackground @"DoPostAppComesFromBackground"
#define kEOConfig_DoPostAppGoesToBackground @"DoPostAppGoesToBackground"
#define kEOConfig_DoPostAppGoesToClose @"DoPostAppGoesToClose"
#define kEOConfig_DoPostAppIsLaunched @"DoPostAppIsLaunched"
#define kEOConfig_DoPostOnScreenChange @"DoPostOnScreenChange"
#define kEOConfig_DynamicConfigurationEnabled @"DynamicConfigurationEnabled"
#define kEOConfig_CachedFileMaxBytesSize @"CachedFileMaxBytesSize"
#define kEOConfig_PostMessageMaxBytesSize @"PostMessageMaxBytesSize"
#define kEOConfig_MaxNumberOfFilesToCache @"MaxNumberOfFilesToCache"
#define kEOConfig_TurnOffCorrectOrientationUpdates @"TurnOffCorrectOrientationUpdates"
#define kEOConfig_DefaultOrientation @"DefaultOrientation"

#define kEOConfig_DoPostOnIntervals @"DoPostOnIntervals"
#define kEOConfig_MaxNumberOfPostsPerActivation @"MaxNumberOfPostsPerActivation"
#define kEOConfig_MaxNumberOfBytesPerActivation @"MaxNumberOfBytesPerActivation"
#define kEOConfig_PostMessageTimeInterval @"PostMessageTimeIntervals"
#define kEOConfig_ManualPostEnabled @"ManualPostEnabled"
#define kEOConfig_PostMessageTimeout @"PostMessageTimeout"
#define kEOConfig_PostMessageURL @"PostMessageUrl"
#define kEOConfig_CompressPostMessage @"CompressPostMessage"
#define kEOConfig_URLEncoded @"URLEncoded"
#define kEOConfig_CachingLevel @"CachingLevel"
#define kEOLoggingLevel @"LoggingLevel"
#define kEOConfig_LibraryVersion @"LibraryVersion"
#define kEOConfig_MessageVersion @"MessageVersion"
#define kEOConfig_QueryStringEncodeCharSet @"QueryStringEncodeCharSet"

//Keys for Environmental services and others consumed by TLFEnvironmentManager
#define keyEONetworkStatus @"keyOENetworkStatus"
#define keyEOConnectionType @"keyEOConnectionType"
#define keyEOBatteryLevel @"keyEOBatteryLevel"
#define keyEODeviceOrientation @"keyEODeviceOrientation"
#define keyTLFDeviceID @"keyTLFDeviceID"

//EOEnvironmentalService notification constants
#define kEOEnvironmentalServiceDidPostNetworkChangeNotification @"kEOEnvironmentalServiceDidPostNetworkChangeNotification"
#define kEOEnvironmentalServiceDidPostOrientationChangeNotification @"kEOEnvironmentalServiceDidPostOrientationChangeNotification"

//TLFApplicationHelper can set deviceID - this has to be synced
#define kTLFApplicationHelperDidPostDeviceIDChangeNotification @"kTLFApplicationHelperDidPostDeviceIDChangeNotification"

//EOBatteryManager notification constants
#define kEOBatteryManagerDidPostBatteryChangeNotification @"kEOBatteryManagerDidPostBatteryChangeNotification"



//Enums
typedef enum {
    kEOMonitoringLevelIgnore = 0,
    kEOMonitoringLevelCellularAndWiFi = 1,
    kEOMonitoringLevelWiFi = 2,
} kEOMonitoringLevel;

typedef NS_ENUM(NSUInteger, kEONetworkStatus) {
    //TODO: Do we need unknown ??
    //TODO: CRITICAL - this is different to Apple Reachability enums !!
    //IF USING -1, THEN TYPE CHANGES AND SO DOES SOME LITERAL STUFF IN EOEnvironmentalService
    kEONetworkStatusUnknown = 0,
    kEONetworkStatusNotReachable = 1,
    kEONetworkStatusReachableViaWiFi = 2,
    kEONetworkStatusReachableViaWWAN = 3,
    
};

typedef NS_ENUM(NSUInteger, kEOApplicationState) {
    
    kEOApplicationStateUnknown = 0,
    kEOApplicationStateActive = 1,
    kEOApplicationStateInactive = 2,
    kEOApplicationStateBackground = 3,
    
};
