//
// Copyright (C) 2015 Acoustic, L.P. All rights reserved.
//
// NOTICE: This file contains material that is confidential and proprietary to
// Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
// industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
// Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
// prohibited.
//

#import <EOCore/EOCore.h>

//TODO: Move this to EOCORE ???
#if TEALEAF_USE_REACT
#import <TealeafReactNative/TLFPublicDefinitions.h>
#else
#import <Tealeaf/TLFPublicDefinitions.h>
#endif

@interface TLFMessage : EOMessage

@property (nonatomic) int type;
@property (nonatomic) BOOL fromWeb;
@property (nonatomic) int count;
@property (nonatomic) int screenviewOffset;

- (void)setLogLevel:(kTLFMonitoringLevelType)logLevel;
@end
