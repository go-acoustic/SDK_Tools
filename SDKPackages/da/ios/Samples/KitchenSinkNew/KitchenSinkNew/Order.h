/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
#import <Foundation/Foundation.h>

@interface Order : NSObject

@property (nonatomic,strong) NSString *orderId;
@property (nonatomic,strong) NSString *subTotal;
@property (nonatomic,strong) NSString *customerId;
@property (nonatomic,strong) NSString *customerCity;
@property (nonatomic,strong) NSString *customerState;
@property (nonatomic,strong) NSString *customerZip;
@property (nonatomic,strong) NSString *shippingCharge;
@property (nonatomic,strong) NSString *currencyCode;
@property (nonatomic,strong) NSArray  *attributes;


@end
