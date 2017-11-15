/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import <Foundation/Foundation.h>
#import "BaseTag.h"
#import "Tag.h"
#import "ShoppingCart.h"
#import "Order.h"

@interface TagOrderCompletion : BaseTag<Tag>

-(id) initTag:(NSString *) pageName shoppingCart:(ShoppingCart *)shoppingCart order:(Order *)order;

@end
