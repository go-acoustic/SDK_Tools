//
//  Product.m
//  TL Connect
//
//  Created by Geoff Heeren on 1/14/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "Product.h"
#import "LinkItem.h"
@implementation Product
-(id)initWithName:(NSString*)name andBlurb:(NSString*)blurb andItems:(NSArray*)items
{
	self=[super init];
	if(self)
	{
		self.name=[name copy];
		self.blurb=[blurb copy];
		self.relatedItems=[NSArray arrayWithArray:items];
	}
	return  self;
}
-(id)initWithDictionary:(NSDictionary*)dict
{
	self=[super init];
	if(self)
	{
		self.prodCode=dict[@"Product"];
		self.name=dict[@"Name"];
		self.subtitle=dict[@"Desc"];
		self.imageName=dict[@"ImageName"];
		NSError *err;
		NSString* filePath = [[NSBundle mainBundle] pathForResource:[NSString stringWithFormat:@"%@",self.prodCode]  ofType:@"txt"];
		NSLog(@"%@",filePath);
		self.blurb=[NSString stringWithContentsOfFile:filePath encoding:NSUTF8StringEncoding error:&err];
		if (err!=nil)
			NSLog(@"Blurb problem:%@",[err localizedDescription]);
		self.relatedItems=@[[[LinkItem alloc] initWithLinkName:dict[@"Link1Desc"] andUrl:dict[@"Link1"]],[[LinkItem alloc] initWithLinkName:dict[@"Link2Desc"] andUrl:dict[@"Link2"]]];
	}
	return self;
}
@end
