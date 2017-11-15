//
//  LinkItem.h
//  TL Connect
//
//  Created by Geoff Heeren on 1/17/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import <Foundation/Foundation.h>

@interface LinkItem : NSObject
@property (nonatomic) NSString *linkName;
@property (nonatomic) NSURL *link;
-(id)initWithLinkName:(NSString*)name andUrl:(NSString*)url;
@end
