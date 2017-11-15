/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
#import "Catalog.h"
#import "ProductCategory.h"
#import "Product.h"

@implementation Catalog

@synthesize categories;

-(id) init
{
    self = [super init];
    if(self)
    {
        //Go ahead and bootstrap the Catalog data to be used by the App
        ProductCategory *breakfast = [[ProductCategory alloc] init];
        breakfast.categoryId = @"/kitchensink/iossdk/BREAKFAST";
        breakfast.name = @"Breakfast";
        
        
        NSArray *cheeriosAttributes = [[NSArray alloc] initWithObjects:@"honey",@"kellogs", @"healthy", nil];
        Product *cheerios = [[Product alloc] initWithDetails:@"/kitchensink/iossdk/CHEERIOS" categoryId:breakfast.categoryId name:@"Cheerios" baseUnitPrice:@"5.00" quantity:0 attributes:cheeriosAttributes];
        
        
        NSArray *cocoapuffsAttributes = [[NSArray alloc] initWithObjects:@"sweet",@"general_mills", @"unhealthy", nil];
        Product *cocoapuffs = [[Product alloc] initWithDetails:@"/kitchensink/iossdk/COCOAPUFFS" categoryId:breakfast.categoryId name:@"Cocoa Puffs" baseUnitPrice:@"2.00" quantity:0 attributes:cocoapuffsAttributes];
        
        breakfast.products = [[NSArray alloc] initWithObjects:cheerios, cocoapuffs, nil];
        
        ProductCategory *lunch = [[ProductCategory alloc] init];
        lunch.categoryId = @"/kitchensink/iossdk/LUNCH";
        lunch.name = @"Lunch";
        
        NSArray *lcAttributes = [[NSArray alloc] initWithObjects:@"salty",@"lean_cuisine", @"healthy", nil];
        Product *leancuisine = [[Product alloc] initWithDetails:@"/kitchensink/iossdk/LEANCUISINE" categoryId:lunch.categoryId name:@"Lean Cuisine" baseUnitPrice:@"2.00" quantity:0 attributes:lcAttributes];
        
        NSArray *hpAttributes = [[NSArray alloc] initWithObjects:@"spicy",@"hot_pockets", @"quick_meal", nil];
        Product *hotpockets = [[Product alloc] initWithDetails:@"/kitchensink/iossdk/HOTPOCKETS" categoryId:lunch.categoryId name:@"Hot Pockets" baseUnitPrice:@"1.00" quantity:0 attributes:hpAttributes];
        
        lunch.products = [[NSArray alloc] initWithObjects:leancuisine, hotpockets, nil];
        
        self.categories = [[NSArray alloc] initWithObjects:breakfast, lunch, nil];
    }
    
    return self;
}

@end
