//
//  TLConnectHelper.h
//  TL Connect
//
//  Created by Geoff Heeren on 1/14/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

FOUNDATION_EXPORT float const ROW_HEIGHT;
FOUNDATION_EXPORT const NSString* kRV_Key_Label;
FOUNDATION_EXPORT const NSString* kRV_Key_Value;
FOUNDATION_EXPORT const NSString* kRV_Key_List;
FOUNDATION_EXPORT const NSString* kRV_Key_Selected;
typedef enum {SocialFeed_Facebook=0,SocialFeed_LinkedIn=1,SocialFeed_Twitter=2} SocialFeed;
typedef enum {TLProduct_CX=0,TLProduct_Mobile=1,TLProduct_Impact=2,TLProduct_Overstat=3,TLProduct_Reveal}TLProduct ;
@class Product;
@interface TLConnectHelper : NSObject
+(NSString*)contactPhone;
+(NSString*)contactEmail;
+(NSString*)getSocialUrl:(SocialFeed)feed;
+(NSString*)getSocialAppUrl:(SocialFeed)feed;
+(NSArray*)getProducts;
+(Product*)getProductFor:(TLProduct)productType;
+(CLLocationCoordinate2D)getTLOfficeCoord;

+(void)tagTableCell:(UITableViewCell*)cell withIndexPath:(NSIndexPath*)path;
+(void)dontClickThat;
+(void)logView:(UIView*)view;

+(NSMutableArray*)trackImageView:(UIImageView*)imgView withId:(NSString*)imgId andIdType:(NSString*)idType andType:(NSString*)imgType;
@end
