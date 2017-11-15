/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2017
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import <Foundation/Foundation.h>
@interface DigitalAnalytics : NSObject

/*!
 * @abstract Starts Digital Analytics SDK
 * @discussion Enables internal data structures required for Digital Analytics, creates new session
 * @return Yes if successfully starts the SDK.
 */
+ (BOOL)startup;

/*!
 * @abstract Starts new Digital Analytics SDK session.
 * @discussion startup API must already be called before calling this API. If you have called shutdown API, please first call startup before calling this API. After calling this API SDK starts sending tags to server with newly created session id
 * @return Yes if successfully creates new SDK session.
 */
+ (BOOL)startNewSession;

/*!
 * @abstract Shuts down the Digital Analytics SDK
 * @discussion Call this API if you want to shutdown Digital Analytics SDK. Calling any fireXX APIs after calling shutdown will not send any tags to the server. If you want fireXXX APIs to resume sending tags to the server call startup API.
 * @return Yes if successfully shutsdown Digital Analytics Session.
 */
+ (BOOL)shutdown;

/*!
 * @abstract Retrives session ID for current DA SDK Session
 * @discussion API will return empty string if DA SDK startup is not called, else a valid string
 * @return Valid Session ID string for current session
 */
+ (NSString*)sessionId;

/**
 Setup the advertising ID that is used in conjunction with CXA.
 @param value - the string which represents the advertising ID.
 @return if the advertising ID was successfully updated or not.
 */
+ (BOOL)setCXAAdvertisingId:(NSString*)value;

/**
 Returns a string representation of the advertising ID.
 @return a string representation of the advertising ID.
 */
+ (NSString*)getCXAAdvertisingId;

/*!
 * @abstract Retrives visitor ID for current DA SDK Session
 * @discussion API will return empty string if DA SDK startup is not called, else a valid string
 * @return Valid Visitor ID string for current session
 */+ (NSString*)visitorId;

/*!
 * @abstract Retrives library version for DA SDK
 * @discussion API will return library version as a string in XX.XX.XX.XXXX format
 * @return Valid library version string for the DA SDK
 */
+ (NSString*)libraryVersion;

/*!
 * @abstract Sends Page View Tag to the server
 * @discussion startup API must be called once before calling any fireXXX API
 * @param pageId - pageId is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param category - please refer to documentation for details
 * @param searchTerm - please refer to documentation for details
 * @param searchResult - please refer to documentation for details
 * @param attributes - please refer to documentation for details
 * @param cmmmc - please refer to documentation for details
 * @return Yes if successfully fires Page View Tag
 */
+ (BOOL)firePageView:(NSString *)pageId category:(NSString *)category searchTerm:(NSString *)searchTerm searchResult:(NSString *)searchResult attributes:(NSArray *)attributes cmmmc:(NSArray *)cmmmc;

/*!
 * @abstract Sends Product View Tag to the server
 * @discussion startup API must be called once before calling any fireXXX API
 * @param pageId - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param productId - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param productName - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param category - please refer to documentation for details
 * @param attributes - please refer to documentation for details
 * @return Yes if successfully fires Product View Tag
 */
+ (BOOL)fireProductview:(NSString *)pageId productId:(NSString*)productId productName:(NSString *)productName category:(NSString*)category attributes:(NSArray *)attributes;

/*!
 * @abstract Sends Shop Action 5 Tag to the server
 * @discussion startup API must be called once before calling any fireXXX API
 * @param productId - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param productName - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param quantity - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param baseUnitPrice - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param category - please refer to documentation for details
 * @param currencyCode - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param attributes - please refer to documentation for details
 * @return Yes if successfully fires Shop Action 5 Tag
 */
+ (BOOL)fireShopAction5:(NSString *)productId productName:(NSString *)productName quantity:(NSString *)quantity baseUnitPrice:(NSString *)baseUnitPrice category:(NSString *)category currencyCode:(NSString *)currencyCode attributes:(NSArray *)attributes;

/*!
 * @abstract Sends Shop Action 9 Tag to the server
 * @discussion startup API must be called once before calling any fireXXX API
 * @param productId - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param productName - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param quantity - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param baseUnitPrice - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param category - please refer to documentation for details
 * @param orderId - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param orderSubTotal - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param customerId - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param currencyCode - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param attributes - please refer to documentation for details
 * @return Yes if successfully fires Shop Action 9 Tag
 */
+ (BOOL)fireShopAction9:(NSString *)productId productName:(NSString *)productName quantity:(NSString *)quantity baseUnitPrice:(NSString *)baseUnitPrice category:(NSString *)category orderId:(NSString *)orderId orderSubTotal:(NSString *)orderSubTotal customerId:(NSString *)customerId currencyCode:(NSString *)currencyCode attributes:(NSArray *)attributes;

/*!
 * @abstract Sends Order Tag to the server
 * @discussion startup API must be called once before calling any fireXXX API
 * @param orderId - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param subtotal - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param shippingCharge - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param customerId - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param customerCity - please refer to documentation for details
 * @param customerState - please refer to documentation for details
 * @param customerZip - please refer to documentation for details
 * @param currencyCode - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param attributes - please refer to documentation for details
 * @return Yes if successfully fires Order Tag
 */
+ (BOOL)fireOrder:(NSString *)orderId subtotal:(NSString *)subtotal shippingCharge:(NSString *)shippingCharge customerId:(NSString *)customerId customerCity:(NSString *)customerCity customerState:(NSString *)customerState customerZip:(NSString *)customerZip currencyCode:(NSString *)currencyCode attributes:(NSArray *)attributes;

/*!
 * @abstract Sends Registration Tag to the server
 * @discussion startup API must be called once before calling any fireXXX API
 * @param customerId - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param email - please refer to documentation for details
 * @param customerCity - please refer to documentation for details
 * @param customerState - please refer to documentation for details
 * @param customerZip - please refer to documentation for details
 * @param customerCountry - please refer to documentation for details
 * @param attributes - please refer to documentation for details
 * @return Yes if successfully fires Registration Tag
 */
+ (BOOL)fireRegistration:(NSString *)customerId email:(NSString *)email customerCity:(NSString *)customerCity customerState:(NSString *)customerState customerZip:(NSString *)customerZip customerCountry:(NSString*)customerCountry attributes:(NSArray *)attributes;

/*!
 * @abstract Sends Element Tag to the server
 * @discussion startup API must be called once before calling any fireXXX API
 * @param elementId - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param elementCategory - please refer to documentation for details
 * @param attributes - please refer to documentation for details
 * @return Yes if successfully fires Element Tag
 */
+ (BOOL)fireElement:(NSString *)elementId elementCategory:(NSString *)elementCategory attributes:(NSArray *)attributes;

/*!
 * @abstract Sends Conversion Tag to the server
 * @discussion startup API must be called once before calling any fireXXX API
 * @param eventId - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param actionType - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param eventCategory - please refer to documentation for details
 * @param eventPoints - please refer to documentation for details
 * @param attributes - please refer to documentation for details
 * @return Yes if successfully fires Conversion Tag
 */
+ (BOOL)fireConversionEvent:(NSString *)eventId actionType:(NSString *)actionType eventCategory:(NSString *)eventCategory eventPoints:(NSString *)eventPoints attributes:(NSArray *)attributes;

/*!
 * @abstract Sends Link Click Tag to the server
 * @discussion startup API must be called once before calling any fireXXX API
 * @param pageId - please refer to documentation for details
 * @param linkName - is a mandatory parameter, firePageView API would fail to post tag to the server without it
 * @param link - please refer to documentation for details
 * @return Yes if successfully fires Link Click Tag
 */
+ (BOOL)fireLinkClick:(NSString *)pageId linkName:(NSString *)linkName link:(NSString *)link;

/*!
 * @abstract Sends Impression Tag to the server
 * @discussion startup API must be called once before calling any fireXXX API
 * @param pageId - please refer to documentation for details
 * @param cmsp - please refer to documentation for details
 * @param cmre - please refer to documentation for details
 * @return Yes if successfully fires Impression Tag
 */
+ (BOOL)fireImpression:(NSString *)pageId cmsp:(NSArray *)cmsp cmre:(NSArray *)cmre;
/*!
 * @abstract Sets new client id for current DA SDK Session
 * @discussion API will return YES if DA SDK successfully sets new client id into internal data structure, else NO
 * @return a boolean indicating success or failure of the API call
 */
+(BOOL)activateClientId:(NSString*)clientId;
/*!
 * @abstract Resets to original clientId from BasicInfo plist for current DA SDK Session
 * @discussion API will return default client id if successful else nil or empty string or the clientid in use
 * @return Valid Session ID string for current session
 */
+(BOOL)resetToDefaultClientId;
/*!
 * @abstract Sets opt out mode string
 * @discussion Valid parameter values are @"opt_out" @"opt_in" @"anonymous", default is @"opt_in"
 * @return YES if value set in successfully
 */
+(BOOL)setOptOut:(NSString*)mode;
/*!
 * @abstract Gets opt out mode string value
 * @discussion API will return opt out mode string value
 * @return If nothing is set or invalid value is set, it will return @"opt_in"
 */
+(NSString*)getOptOut;
@end
