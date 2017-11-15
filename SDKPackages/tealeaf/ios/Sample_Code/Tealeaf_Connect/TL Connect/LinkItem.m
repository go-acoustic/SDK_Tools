//
//  LinkItem.m
//  TL Connect
//
//  Created by Geoff Heeren on 1/17/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "LinkItem.h"

@implementation LinkItem
-(id)initWithLinkName:(NSString*)name andUrl:(NSString*)url
{
	self=[super init];
	if (self)
	{
		self.linkName=name;
		self.link=[NSURL URLWithString:url];
	}
	return self;
}
@end
