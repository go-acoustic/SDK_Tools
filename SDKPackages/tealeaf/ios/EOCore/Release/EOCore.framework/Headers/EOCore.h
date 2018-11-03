//
//  EOCore.h
//  EOCore
//
//  Licensed Materials - Property of IBM
//  (C) Copyright IBM Corp. 2016
//  US Government Users Restricted Rights - Use, duplication or disclosure
//  restricted by GSA ADP Schedule Contract with IBM Corp.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <EOCore/EOLifecycleObject.h>
#import <EOCore/EOMessage.h>
#import <EOCore/EOPublicHeader.h>
#import <EOCore/EOReachability.h>
//Extensions
#import <EOCore/NSDictionary+EOCoreExtensions.h>

@interface EOCore : NSObject <EOLifecycleObject>

/*!
 * @abstract The collection of modules managed by EOCore.
 * @return The collection of modules managed by EOCore.
 */
@property(nonatomic, readonly, strong) NSMutableDictionary *classReferences;

/*!
 * @abstract The current state of EOCore. Enabled and running or disabled.
 * @return True if the module is enabled False if disabled.
 */
@property(nonatomic, readonly, getter=isEnabled) BOOL enabled;

/*!
 * @abstract The common EOCore SessionID
 * @discussion This session ID is shared by all modules. A module that doesn't specify an ID will be defaulted to this ID. This value will also be posted as a cookie to combine data from various modules.
 * @return The current EOCore session ID
 */
@property(nonatomic, readonly, strong) NSString *currentSessionID;

/*!
 * @abstract The currentLoggingLevel being used in EOCore
 * @discussion This variable determine which messages will be sent and which will be discarded
 * @return The current EOCore Monitoring Level
 */
@property(assign, readonly) kEOMonitoringLevel currentLoggingLevel;

/*!
 * @abstract Returns the current EOCore Framework Version.
 * @return the current EOCore Framework Version.
 */
- (NSString *)frameworkVersion;

/*!
 * @abstract Enables the module and EOCore if not yet enabled.
 * @discussion This call will check with EOCore and if necessary enable EOCore along with it's necessary services.
 * @return If the module was successfully enabled or not.
 */
- (BOOL)enableModule:(NSString *)moduleName;

/*!
 * @abstract Disables the module.
 * @discussion This call will disable the module. EOCore will remain enabled until the end of the application lifecycle.
 * @return If the module was successfully disabled or not.
 */
- (BOOL)disableModule:(NSString *)moduleName;

/*!
 * @abstract Retrieves the lifecycle object matching the class name.
 * @discussion Searches for the lifecycle object associated to the class name. If no object is found this will return nil. The target object must conform to the EOLifecycleObject protocol and implement the required sharedInstance method.
 * @param name - An NSString representing the class name of the target EOLifecycleObject
 * @return The instance of the EOLifecycleObject. If no object is found the method will return nil.
 */
- (id<EOLifecycleObject>)lifecycleObjectForName:(NSString *)name;

/*!
 * @abstract Posts an EOMessage object to the active session of a particular module.
 * @discussion Posts an EOMessage object, or subclass of an EOMessage, to the active session of a particular module. The active session is determined by the last session id to receive a message. If no session has been created a new session with the EOCore Session ID will be generated and the message will be posted to that session. If the device is currently connected via Cellular than the message will not be posted if its value is higher than that configured in PostMessageLevelCellular. If the device is currently connected via Wifi than the message will not be posted if its value is higher than that configured in PostMessageLevelWifi.
 * @param message - The EOMessage or EOMessage subclass that will be added to the queue for the specified module.
 * @param moduleName - The class name of the module's EOLifecycleObject posting the message.
 * @return If the message was successfully posted to the queue or not.
 */
- (BOOL)postMessage:(EOMessage *)message forModuleName:(NSString *)moduleName;

/*!
 * @abstract Posts an EOMessage object to the specified session of a particular module.
 * @discussion Posts an EOMessage object, or subclass of an EOMessage, to the specified session of a particular module. If the session is nil it will default to If no session has been created a new session with the EOCore Session ID will be generated and the message will be posted to that session. If the device is currently connected via Cellular than the message will not be posted if its value is higher than that configured in PostMessageLevelCellular. If the device is currently connected via Wifi than the message will not be posted if its value is higher than that configured in PostMessageLevelWifi.
 * @param message - The EOMessage or EOMessage subclass that will be added to the queue for the specified module.
 * @param moduleName - The class name of the module's EOLifecycleObject posting the message.
 * @param sessionID - The sessionID to post the message to.
 * @return If the message was successfully posted to the queue or not.
 */
- (BOOL)postMessage:(EOMessage *)message forModuleName:(NSString *)moduleName withSessionID:(NSString *)sessionID;

/*!
 * @abstract Request to flush queues to their respective target urls.
 * @discussion Request a flush of all queues. These queues will be prepared and the flush will then happen in the background.
 */
- (void)flushQueues;

/*!
 * @abstract Gets the module's configuration item from AdvancedConfig.json or BasicConfig.plist that matches the specified key.
 * @discussion Gets the module's configuration item from AdvancedConfig.json or BasicConfig.plist that matches the specified key.
 * @param key - An NSString indicating which value is being requested
 * @param moduleName - The class name of the module's EOLifecycleObject for which the configuration item is referencing.
 * @return The value of the configuration item key.
 */
- (id)getConfigItem:(NSString *)key forModuleName:(NSString *)moduleName;

/*!
 * @abstract Unloads the configuration and reloads the data from the files AdvancedConfig.json and BasicConfig.plist.
 * @discussion This allows configuration values to be reverted after an update or temporary change.
 * @param moduleName - The class name of the module's EOLifecycleObject for which to reload data.
 * @return If the configuration data was successfully loaded or not.
 */
- (BOOL)reloadConfigForModuleName:(NSString *)moduleName;

/*!
 * @abstract Allows for configurable data to be updated by a particular module.
 * @discussion This will allow for the creation of new data as well as the ability to change existing data. Note when this does not save the data to the stored json and plist files. The new data can be a partial list of key value pairs. Omitting an existing key form the newConfig will not effect the value for that particular key.
 * @param moduleName - The class name of the module's EOLifecycleObject for which the configuration data is referencing.
 * @param newConfig - The updated configuration data.
 * @return If the configuration data was successfully updated or not.
 */
- (BOOL)updateConfigForModuleName:(NSString *)moduleName withData:(NSDictionary *)newConfig;
- (unsigned long long)getCurrentSessionStartTimeforModuleName:(NSString *)moduleName;
-(NSString *)getConfigItemAsString:(NSString *)key forModuleName:(NSString *)moduleName;
-(NSNumber *)getConfigItemAsNumber:(NSString *)key forModuleName:(NSString *)moduleName;
-(BOOL ) getConfigItemAsBool:(NSString *)key withDefault:(BOOL)theDefault forModuleName:(NSString *)moduleName;
-(NSNumber *)defaultLogLevel;

/*!
 * @abstract Generate device ID for Session
 * @discussion Generate device ID for Session
 * @return The device ID
 */
- (NSString *)deviceID;
//- (NSString *)generateDeviceID;
- (NSString *)generateUUID;
- (BOOL)setEOAdvertisingId:(NSString*)eoAdvertisingId;
- (NSString*)getEOAdvertisingId;

//environmental services exposure
- (NSNumber *)deviceTotalStorageCapacity;
- (NSNumber *)deviceTotalStorageFreeCapacity;
- (NSNumber *)deviceTotalMemory;
- (NSNumber *)deviceUserMemory;

//network information
- (kEONetworkStatus)currentNetworkStatus;
- (NSString *)currentNetworkReachabilityName;
- (NSString *)currentConnectionType;
- (NSString *)networkCarrierName;
- (NSString *)networkWiFiIPAddress;
- (NSString *)networkIPAddress;

- (NSData*)compressData:(NSData*)data;

- (NSString *)appVersion;
- (NSString *)appVersionMajorMinor;
- (NSString *)appName;

- (NSString*)currentOrientationName;
- (UIDeviceOrientation)currentDeviceOrientation;
- (CGSize)currentScreenDimensions;
- (UIInterfaceOrientation)currentUIOrientation;
- (NSNumber*)currentUIOrientationInDegrees;
- (NSNumber*)currentDeviceOrientationInDegrees;

- (NSNumber*)getCurrentBatteryLevel;
- (NSString*)setUserAgentFromBrowser;
- (NSString*)getUserAgentFromBrowser;

#pragma mark - Methods for config items specific to EOCore

-(NSNumber *)loggingLevel;
-(BOOL)isDynamicConfigurationEnabled;
-(BOOL)isManualPostEnabled;

@end

