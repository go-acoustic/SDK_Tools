//
// Copyright (C) 2021 Acoustic, L.P. All rights reserved.
//
// NOTICE: This file contains material that is confidential and proprietary to
// Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
// industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
// Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
// prohibited.
//
#import <Foundation/Foundation.h>
#import <WebKit/WebKit.h>
#import <Tealeaf/TLFPublicDefinitions.h>

@interface TLFApplicationHelper : NSObject <TLFLibDelegate, TLFApplicationHelperJSProtocol>

@property(nonatomic, unsafe_unretained)NSObject<TLFLibDelegate> *TLFLibDelegate;

/*!
 * @abstract Returns the common application helper.
 * @return  Returns the common application helper, used to manage the flow and lifecycle of the application and Tealeaf.
 */
+ (TLFApplicationHelper *)sharedInstance;

/*!
 * @abstract Turns on the Tealeaf framework
 * @return Returns YES or NO based on whether the framework was successfully enabled or not.
 */
- (BOOL)enableTealeafFramework;

/**
 * @abstract Turns off the Tealeaf Framework.
 * @return Returns YES or NO based on whether the framework was successfully enabled or not.
 */
- (BOOL)disableTealeafFramework;

/*!
 * @abstract Pauses capture of data on the Tealeaf framework
 * @return Returns YES or NO based on whether the framework was successfully paused or not.
 */
- (BOOL)pauseTealeaf;

/**
 * @abstract Resumes capture of data on the Tealeaf framework
 * @return Returns YES or NO based on whether the framework was successfully resumed or not.
 */
- (BOOL)resumeTealeaf;

/**
 Setup the Kill Switch URL. This is the URL to be checked when the framework initializes. If the page is not reachable the framework will not initialize. Setting the URL will update the configurable plist file for that user's device.
 @param value - The string representation of the new Kill Switch URL.
 */
- (void)setKillSwitchUrl:(NSString*)value;

/**
 Setup the Post Message URL. The URL for posting data to your server. Setting the URL will update the configurable plist file for that user's device.
 @param value - The string representation of the new Target Page URL.
 */
- (void)setPostMessageUrl:(NSString*)value;

/**
 Requests that the framework post to the server as soon as possible. It is a good idea to call this method after you have finished your own network transmissions. The device shuts down the Wi-Fi and cell radios when there is no activity. NOTE: In TLFConfigurableItems.plist ManualPostEnabled must be set to YES.
 */
- (void)requestManualServerPost;

/**
 Starts a new Tealeaf Session. For example, if you want a new session to begin after every successful purchase.
 @return if the Tealeaf session successful started or not.
 */
- (BOOL)startNewTLFSession;

/**
 Returns the current session ID.
 @return the current Tealeaf session ID
 */
- (NSString*)currentSessionId;

/**
 Returns a BOOL value indicating if the Tealeaf Framework is enabled or not.
 @return If the Tealeaf Framework is enabled or not.
 */
- (BOOL)isTLFEnabled;

/**
 Returns a string variable which represents the Tealeaf Framework version.
 @return The string representation of the Tealeaf Framework version.
 */
- (NSString*)frameworkVersion;

/**
 Sets logging level for an event. Event names are listed in TLFEventsLevels.plist. If you call this method for an event not listed in the TLFEventsLevels.plist then the set will fail.
 @param logLevel - the kTLFMonitoringLevelType (unsigned integer) for the event specified.
 @param event - the string representation of the event.
 @return if the logging level was successfully update or not.
 */
- (BOOL)setLogLevel:(NSUInteger)logLevel forEvent:(NSString*) event;

/**
 Gets logging level for an event. If the event is not found kTLFMonitoringLevel0 is returned. Event names are listed in TLFEventsLevels.plist.
 @param event - the string representation of the event
 @return the kTLFMonitoringLevelType (unsigned integer) for the event specified.
 */
- (NSUInteger)logLevelForEvent:(NSString*) event;
/**
 Sets the value of a configurable item in TLFConfigurableItems.plist file. This will only update the in memory value.
 @param configItem - the name of the configurable item. See TLFConfigurableItems.plist for a list of items.
 @param value - the new value of the configurable item.
 @return if the configurable item was successfully update or not.
 */
- (BOOL)setConfigurableItem:(NSString*)configItem value:(id)value;

/**
 Gets value of a configurable item either from TLFConfigurableItems.plist file or in memory data structure.
 @param configItem - the name of the configurable item. See TLFConfigurableItems.plist for a list of items.
 @return the value of the configurable item.
 */
- (id)valueForConfigurableItem:(NSString*)configItem;

///**
// Gets default value of a configurable item in TLFConfigurableItems.plist file.
// @param configItem - the name of the configurable item. See TLFConfigurableItems.plist for a list of items.
// @return the value of the configurable item.
// */
//- (id)defaultValueForConfigurableItem:(NSString*)configItem;

/**
 Setup the Device ID.
 @param value - the string which represents the new Device ID.
 @return if the Device ID was successfully updated or not.
 */
- (BOOL)setDeviceId:(NSString*)value;

/**
 Returns a string representation of the Device ID.
 @return a string representation of the Device ID.
 */
- (NSString*)getDeviceId;

/**
 Setup the advertising ID that is used in conjunction with CXA.
 @param value - the string which represents the advertising ID.
 @return if the advertising ID was successfully updated or not.
 */
- (BOOL)setCXAAdvertisingId:(NSString*)value;

/**
 Returns a string representation of the advertising ID.
 @return a string representation of the advertising ID.
 */
- (NSString*)getCXAAdvertisingId;

/**
 Returns a string representation of the current application context.
 @return a string representation of the current application context.
 */
- (NSString*)applicationContextName;

/**
 Set any application specific headers into each HTTP request to PostMessageURL
 @param headers - Dictionary of headers with key value pairs to be sent over the HTTP Request.
 */
- (void) setAdditionalHttpHeaders:(NSMutableDictionary*)headers;

/**
 Set an application specific header into each HTTP request to PostMessageURL
 @param name - Header key name to be sent over the HTTP Request.
 @param value - Header value to be sent over the HTTP Request.
 */
- (void) addAdditionalHttpHeader:(NSString*)value forName:(NSString*)name;

/**
 Set any application specific cookies into each HTTP request to PostMessageURL
 @param cookies - Array of NSHTTPCookie objects to be sent over the HTTP Request.
 */
- (void) setAdditionalHttpCookies:(NSMutableArray*)cookies;

/**
 Set any application specific cookie into each HTTP request to PostMessageURL
 @param cookie - NSHTTPCookie object to be sent over the HTTP Request.
 */
- (void) addAdditionalHttpCookie:(NSHTTPCookie*)cookie;

/**
 Get all HTTP Headers that are sent with each HTTP request to PostMessageURL
 @return Dictonary of HTTP Headers as key value pairs.
 */
- (NSDictionary*) getAdditionalHttpHeaders;

/**
 Get all HTTP cookies that are sent with each HTTP request to PostMessageURL
 @return Array of HTTP Cookies as NSHTTPCookie objects.
 */
- (NSArray*) getAdditionalHttpCookies;

/**
 Add session cookie to given NSMutableURLRequest object. Typically used when manually instrumenting the application.
 @param request - NSMutableURLRequest object to be sessionized.
 @return YES when successful else NO.
 */
- (BOOL) sessionizeRequest:(NSMutableURLRequest*)request;

/**
 Confirms if given NSURLRequest coming from javascript is a Tealeaf request URL. Typically used when manually instrumenting the application.
 @param request - NSMutableURLRequest object to be checked if is a Tealeaf request URL.
 @param webView - WKWebView object which contains Tealeaf hybrid bridge javascript code.
 @return YES when Tealeaf hybrid bridge request else NO.
 */
- (BOOL) isTealeafHybridBridgeRequest:(NSURLRequest*)request webView:(id)webView;

/**
 Injects Tealeaf hybrid bridge javascript code into the webpage loaded into WKWebView. Typically used when manually instrumenting the application.
 @param webView - WKWebView object into which Tealeaf hybrid bridge javascript code needs to be injected.
 @return YES when Tealeaf hybrid bridge javascript code injection is successful request else NO.
 */
- (BOOL) InjectTealeafHybridBridgeOnWebViewDidFinishLoad:(id)webView;

/**
 UIApplication sendEvent API which needs to be overriden in case developers are using their own class derived from UIApplication. Please see UIApplication documentation for details.
 @param event - UIEvent object that application will be processing.
 */
- (void)sendEvent:(UIEvent*)event;

/**
 UIApplication sendAction API which needs to be overriden in case developers are using their own class derived from UIApplication. Please see UIApplication documentation for details.
 @param action - selector of the event/action handler.
 @param target - UIView or UIControl that received the action.
 @param sender - UIView or UIControl that sent the action.
 @param event - UIEvent object that application will be processing.
 */
- (void)sendAction:(SEL)action to:(id)target from:(id)sender forEvent:(UIEvent *)event;

/**
 Requests that the framework returns the AutoLayoutConfiguration set by user
 @return Dictionary of configuration items in TealeafLayoutConfig.json file for AutoLayout
 */
-(NSDictionary *)layoutConfigItems;
@end
