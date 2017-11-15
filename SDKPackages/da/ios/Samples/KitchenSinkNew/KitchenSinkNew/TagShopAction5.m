/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "TagShopAction5.h"
#import "DigitalAnalytics.h"

@interface TagShopAction5()

@property (nonatomic,strong) NSString *pageName;
@property (nonatomic,strong) Product *product;

@end

@implementation TagShopAction5

@synthesize pageName;
@synthesize product;

-(id) initTag:(NSString *) pageNameParam product:(Product *) productParam
{
    self = [super init];
    if(self)
    {
        self.pageName = pageNameParam;
        self.product = productParam;
    }
    
    return self;
}

-(void) executeTag
{
    // Perform Tagging
    NSString *productId = self.product.productId;
    NSString *productName = self.product.name;
    NSString *categoryId = self.product.categoryId;
    NSString *currency = @"USD"; //hard coded for sake of simplicity
    NSString *baseUnitPrice = self.product.baseUnitPrice;
    NSString *quantity = [NSString stringWithFormat:@"%d",self.product.quantity];
    NSArray *attributes = self.product.attributes;
    
    BOOL success = [DigitalAnalytics fireShopAction5:productId productName:productName quantity:quantity baseUnitPrice:baseUnitPrice category:categoryId currencyCode:currency attributes:attributes];
    
    [self finish:success];
}

@end
