//
//  TLConnectHelper.m
//  TL Connect
//
//  Created by Geoff Heeren on 1/14/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "TLConnectHelper.h"
#import "Product.h"

float const ROW_HEIGHT=46.0;
const NSString* kRV_Key_Label=@"LABEL";
const NSString* kRV_Key_Value=@"VALUE";
const NSString* kRV_Key_List=@"LIST";
const NSString* kRV_Key_Selected=@"SELECTED";

@implementation TLConnectHelper
+(NSString*)contactPhone{
	return @"866-277-7488 ";
}
+(NSString*)contactEmail{
	return @"email@marketing.com";
}
+(NSString*)getSocialUrl:(SocialFeed)feed
{
	switch (feed) {
		case SocialFeed_Facebook:
			return @"https://www.facebook.com/pages/Tealeaf/52508527390";
			break;
		case SocialFeed_LinkedIn:
			return @"http://www.linkedin.com/company/tealeaf-technology";
			break;
		case SocialFeed_Twitter:
			return @"https://twitter.com/tealeaf";
			break;
			
		default:
			return @"http://www-01.ibm.com/software/info/tealeaf/";
			break;
	}
	
}
+(NSString*)getSocialAppUrl:(SocialFeed)feed
{
	switch (feed) {
		case SocialFeed_Facebook:
			return @"fb://profile/52508527390";
			break;
		case SocialFeed_LinkedIn:
			return @"linkedin://company/16447";
			break;
		case SocialFeed_Twitter:
			return @"twitter://tealeaf";
			break;
			
		default:
			return @"http://www-01.ibm.com/software/info/tealeaf/";
			break;
	}
	
}
+(NSArray*)getProducts{
	NSArray* array = [self jsonArrayFromFileName:@"Products" andExt:@"json"];
	NSMutableArray *outArray=[NSMutableArray array];
	for (NSDictionary *dict in array)
	{
		[outArray addObject:[[Product alloc] initWithDictionary:dict]];
	}
	return outArray;
}
+(Product*)getProductFor:(TLProduct)productType{
	NSArray *prods=[self getProducts];
	switch (productType) {
		case TLProduct_CX:
			return prods[0];
			break;
		case TLProduct_Mobile:
			return prods[1];
			break;
		case TLProduct_Impact:
			return prods[2];
			break;
		case TLProduct_Overstat:
			return prods[3];
			break;
		case TLProduct_Reveal:
			return prods[4];
			break;
		default:
			return nil;
			break;
	}
}
+(CLLocationCoordinate2D)getTLOfficeCoord{
	return CLLocationCoordinate2DMake(37.7888565, -122.4002838);
}
+(void)tagTableCell:(UITableViewCell*)cell withIndexPath:(NSIndexPath*)path{
	cell.tag=path.row*1000;
	cell.textLabel.tag=cell.tag+1;
	cell.detailTextLabel.tag=cell.tag+2;
	cell.imageView.tag=cell.tag+3;
}
+(void)dontClickThat
{
	UIAlertView *alert=[[UIAlertView alloc] initWithTitle:@"Don't do that" message:@"Feature is not implemented" delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles: nil];
	[alert show];
}
+(NSArray*)jsonArrayFromFileName:(NSString*)name andExt:(NSString*)ext
{
	NSError* error = nil;
	NSString* dataPath = [[NSBundle mainBundle] pathForResource:name ofType:ext];
	NSArray* array = [NSJSONSerialization JSONObjectWithData:[NSData dataWithContentsOfFile:dataPath]
													 options:kNilOptions
													   error:&error];
	return array;
}
+(void)logView:(UIView*)view{
	NSLog(@"%@:x:%f,y:%f,w:%f,h:%f",[[view class] description], view.frame.origin.x,view.frame.origin.y,view.frame.size.width,view.frame.size.height);
}

+(NSMutableArray*)trackImageView:(UIImageView*)imgView withId:(NSString*)imgId andIdType:(NSString*)idType andType:(NSString*)imgType
{
	NSMutableArray* viewImagesArray = [[NSMutableArray alloc] init];
    
    //first view dictionary
    NSMutableDictionary* splashScreenImgViewDictionary = [[NSMutableDictionary alloc] init];
    
    //add first view to first view dictonary
    [splashScreenImgViewDictionary setObject:imgView forKey:@"view"];
    
    //images array for that view
    NSMutableArray* splashScreenImageArray = [[NSMutableArray alloc] init];
    
    //first image
    NSMutableDictionary* splashScreenImageBackgroundDict = [[NSMutableDictionary alloc] init];
    //[splashScreenImageBackgroundDict setObject:@"http://usa.visa.com/img/home/logo_visa.gif" forKey:@"id"];
    [splashScreenImageBackgroundDict setObject:imgId forKey:@"id"];
    [splashScreenImageBackgroundDict setObject:idType forKey:@"idType"];
    [splashScreenImageBackgroundDict setObject:imgType forKey:@"type"];
    
    //position data
    NSMutableDictionary* splashScreenImgPositionDict = [[NSMutableDictionary alloc] init];
    [splashScreenImgPositionDict setObject:[NSNumber numberWithLong:(long)(imgView.bounds.origin.x)] forKey:@"x"];
    [splashScreenImgPositionDict setObject:[NSNumber numberWithLong:(long)(imgView.bounds.origin.y)] forKey:@"y"];
    [splashScreenImgPositionDict setObject:[NSNumber numberWithLong:(long)(imgView.bounds.size.width)] forKey:@"width"];
    [splashScreenImgPositionDict setObject:[NSNumber numberWithLong:(long)(imgView.bounds.size.height)] forKey:@"height"];
    [splashScreenImageBackgroundDict setObject:splashScreenImgPositionDict forKey:@"position"];
    
    //add first image to image arrayarray
    [splashScreenImageArray addObject:splashScreenImageBackgroundDict];
    
    //add images array to dictionary
    [splashScreenImgViewDictionary setObject:splashScreenImageArray forKey:@"images"];
    
    //add first view dictionary to views-images collection
    [viewImagesArray addObject:splashScreenImgViewDictionary];
	return viewImagesArray;
}
@end
