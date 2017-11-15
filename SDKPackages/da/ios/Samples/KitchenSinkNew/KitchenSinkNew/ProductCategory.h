/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
#import <Foundation/Foundation.h>

@interface ProductCategory : NSObject

@property (strong,nonatomic) NSString *categoryId;
@property (strong,nonatomic) NSString *name;
@property (strong,nonatomic) NSArray *products;

@end
