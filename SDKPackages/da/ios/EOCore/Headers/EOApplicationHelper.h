//
//  Licensed Materials - Property of IBM
//  (C) Copyright IBM Corp. 2017
//  US Government Users Restricted Rights - Use, duplication or disclosure
//  restricted by GSA ADP Schedule Contract with IBM Corp.
//

/*!
 @file EOApplicationHelper.h
 @brief Header file defining all manual API calls that can be made to the TeaLeaf library
 @copyright IBM Corp. 2017
 @version 9.0.2
 */
#import <Foundation/Foundation.h>

/* Module Names */
#define kEOCoreModule @"EOCore"
#define kTLFCoreModule @"TLFCoreModule"
#define kDAModule @"DAMod"

/*!
 @class EOApplicationHelper
 @brief EOApplicationHelper is a singleton class providing API method calls to the logging interface in the EOCore module.
 @superclass Superclass: NSObject
 */
@interface EOApplicationHelper : NSObject

/*!
 @brief Returns the singleton EOApplicationHelper.
 @discussion The EOApplicationHelper singleton reference.
 @return EOApplicationHelper Returns the singleton, used for EOApplicationHelper.
 */
+ (EOApplicationHelper *)sharedInstance;

/*!
 * @abstract Allows for configurable data to be updated by a particular module.
 * @discussion This will allow for the creation of new data as well as the ability to change existing data. Note when this does not save the data to the stored json and plist files. The new data can be a partial list of key value pairs. Omitting an existing key form the newConfig will not effect the value for that particular key.
 * @param moduleName - The class name of the module's EOLifecycleObject for which the configuration data is referencing.
 * @param newConfig - The updated configuration data.
 * @return If the configuration data was successfully updated or not.
 */
- (BOOL)updateConfigWithData:(NSDictionary *)data forModuleName:(NSString *)moduleName;

/*!
 * @abstract Gets the module's configuration item from AdvancedConfig.json or BasicConfig.plist that matches the specified key.
 * @discussion Gets the module's configuration item from AdvancedConfig.json or BasicConfig.plist that matches the specified key.
 * @param key - An NSString indicating which value is being requested
 * @param moduleName - The class name of the module's EOLifecycleObject for which the configuration item is referencing.
 * @return The value of the configuration item key.
 */
- (id)getConfigItem:(NSString *)key forModuleName:(NSString *)moduleName;

/*!
 * @abstract Gets the module's configuration item from AdvancedConfig.json or BasicConfig.plist that matches the specified key as a BOOL value.
 * @discussion Gets the module's configuration item from AdvancedConfig.json or BasicConfig.plist that matches the specified key.
 * @param theDefault - In case no value if found, use this value as default.
 * @param key - An NSString indicating which value is being requested
 * @param moduleName - The class name of the module's EOLifecycleObject for which the configuration item is referencing.
 * @return The value of the configuration item key as a BOOL value.
 */
- (BOOL)getBOOLconfigItemForKey:(NSString *)key withDefault:(BOOL)theDefault forModuleName:(NSString *)moduleName;

/*!
 * @abstract Gets the module's configuration item from AdvancedConfig.json or BasicConfig.plist that matches the specified key as a NString value.
 * @discussion Gets the module's configuration item from AdvancedConfig.json or BasicConfig.plist that matches the specified key.
 * @param theDefault - In case no value if found, use this value as default.
 * @param key - An NSString indicating which value is being requested
 * @param moduleName - The class name of the module's EOLifecycleObject for which the configuration item is referencing.
 * @return The value of the configuration item key as a NString value.
 */
- (NSString *)getStringItemForKey:(NSString *)key withDefault:(NSString *)theDefault forModuleName:(NSString *)moduleName;

/*!
 * @abstract Gets the module's configuration item from AdvancedConfig.json or BasicConfig.plist that matches the specified key as a NSNumber value.
 * @discussion Gets the module's configuration item from AdvancedConfig.json or BasicConfig.plist that matches the specified key.
 * @param theDefault - In case no value if found, use this value as default.
 * @param key - An NSString indicating which value is being requested
 * @param moduleName - The class name of the module's EOLifecycleObject for which the configuration item is referencing.
 * @return The value of the configuration item key as a NSNumber value.
 */
- (NSNumber *)getNumberItemForKey:(NSString *)key withDefault:(NSNumber *)theDefault forModuleName:(NSString *)moduleName;

/*!
 * @abstract Sets the module's configuration item from AdvancedConfig.json or BasicConfig.plist that matches the specified key.
 * @discussion Sets the module's configuration item from AdvancedConfig.json or BasicConfig.plist that matches the specified key.
 * @param key - An NSString indicating which value is being requested
 * @param moduleName - The class name of the module's EOLifecycleObject for which the configuration item is referencing.
 * @return Whether it was able to set the value based on the configuration item key.
 */
- (BOOL)setConfigItem:(NSString*)item value:(id)value forModuleName:(NSString *)moduleName;
@end
