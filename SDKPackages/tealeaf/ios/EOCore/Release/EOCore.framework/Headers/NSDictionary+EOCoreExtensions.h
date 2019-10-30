//
//  NSDictionary+EOCoreExtensions.h
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

@interface NSDictionary (EOCoreExtensions)

/*!
 * @abstract Converts and NSDictionary and its subclasses to a JSON formatted NSString.
 * @discussion The JSON serialization will fail and return an empty string if any non-Foundation Object is encountered.
 * @return A string in JSON syntax that represents the NSDictionary.
 */
- (NSString *)toJson;

/*!
 * @abstract Converts the NSDictionary into a string of URL encoded parameters.
 * @discussion This will convert a dictionary to a URL percent escaped string of parameters with their associated values. Note Booleans are represented as 0 and 1
 * @return Percent escaped url encoded parameter string ready to be added to a URL GET request.
 */
- (NSString *)urlEncodedString;

/*!
 * @abstract Converts the NSDictionary into an array of NSURLQueryItems.
 * @discussion This will convert a dictionary to a NSURLQueryItem.
 * @return Array ready to be added to a  GET request NSURL's NSURLComponent.
 */
-(NSArray<NSURLQueryItem *>*)daQueryItemsForCharset:(NSCharacterSet*)charset;
@end
