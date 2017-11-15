//
//  FontExplorer.m
//  TL Connect
//
//  Created by Geoff Heeren on 4/23/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "FontExplorer.h"


@implementation FontExplorer


+(NSString*)getAllFonts
{
	NSMutableString *fontString=[NSMutableString string];
	NSArray *families=[[UIFont familyNames] sortedArrayUsingSelector:@selector(localizedCaseInsensitiveCompare:)];
	for (NSString* family in families)
	{
		NSArray *names=[[UIFont fontNamesForFamilyName:family] sortedArrayUsingSelector:@selector(localizedCaseInsensitiveCompare:)];
		for (NSString* name in names)
		{
			[fontString appendFormat:@"%@,%@\r",family,[self getFontWeight:name]];
		}
	}
	return fontString;
}
+(NSString*)getFontTestPage
{
	NSMutableString *fontString=[NSMutableString stringWithString:@"<html><head>,<title>Font Test</title></head><body>\r"];
	NSArray *families=[[UIFont familyNames] sortedArrayUsingSelector:@selector(localizedCaseInsensitiveCompare:)];
	for (NSString* family in families)
	{
		NSArray *names=[[UIFont fontNamesForFamilyName:family] sortedArrayUsingSelector:@selector(localizedCaseInsensitiveCompare:)];
		for (NSString* name in names)
		{
			NSString *fontWeight=[self getFontWeight:name];
			NSString *fontStyle=[self getFontStyle:name];
			[fontString appendFormat:@"<div style=\"font-weight:%@;font-style:%@;font-size:17px;font-family:'%@';\">%@, %@, %@ (%@)</div>\r",fontWeight,fontStyle,family,family,fontStyle,fontWeight, name];
		}
	}
	[fontString appendString:@"</body></html>\r"];
	return fontString;
}
+(NSString*)getFontStyle:(NSString*)name
{
	if ([name rangeOfString:@"Italic" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"italic";
	else if ([name rangeOfString:@"Oblique" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"oblique";
	else
		return @"normal";
}
+(NSString*)getFontWeight:(NSString*)name
{
	if ([name rangeOfString:@"Black" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"900";
	else if ([name rangeOfString:@"Heavy" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"800";
	else if ([name rangeOfString:@"DemiBold" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"600";
	else if ([name rangeOfString:@"SemiBold" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"600";
	else if ([name rangeOfString:@"Bold" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"700";
	else if ([name rangeOfString:@"Medium" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"500";
	else if ([name rangeOfString:@"Normal" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"400";
	else if ([name rangeOfString:@"Book" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"400";
	else if ([name rangeOfString:@"Roman" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"400";
	else if ([name rangeOfString:@"UltraLight" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"200";
	else if ([name rangeOfString:@"Light" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"300";
	else if ([name rangeOfString:@"Thin" options:NSCaseInsensitiveSearch].location!=NSNotFound)
		return @"100";
	else
		return @"400";
}

@end
