//
//  TLFLevelMonitor.h
//  Tealeaf
//
//
// Copyright (C) 2023 Acoustic, L.P. All rights reserved.
//
// NOTICE: This file contains material that is confidential and proprietary to
// Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
// industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
// Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
// prohibited.
//

#import <Foundation/Foundation.h>
#import "TLFPublicDefinitions.h"

@interface TLFLevelManager : NSObject
+(instancetype)sharedInstance;
-(BOOL)isPrintScreenEnabled;

-(kTLFMonitoringLevelType)logLevelForMobileState;
-(kTLFMonitoringLevelType)logLevelForOrientationChange;
-(kTLFMonitoringLevelType)logLevelForExceptionEvent;
-(kTLFMonitoringLevelType)logLevelForEvent:(NSString *)eventName;
-(kTLFMonitoringLevelType)logLevelForCustomEvent;
-(kTLFMonitoringLevelType)logLevelForLayout;
-(kTLFMonitoringLevelType)logLevelForGestures;
-(kTLFMonitoringLevelType)logLevelForPerformance;
-(kTLFMonitoringLevelType)logLevelForLocationEvent;
-(kTLFMonitoringLevelType)logLevelForScreenChangeEvent;
-(kTLFMonitoringLevelType)logULevelForICPayload;
-(kTLFMonitoringLevelType)logLevelForAsyncConnectionInit;
-(kTLFMonitoringLevelType)logLevelForSyncConnection;
-(kTLFMonitoringLevelType)logLevelForSyncConnectionError;
-(kTLFMonitoringLevelType)logLevelForAsyncConnectionDidReceiveResponse;
-(kTLFMonitoringLevelType)logLevelForAsyncConnectionDidFinishWithError;
-(kTLFMonitoringLevelType)logLevelForAutolog:(NSString*)autolog;
@end
