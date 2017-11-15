//
//  Licensed Materials - Property of IBM
//  (C) Copyright IBM Corp. 2016
//  US Government Users Restricted Rights - Use, duplication or disclosure
//  restricted by GSA ADP Schedule Contract with IBM Corp.
//

#define WEBEXAMPLE_SOMESESSIONID @"MyFixedSessionId"
/* #define WEBEXAMPLE_SOMESESSIONID nil */

#import "WebExampleSessionIdGenerator.h"

@implementation WebExampleSessionIdGenerator
- (NSString*)sessionIdGeneration
{
    NSString *uuid = [[NSUUID UUID] UUIDString];
    return [uuid stringByReplacingOccurrencesOfString:@"-" withString:@""];
}
@end
