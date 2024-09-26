//
// Copyright (C) 2024 Acoustic, L.P. All rights reserved.
//
// NOTICE: This file contains material that is confidential and proprietary to
// Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
// industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
// Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
// prohibited.
//

#ifndef Tealeaf_TLFEnums_h
#define Tealeaf_TLFEnums_h
typedef NS_ENUM(NSUInteger, TLFMessageType)
{
    TLFMessageTypeClientState = 1,
    TLFMessageTypeApplicationContext = 2,
    TLFMessageTypeConnection = 3,
    TLFMessageTypeControl = 4,
    TLFMessageTypeCustom = 5,
    TLFMessageTypeException = 6,
    TLFMessageTypePerformance = 7,
    TLFMessageTypeWebStorage = 8,
    TLFMessageTypeOverstatHover = 9,
    TLFMessageTypeScreenLayout = 10,
    TLFMessageTypeGesture = 11,
    TLFMessageTypeDOMCapture = 12,
    TLFMessageTypeLocation = 13,
    TLFMessageTypeFormCompletion = 15,
    TLFMessageTypeSignal = 21,
    TLFMessageTypeJSONString = 99,
    TLFMessageTypeScreenViewPlaceHolder = 100,
    TLFMessageTypeScreenLayoutPlaceHolder = 101,
    TLFMessageTypeKeyboardPlaceHolder = 102,
    TLFMessageTypeTextChangePlaceHolder = 103
};
typedef NS_ENUM(NSUInteger, kTLFApplicationDidFinishLaunchingType) {
    
    kTLFApplicationDidFinishLaunchingNormal = 0,
    kTLFApplicationDidFinishLaunchingURL = 1,
    kTLFApplicationDidFinishLaunchingSourceApplication = 2,
    kTLFApplicationDidFinishLaunchingRemoteNotification = 3,
    kTLFApplicationDidFinishLaunchingLocalNotification = 4,
    kTLFApplicationDidFinishLaunchingAnnotation = 5
    
};

typedef NS_ENUM(NSUInteger, kTLFDeviceModel) {
    
    kTLFDeviceModelUnknown = 0,
    kTLFDeviceModeliPad = 1,
    kTLFDeviceModeliPhone = 2,
    kTLFDeviceModeliPod = 3,
    
};

typedef NS_ENUM(NSUInteger, kTLFApplicationState) {
    
    kTLFApplicationStateUnknown = 0,
    kTLFApplicationStateActive = 1,
    kTLFApplicationStateInactive = 2,
    kTLFApplicationStateBackground = 3,
    
};

typedef NS_ENUM(NSUInteger, kTLFMaskingLevelType) {
    
    kTLFMaskingLevelNone = 0, // Leave the item value as is, no mask will be applied.
    kTLFMaskingLevelCustomMask1 = 1, // Apply custom mask to the item value.
    kTLFMaskingLevelCustomMask2 = 2, // Apply custom mask to the item value.
    kTLFMaskingLevelCustomMask3 = 3, // Apply custom mask to the item value.
    kTLFMaskingLevelCustomMask4 = 4, // Apply custom mask to the item value.
    
};

typedef NS_ENUM(NSUInteger, kTLFNewMaskingLevelType) {
    
    kTLF_MaskingLevelNone = 0, // Leave the item value as is, no mask will be applied.
    kTLFMaskingLevelStandardMask = 1, // Apply standard mask to the item value.
    kTLFMaskingLevelCustomMask = 2, // Apply custom mask to the item value.
};

//TODO: evaluate if this has to be converted into new format for enums
enum TLFLayout_IdType {
    TLFLayout_IdType_Manual=-1,
    TLFLayout_IdType_Dynamic =-3,
    TLFLayout_IdType_Xpath=-4
};

//TODO do we still use this?
typedef NS_ENUM(NSUInteger, kTLFItemType) {
    
    kTLFItemTypeNotSet                                     = 0  ,
    kTLFItemTypeTotalStorageCapacity                       = 6  ,
    kTLFItemTypeTotalDeviceMemory                          = 7  ,
    kTLFItemTypeBatteryLevel                               = 102,
    kTLFItemTypeDeviceOrientation                          = 104,
    kTLFItemTypeLocalIPAddress                             = 110,
    kTLFItemTypeNetworkStatus                              = 113,
    kTLFItemTypeApplicationDidFinishLaunching              = 201,
    kTLFItemTypeApplicationWillTerminate                   = 202,
    kTLFItemTypeApplicationDidBecomeActive                 = 203,
    kTLFItemTypeApplicationWillResignActive                = 204,
    kTLFItemTypeApplicationDidEnterBackground              = 205,
    kTLFItemTypeApplicationWillEnterForeground             = 206,
    kTLFItemTypeApplicationDidReceiveMemoryWarning         = 207,
    kTLFItemTypeApplicationWillChangeStatusBarOrientation  = 208,
    kTLFItemTypeApplicationDidChangeStatusBarOrientation   = 209,
    kTLFItemTypeApplicationWillChangeStatusBarFrame        = 210,
    kTLFItemTypeApplicationDidChangeStatusBarFrame         = 211,
    kTLFItemTypeKeyboardDidShow                            = 213,
    kTLFItemTypeKeyboardDidHide                            = 215,
    kTLFItemTypeTableViewSelectionDidChange                = 216,
    kTLFItemTypeTextFieldTextDidBeginEditing               = 217,
    kTLFItemTypeTextFieldTextDidEndEditing                 = 219,
    kTLFItemTypeTextViewTextDidBeginEditing                = 220,
    kTLFItemTypeTextViewTextDidEndEditing                  = 222,
    kTLFItemTypeAlertViewShown                             = 223,
    kTLFItemTypeViewControllerViewDidAppear                = 229,
    kTLFItemTypeViewControllerViewWillDisappear            = 230,
    kTLFItemTypeSyncConnection                             = 235,
    kTLFItemTypeSyncConnectionWithError                    = 236,
    kTLFItemTypeAsyncConnectionInit                        = 237,
    kTLFItemTypeAsyncConnectionDidReceiveResponse          = 238,
    kTLFItemTypeAsyncConnectionDidFinishWithError          = 240,
    kTLFItemTypeConnectivityStateChanged                   = 242,
    kTLFItemTypeMoviePlayerLoadState					   = 248,
    kTLFItemTypeMoviePlayerPlaybackState				   = 249,
    kTLFItemTypeTextFieldSecureTextEntryDidBeginEditing    = 258,
    kTLFItemTypeTextFieldSecureTextEntryDidEndEditing	   = 259,
    kTLFItemTypeTextViewSecureTextEntryDidBeginEditing     = 260,
    kTLFItemTypeTextViewSecureTextEntryDidEndEditing       = 262,
    kTLFItemTypePopoverShouldDismiss					   = 270,
    kTLFItemTypePopoverDidDismiss						   = 271,
    kTLFItemTypeAlertViewButtonClicked                     = 275,
    kTLFItemTypeActionSheetButtonClicked                   = 276,
    kTLFItemTypeApplicationDidReceiveLocalNotification	   = 277,
    kTLFItemTypeApplicationDidReceiveRemoteNotification	   = 278,
    kTLFItemTypeUserEvent                                  = 301,
    kTLFItemTypePrintScreen                                = 401,
    
    
};




#endif
