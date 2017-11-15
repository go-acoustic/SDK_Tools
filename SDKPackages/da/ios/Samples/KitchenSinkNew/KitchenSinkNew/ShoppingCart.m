/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
#import "ShoppingCart.h"

@implementation ShoppingCart

@synthesize products;

+(ShoppingCart *) instance
{
    static ShoppingCart *singleton = nil;
    
    static dispatch_once_t singletonToken;
    
    dispatch_once(&singletonToken, ^{
        singleton = [[ShoppingCart alloc] init];
    });
    
    return singleton;
}

-(id) init
{
    self = [super init];
    if(self)
    {
        self.products = [[NSMutableSet alloc] init];
    }
    return self;
}

-(void) addToCart:(Product *) product
{
    [self.products removeObject:product];
    if(product.quantity > 0)
    {
        [self.products addObject:product];
    }
}

-(BOOL) isEmpty
{
    return self.products.count == 0;
}

-(void) clear
{
    [self.products removeAllObjects];
}

-(float) calculateSubtotal
{
    float subtotal = 0.00F;
    
    for(id cour in self.products)
    {
        Product *product = (Product *)cour;
        subtotal += [product totalPrice];
    }
    
    return subtotal;
}

-(Order *) generateOrder
{
    Order *order = [[Order alloc] init];
    
    //Generate an order id
    NSString *uuid = [[NSUUID UUID] UUIDString];
    NSString *orderId = [NSString stringWithFormat:@"/kitchensink/new/iossdk/%@",uuid];
    order.orderId = orderId;
    
    //Set the subtotal
    NSString *subtotalStr = [NSString stringWithFormat:@"%f", [self calculateSubtotal]];
    order.subTotal = subtotalStr;
    
    //Hard coded data...no need to complicate the user experience to collect this data
    order.currencyCode = @"USD";
    order.shippingCharge = @"5.00";
    order.attributes = [[NSArray alloc] initWithObjects:@"grocery",@"shopping", nil];
    
    return order;
}
@end
