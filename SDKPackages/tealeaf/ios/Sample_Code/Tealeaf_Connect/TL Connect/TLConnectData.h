//
//  TLConnectData.h
//  TL Connect
//
//  Created by Geoff Heeren on 1/27/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import <Foundation/Foundation.h>

@interface TLConnectData : NSObject
+(NSArray*)getIndustries;
+(NSArray*)getTitles;
+(NSArray*)getRoles;
+(NSArray*)getCountries;
+(NSArray*)getStates;
+(NSArray*)getProvinces;
@end
