//
// Copyright (C) 2019 Acoustic, L.P. All rights reserved.
//
// NOTICE: This file contains material that is confidential and proprietary to
// Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
// industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
// Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
// prohibited.
//

/*!
 @file TLFCustomEvent.h
 @brief Header file defining all manual API calls that can be made to the TeaLeaf library
 @copyright Acoustic, L.P. 2017
 */

#import <Foundation/Foundation.h>
#import "TLFPublicDefinitions.h"
#import <CoreLocation/CoreLocation.h>

/*!
 @class TLFCustomEvent
 @brief TLFCustomEvent is a singleton class providing API method calls to the logging interface in the Tealeaf module. 
 @superclass Superclass: NSObject
 */
@interface TLFCustomEvent : NSObject<TLFCustomEventJSProtocol>

/*!
 @brief Returns the singleton custom event logger.
 @discussion The TLFCustomEvent singleton reference.
 @return  TLFCustomEvent Returns the singleton custom event logger, used for custom event logging.
 */
+ (TLFCustomEvent *)sharedInstance;

/*!
 @brief logEvent allows for the logging of custom event.
 @discussion When logging an event the event will be posted with the log level posted in "EventLevels" (configured in EOCoreSettings.bundle > BasicConfig.plist) will be used. If the event is not configured in EventLevels the default logging level "LoggingLevel" (configured in EOCoreSettings.bundle > AdvancedConfig.plist)\nWhen connected to a Cellular network only events with a log level less than or equal to the PostMessageLevelCellular level (configured in EOCoreSettings.bundle > BasicConfig.plist) will be posted. When connected to a Wifi network only events with a log level less than or equal to the PostMessageLevelWifi level (configured in EOCoreSettings.bundle > BasicConfig.plist) will be posted. If the network state cannot be determined then PostMessageLevelCellular will be used by default.
 @param eventName - the name of the event to be logged this will appear in the posted json
 @return BOOL Whether the message was successfully logged or not
 */
- (BOOL)logEvent:(NSString *)eventName;

/*!
 @brief logEvent allows for the logging of custom event and data.
 @discussion When logging an event the event will be posted with the log level posted in "EventLevels" (configured in EOCoreSettings.bundle > BasicConfig.plist) will be used. If the event is not configured in EventLevels the default logging level "LoggingLevel" (configured in EOCoreSettings.bundle > AdvancedConfig.plist)\nWhen connected to a Cellular network only events with a log level less than or equal to the PostMessageLevelCellular level (configured in EOCoreSettings.bundle > BasicConfig.plist) will be posted. When connected to a Wifi network only events with a log level less than or equal to the PostMessageLevelWifi level (configured in EOCoreSettings.bundle > BasicConfig.plist) will be posted. If the network state cannot be determined then PostMessageLevelCellular will be used by default.
 @param eventName - the name of the event to be logged this will appear in the posted json
 @param values - additional key value pairs to be logged with the message
 @return BOOL Whether the message was successfully logged or not
 */
- (BOOL)logEvent:(NSString *)eventName values:(NSDictionary *)values;

/*!
 @brief logEvent allows for the logging of custom event, data, and level.
 @discussion When connected to a Cellular network only events with a log level less than or equal to the PostMessageLevelCellular level (configured in EOCoreSettings.bundle > BasicConfig.plist) will be posted. When connected to a Wifi network only events with a log level less than or equal to the PostMessageLevelWifi level (configured in EOCoreSettings.bundle > BasicConfig.plist) will be posted. If the network state cannot be determined then PostMessageLevelCellular will be used by default.
 @param eventName - the name of the event to be logged this will appear in the posted json
 @param values - additional key value pairs to be logged with the message
 @param level - set a custom log level to the event. This will override the configured log level for that event.
 @return BOOL Whether the message was successfully logged or not
 */
- (BOOL)logEvent:(NSString *)eventName values:(NSDictionary *)values level:(kTLFMonitoringLevelType)level;

#pragma mark - Errors and Exceptions
/*!
 @brief Log an NSException
 @discussion In the event of an NSException, the developer can log that exception using the following API. Using this API call the unhandled field will be defaulted to NO.
 @param exception - the exception to be logged.
 @return BOOL Whether the message was successfully logged or not
 */
- (BOOL)logNSExceptionEvent:(NSException *)exception;

/*!
 @brief Log an NSException with any additional data.
 @discussion In the event of an NSException, the developer can log that exception using the following API. In addition to the data provided by the exception additional data can be logged by passing a dictionary into the dataDictionary field. Using this API call the unhandled field will be defaulted to NO.
 @param exception - the exception to be logged.
 @param dataDictionary - a dictionary of additional values to be logged with the exception.
 @return BOOL Whether the message was successfully logged or not
 */
- (BOOL)logNSExceptionEvent:(NSException *)exception dataDictionary:(NSDictionary*)dataDictionary;

/*!
 @brief Log an NSException with any additional data and setting the unhandled flag
 @discussion In the event of an NSException, the developer can log that exception using the following API. In addition to the data provided by the exception additional data can be logged by passing a dictionary into the dataDictionary field. The developer can also override the unhandled key and set the exception as either a handled or unhandled exception.
 @param exception - the exception to be logged.
 @param dataDictionary - a dictionary of additional values to be logged with the exception.
 @param unhandled - a flag indicating if the exception was handled or left unhandled.
 @return BOOL Whether the message was successfully logged or not
 */
- (BOOL)logNSExceptionEvent:(NSException *)exception dataDictionary:(NSDictionary*)dataDictionary isUnhandled:(BOOL)unhandled;

/*!
 @brief Log an NSError
 @discussion In the event of an NSError has been generated this API allows for the logging of the error.
 @param error - the error to be logged.
 @param message - additional information to be logged with the error.
 @param level - set a custom log level to the event. This will override the configured log level for the exception event.
 @return BOOL Whether the message was successfully logged or not
 */
- (BOOL)logNSErrorEvent:(NSError *)error message:(NSString *)message level:(kTLFMonitoringLevelType)level;

/*!
 @brief Log an NSError
 @discussion In the event of an NSError has been generated this API allows for the logging of the error.
 @param error - the error to be logged.
 @param message - additional information to be logged with the error.
 @param file - the file in which the Error occured. Can be caputured by passing  __FILE__ to the paramater.
 @param line - the line in which the Error occured. Can be caputured by passing  __LINE__ to the paramater.
 @param level - set a custom log level to the event. This will override the configured log level for the exception event.
 @return BOOL Whether the message was successfully logged or not
 */
- (BOOL)logNSErrorEvent:(NSError *)error message:(NSString *)message file:(const char *)file line:(unsigned int)line level:(kTLFMonitoringLevelType)level;

#pragma mark - Network Logging
/*!
 @brief Requests that the framework logs the connection information.
 @param urlSession - The NSURLSession object
 @param error - any NSError object
 @return BOOL If the event was successfully logged or not.
 */
-(BOOL)logNSURLSession:(id)urlSession error:(NSError*)error;

/*!
 @brief Requests that the framework logs the connection information.
 @param urlSession - The NSURLSession object.
 @param response - The NSURLResponse object from the connection request
 @param responseTime time taken by the server to respond.
 @return BOOL If the event was successfully logged or not.
 */
-(BOOL)logNSURLSession:(id)urlSession response:(NSURLResponse*)response responseTimeInMilliseconds:(long long)responseTime;

/*!
 @brief Requests that the framework logs the connection information.
 @param urlSession - The NSURLSession object
 @param request - Any NSURLRequest object
 @return BOOL If the event was successfully logged or not.
 */
-(BOOL)logNSURLSession:(id)urlSession request:(NSURLRequest*)request;

/*!
 @brief Requests that the framework logs the connection information.
 @param initTime - Time duration since current session start
 @param loadTime - Time duration since current session start //TODO: EVALUATE what param loadTime actually means
 @param connection - The NSURLSession object, can be nil
 @param request - The NSURLRequest object associated with the connection
 @param response - The NSURLResponse object from the connection request
 @param error - any NSError object
 @return BOOL If the event was successfully logged or not.
 */
- (BOOL)logConnectionWithInitTime:(NSNumber*)initTime loadTime:(NSNumber*)loadTime connection:(id)connection  request:(NSURLRequest*)request response:(NSURLResponse*)response error:(NSError*)error;

/*!
 @brief Requests that the framework logs the connection information.
 @param initTime - Time duration since current session start
 @param loadTime - Time duration since current session start //TODO: EVALUATE what param loadTime actually means
 @param connection - The NSURLSession object, can be nil
 @param request - The NSURLRequest object associated with the connection
 @param response - The NSURLResponse object from the connection request
 @param data - The NSData object from the connection request or response
 @param error - any NSError object
 @return BOOL If the event was successfully logged or not.
 */
- (BOOL)logConnectionWithInitTime:(NSNumber*)initTime loadTime:(NSNumber*)loadTime connection:(id)connection  request:(NSURLRequest*)request response:(NSURLResponse*)response data:(NSData*)data error:(NSError*)error;

#pragma mark - Location
/*!
 @brief Requests that the framework logs a geographic location
 @param location - A CLLocation Object containing a location of interest
 @return BOOL If the event was successfully logged or not.
 */
-(BOOL)logLocation:(CLLocation *)location;

/*!
 @brief Requests that the framework logs the location information. This is not logged automatically to avoid making unnecessary location updates and to protect the privacy of your application's users by ensuring that location is reported only when the app has some other reason to request it. Your application must include the Core Location framework.
 @param lat - The geographic latitude of the user.
 @param lng - The geographic longitude of the user.
 @param level - The monitoring level of the event.
 @return BOOL If the event was successfully logged or not.
 */
-(BOOL)logLocationUpdateEventWithLatitude:(double)lat longitude:(double)lng level:(kTLFMonitoringLevelType)level;
#pragma mark - UIControls
/**
 Requests that the framework logs the click events on any UIControl or UIView. Click event is a normalized form of touch up inside event.
 @param view - UIView object on which click event occurred.
 @param data - any additional custom data that needs to be sent as a dictionary along with the click event.
 @return if the event was successfully logged or not.
 */
-(BOOL)logClickEvent:(UIView*)view data:(NSDictionary*)data;

/**
 Requests that the framework logs the UITableViewCell or UICollectionViewCell's content changed event
 @param view - UIView object on which the event occurred
 @param data - Any additional custom data that needs to be sent as a dictionary along with the event
 @return if the event was successfully logged or not.
 */
-(BOOL)logValueChangeEvent:(UIView*)view data:(NSDictionary*)data;

/**
 Requests that the framework logs the edit event on UITextView, UITextViewSecure, UITextField or UITextFieldSecure
 @param view - UIView object on which edit event occurred
 @param data - any additional custom data that needs to be sent as a dictionary along with the event
 @return if the event was successfully logged or not.
 */
-(BOOL)logTextChangeEvent:(UIView*)view data:(NSDictionary*)data;
/**
 This example shows how to log the text change event of a UILabel. The label will print out the previous state as well as the current state.
 @param label - The UILabel to be logged.
 @return if the event was successfully logged or not.
 */
- (BOOL)logUILabelTextChange:(UILabel*)label;

#pragma mark - Screenview
/*!
 @brief Requests that the framework logs an application context.
 @param logicalPageName - Page name or title e.g. "Login View Controller"; Must not be empty.
 @param screenViewType - valid values are TLFScreenViewTypeLoad or TLFScreenViewTypeUnload; Must not be empty.
 @param referrer - Page name or title that loads logicalPageName. Could be empty.
 @return BOOL If the event was successfully logged or not.
 */
-(BOOL)logScreenViewContext:(NSString*)logicalPageName
  applicationContext:(TLFScreenViewType)screenViewType
            referrer:(NSString*)referrer;

/**
 Requests that the framework logs a Print Screen event. The screenshot in that moment is automatically associated.
 @return if the event was successfully logged or not.
 */
- (BOOL)logPrintScreenEvent;

/**
 Requests that the framework log an image as a background in a type 10 screen layout event.
 @param image - UIImage object that needs to be added as a background for the layout
 @return if the event was successfully logged or not.
 */
- (BOOL)logScreenLayoutWithImage:(UIImage *)image;

/**
 Requests that the framework logs the layout of the screen
 @param viewController - UIViewController object whose layout needs to be logged
 @return if the event was successfully logged or not.
 */
-(BOOL)logScreenLayoutWithViewController:(UIViewController *)viewController;

/**
 Requests that the framework logs the layout of the screen
 @param viewController - UIViewController object whose layout needs to be logged
 @param name - Custom name to associate with the view Controller
 @return if the event was successfully logged or not.
 */
-(BOOL)logScreenLayoutWithViewController:(UIViewController *)viewController andName:(NSString*)name;

/**
 Requests that the framework logs the layout of the screen
 @param viewController - UIViewController object whose layout needs to be logged
 @param delay - number of seconds to wait before logging the view.
 @return if the event was successfully logged or not.
 */
-(BOOL)logScreenLayoutWithViewController:(UIViewController *)viewController	andDelay:(CGFloat)delay;

/**
 Requests that the framework logs the layout of the screen
 @param viewController - UIViewController object whose layout needs to be logged
 @param delay - number of seconds to wait before logging the view.
 @param name - Custom name to associate with the view Controller
 @return if the event was successfully logged or not.
 */
-(BOOL)logScreenLayoutWithViewController:(UIViewController *)viewController	andDelay:(CGFloat)delay andName:(NSString*)name;

/**
 Requests that the framework logs the layout of the screen
 @param viewController - UIViewController object whose layout needs to be logged
 @param views - Array of views that will be logged along with the provided viewController
 @param delay - number of seconds to wait before logging the view.
 @return if the event was successfully logged or not.
 */
-(BOOL)logScreenLayoutWithViewController:(UIViewController *)viewController andRelatedViews:(NSArray*)views andDelay:(CGFloat)delay;

/**
 Requests that the framework logs the layout of the screen
 @param viewController - UIViewController object whose layout needs to be logged.
 @param views - Array of views that will be logged along with the provided viewController.
 @return if the event was successfully logged or not.
 */
-(BOOL)logScreenLayoutWithViewController:(UIViewController *)viewController andRelatedViews:(NSArray*)views;

/**
 Requests that the framework logs the layout of the screen
 @param viewController - UIViewController object whose layout needs to be logged.
 @param views - Array of views that will be logged along with the provided viewController.
 @param name - Custom name to associate with the view Controller
 @return if the event was successfully logged or not.
 */
-(BOOL)logScreenLayoutWithViewController:(UIViewController *)viewController andRelatedViews:(NSArray*)views andName:(NSString*)name;

/**
 Requests that the framework logs the layout of the screen
 @param viewController - UIViewController object whose layout needs to be logged.
 @param views - Array of views that will be logged along with the provided viewController.
 @param delay - number of seconds to wait before logging the view.
 @param name - Custom name to associate with the view Controller
 @return if the event was successfully logged or not.
 */
-(BOOL)logScreenLayoutWithViewController:(UIViewController *)viewController andRelatedViews:(NSArray*)views andDelay:(CGFloat)delay andName:(NSString*)name;

/**
 Requests that the framework logs the layout of the screen w/o logging a screenview change event
 @param viewController - UIViewController object whose layout needs to be logged.
 @param views - Array of related views to be logged
 @return if the event was successfully logged or not.
 */
-(BOOL)logScreenLayoutDynamicUpdateWithViewController:(UIViewController *)viewController andRelatedViews:(NSArray*)views ;

/**
 Message type to indicate form completion on view.
 @param submitted - Indicates if form/input data was submitted or not
 @return if the event was successfully logged or not.
 */
-(BOOL)logFormCompletion:(BOOL)submitted;

/**
 Message type to indicate form completion on view.
 @param submitted - Indicates if form/input data was submitted or not
 @param isValid - Indicating if the submitted input data was valid or not
 @return if the event was successfully logged or not.
 */
-(BOOL)logFormCompletion:(BOOL)submitted withValidData:(BOOL)isValid;

#pragma mark - JSON
/**
 Requests that the framework logs the Tealeaf JSON Message coming over from javascript. Must follow Tealeaf JSON message format.
 @param payload - NSString object that matches Tealeaf JSON Message type format
 @return if the event was successfully logged or not.
 */
-(BOOL)logJSONMessagePayloadStr:(NSString*)payload;

///**
// Retrieves the current view controller that appear during view did appear.
// @return current view controller that appear during view did appear.
// */
//-(UIViewController*)getCurrentDidAppearViewController;
@end
