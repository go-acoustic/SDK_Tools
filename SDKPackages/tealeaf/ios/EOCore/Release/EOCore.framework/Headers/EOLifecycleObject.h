//
//  LifecycleObject.h
//  EOCore
//
//  Licensed Materials - Property of IBM
//  (C) Copyright IBM Corp. 2016
//  US Government Users Restricted Rights - Use, duplication or disclosure
//  restricted by GSA ADP Schedule Contract with IBM Corp.
//

#import <Foundation/Foundation.h>

@protocol EOLifecycleObject <NSObject>

@required

/*!
 * @abstract Access to the singleton representation of the EOLifecycleObject.
 * @discussion This is used to retrieve and reference the EOLifecycleObject. This should only init and return the EOLifecycleObject. EOCore will call enable on its own later in the lifecycle.
 * @return The singleton instance of the EOLifecycleObject.
 */
+ (instancetype)sharedInstance;

/*!
 * @abstract Contains all of the necessary steps to enable the module.
 * @discussion This is where all of the logic related to enabeling the module should be contained. In addition to enabeling the module it should update the isEnabled flag accordingly. If no additional logic is required then simply return YES.
 * @return If the module was successfully enabled or not.
 */
- (BOOL)enable;

/*!
 * @abstract Contains all of the necessary steps to disable the module.
 * @discussion This is where all of the logic related to disableing the module should be contained. In addition to disableing the module it should update the isEnabled flag accordingly. If no additional logic is required then simply return YES.
 * @return If the module was successfully disabled or not.
 */
- (BOOL)disable;

/*!
 * @abstract Reports whether the module is in an enabled state or not
 * @discussion The module is reponsible for updating the isEnabled property in enable and disable.
 * @return The current state of the module. Enabled = YES Disabled = NO.
 */
- (BOOL)isEnabled;

/*!
 * @abstract The file path to the Module settings bundle.
 * @discussion In order to provide clarity to end users and prevent conflict; this bundle should have a unique name ideally involving the name of the module. This bundle must contaion the following files AdvancedConfig.json and BasicConfig.plist. Omiiting one of these files will force the configuration loading to fail. Any additional files in this bundle will exist and be managed by the module alone and will be ignored by EOConfig.
 * @return The full path to the modules settings bundle.
 */
- (NSString *)settingsBundlePath;

@optional

/*!
 * @abstract Method to request any additional Http Header values required by the queues post URL.
 * @discussion This is an optional method. If there are no additional headers required then this method can be omitted. This is called at before each time the the queue is flushed to a particular target URL. If there are any cookies that need to be created or updated at the time of post then the cookie can be generated and posted to [NSHTTPCookieStorage sharedHTTPCookieStorage]. This call is executed in a background thread, any actions executed in this call must remain thread safe.
 * @return A dictionary containing all of the header keys and their repsective values.
 */
- (NSDictionary *)httpHeadersForSessionId:(NSString*)sessionId andJson:(NSString*)json;

/*!
 * @abstract Method to request any additional Http Header values required by the queues post URL.
 * @discussion This is an optional method. If there are no additional headers required then this method can be omitted. This is called at before each time the the queue is flushed to a particular target URL. If there are any cookies that need to be created or updated at the time of post then the cookie can be generated and posted to [NSHTTPCookieStorage sharedHTTPCookieStorage]. This call is executed in a background thread, any actions executed in this call must remain thread safe.
 * @return A dictionary containing all of the header keys and their repsective values.
 */
- (void)addCookiesToRequest:(NSMutableURLRequest*)request;

/*!
 * @abstract Method to respond to uncaught exception events
 * @discussion When EOCore captures an exception it will send a copy of the exception to each of the modules that implement this method. The module then has the option to log or react the the exception. After every module has responded to the exception then EOCore will flush the messages. The modules should not try to manually flush EOCore.
 */
- (void)handleUncaughtException:(NSException *)exception;

/*!
 * @abstract Method to save a new set of configurable items to the file system for this module 
 * @discussion When EOCore makes an amendment to the in-memory configurable items for a module, it calls this method to enable the module to save the new NSDictionary to the file system for the specific module
@return BOOL value indicating if the save was done  
 */

-(BOOL)saveCurrentConfigurableItems:(NSDictionary *)amendedDictionary;

/*!
 * @abstract Get notification application will terminate.
 * @discussion Get notification application is in background. You flush any temp queues in modules so data can be sent back to server.
 */
- (void)tlfAppWillTerminate;

@end
