/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "TagProductView.h"
#import "Product.h"
#import "DigitalAnalytics.h"

@interface TagProductView()

@property (nonatomic,strong) NSString *pageName;
@property (nonatomic,strong) NSArray *products;

@end

@implementation TagProductView

@synthesize pageName;
@synthesize products;

-(id) initTag:(NSString *) pageNameParam products:(NSArray *) productsParam
{
    self = [super init];
    
    if(self)
    {
        self.pageName = pageNameParam;
        self.products = productsParam;
    }
    
    return self;
}

-(void) executeTag
{
    BOOL success = YES;
    
    for(Product *product in products)
    {
        NSString *productId = product.productId;
        NSString *productName = product.name;
        NSString *categoryId = product.categoryId;
        NSArray *attributes = product.attributes;
        
        if(![DigitalAnalytics fireProductview:self.pageName productId:productId productName:productName category:categoryId attributes:attributes])
        {
            success = NO;
        }
    }
    
    [self finish:success];
}

@end
