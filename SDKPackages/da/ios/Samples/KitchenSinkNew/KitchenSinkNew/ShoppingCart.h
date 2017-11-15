/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
#import <Foundation/Foundation.h>

#import "Product.h"
#import "Order.h"

@interface ShoppingCart : NSObject

@property (strong,nonatomic) NSMutableSet *products;

+(ShoppingCart *) instance;

-(void) addToCart:(Product *) product;

-(BOOL) isEmpty;

-(void) clear;

-(float) calculateSubtotal;

-(Order *) generateOrder;

@end
