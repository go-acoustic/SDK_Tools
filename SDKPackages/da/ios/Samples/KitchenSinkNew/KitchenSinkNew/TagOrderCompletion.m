/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "TagOrderCompletion.h"
#import "Product.h"
#import "DigitalAnalytics.h"

@interface TagOrderCompletion()
@property (nonatomic,strong) NSString *pageName;
@property (nonatomic,strong) ShoppingCart *shoppingCart;
@property (nonatomic,strong) Order *order;
@end

@implementation TagOrderCompletion

@synthesize pageName;
@synthesize shoppingCart;
@synthesize order;

-(id) initTag:(NSString *) pageNameParam shoppingCart:(ShoppingCart *)shoppingCartParam order:(Order *)orderParam
{
    self = [super init];
    
    self.pageName = pageNameParam;
    self.shoppingCart = shoppingCartParam;
    self.order = orderParam;
    
    return self;
}

-(void) executeTag
{
    BOOL shopAction9Success = YES;
    
    NSString *orderId = self.order.orderId;
    NSString *orderSubtotal = self.order.subTotal;
    NSString *customerId = self.order.customerId;
    NSString *currencyCode = self.order.currencyCode;
    NSString *shippingCharge = self.order.shippingCharge;
    NSString *customerCity = self.order.customerCity;
    NSString *customerState = self.order.customerState;
    NSString *customerZip = self.order.customerZip;
    NSArray *orderAttributes = self.order.attributes;
    
    //Fire a ShopAction9 for each Product in the SHopping Cart
    NSSet *products = self.shoppingCart.products;
    for(Product* product in products)
    {
        NSString *productId = product.productId;
        NSString *productName = product.name;
        NSString *categoryId = product.categoryId;
        NSString *quantity = [NSString stringWithFormat:@"%d",product.quantity];
        NSString *baseUnitPrice = product.baseUnitPrice;
        NSArray *attributes = product.attributes;
        
        if(![DigitalAnalytics fireShopAction9:productId productName:productName quantity:quantity baseUnitPrice:baseUnitPrice category:categoryId orderId:orderId orderSubTotal:orderSubtotal customerId:customerId currencyCode:currencyCode attributes:attributes])
        {
            shopAction9Success = NO;
        }
    }
    
    //Fire a Tag for Order Placement
    BOOL orderSuccess = [DigitalAnalytics fireOrder:orderId subtotal:orderSubtotal shippingCharge:shippingCharge customerId:customerId customerCity:customerCity customerState:customerState customerZip:customerZip currencyCode:currencyCode attributes:orderAttributes];
    
    //Indicate success
    [self finish:(shopAction9Success && orderSuccess)];
}

@end
