/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/


#import <Foundation/Foundation.h>
#import "Product.h"
#import "BaseTag.h"
#import "Tag.h"

@interface TagShopAction5 : BaseTag<Tag>

-(id) initTag:(NSString *) pageName product:(Product *) product;

@end