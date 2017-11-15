//
//  Product.h
//  TL Connect
//
//  Created by Geoff Heeren on 1/14/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import <Foundation/Foundation.h>

@interface Product : NSObject
@property (nonatomic) NSString* prodCode;
@property (nonatomic) NSString* name;
@property (nonatomic) NSString* subtitle;
@property (nonatomic) NSString *blurb;
@property (nonatomic) NSString *imageName;
@property (nonatomic) NSArray *relatedItems;
-(id)initWithName:(NSString*)name andBlurb:(NSString*)blurb andItems:(NSArray*)items;
-(id)initWithDictionary:(NSDictionary*)dict;
@end
