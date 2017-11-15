/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
#import <Foundation/Foundation.h>

@interface Product : NSObject

@property (strong,nonatomic) NSString *productId;
@property (strong,nonatomic) NSString *categoryId;
@property (strong,nonatomic) NSString *name;
@property (strong,nonatomic) NSString *baseUnitPrice;
@property (nonatomic) int quantity;
@property (strong,nonatomic) NSArray *attributes;

-(id) initWithDetails:(NSString *)productId categoryId:(NSString *)categoryId name:(NSString *)name baseUnitPrice:(NSString *)baseUnitPrice quantity:(int)quantity attributes:(NSArray *)attributes;

-(float) totalPrice;
@end
