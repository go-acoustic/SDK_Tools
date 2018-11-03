//
//  NSDictionary+EOCoreExtensions.h
//  EOCore
//
//  Licensed Materials - Property of IBM
//  (C) Copyright IBM Corp. 2015
//  US Government Users Restricted Rights - Use, duplication or disclosure
//  restricted by GSA ADP Schedule Contract with IBM Corp.
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

@end
