//
//  EOMessage.h
//  EOCore
//
// Copyright (C) 2015 Acoustic, L.P. All rights reserved.
//
// NOTICE: This file contains material that is confidential and proprietary to
// Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
// industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
// Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
// prohibited.
//

#import <Foundation/Foundation.h>
#import "EOPublicHeader.h"

@interface EOMessage : NSObject

/*!
 * @abstract The offset in seconds from when the session starts to the time the message is posted.
 * @discussion EOCore will update this offset automatically when the message is posted to the queue.
 * @return The offset in seconds.
 */
@property (nonatomic) unsigned long long offset;

/*!
 * @abstract The logging level of the EOMessage.
 * @discussion The logging levels follow the Android Standards of:<br><li>Error = 0 <li>Warning = 1 <li>Verbose = 2 <li>Info = 3 <li>Debug = 4 <li>Ignore = 99
 * @return The logging level associated with the message.
 */
@property (nonatomic) kEOMonitoringLevel logLevel;

/*!
 * @abstract The JSON dictionary that represents the necessary key value pairs for the given message.
 * @discussion This dictionary should only contain Foundation objects that can be serialized using the JSON Serialization. This method will be called on every message to generate the JSON string to be posted to the target URL.
 * @return An NSMutableDictionary of Foundation Objects to be used.
 */
- (NSMutableDictionary *)jsonDictionary;

/*!
 * @abstract The JSON formatted string of the EOMessage to be sent to the target URL.
 * @discussion This is generated from jsonDictionary by default.
 * @return JSON formatted NSString
 */
- (NSString *)toJson;

-(void)encodeWithCoder: (NSCoder*) coder;
-(id)initWithCoder: (NSCoder*) coder;

@end
