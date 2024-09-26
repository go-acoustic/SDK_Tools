//
// Copyright (C) 2024 Acoustic, L.P. All rights reserved.
//
// NOTICE: This file contains material that is confidential and proprietary to
// Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
// industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
// Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
// prohibited.
//

/*!
 @file EOApplicationHelper.h
 @brief Header file defining all manual API calls that can be made to the TeaLeaf library
 @copyright Acoustic, L.P. 2019
 */
#import <Foundation/Foundation.h>
#import <UIKit/UIView.h>
#import <EOCore/NSString+EOCoreExtensions.h>

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
 * @param data - The updated configuration data.
 * @param moduleName - The class name of the module's EOLifecycleObject for which the configuration data is referencing.
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
 * @param item - An NSString indicating which key is being requested
 * @param value - Indicating which value is being requested from item
 * @param moduleName - The class name of the module's EOLifecycleObject for which the configuration item is referencing.
 * @return Whether it was able to set the value based on the configuration item key.
 */
- (BOOL)setConfigItem:(NSString*)item value:(id)value forModuleName:(NSString *)moduleName;

/*!
 * @abstract Get the current UIWindow on the screen.
 * @discussion Get the current UIWindow on the screen.
 * @return Get the current UIWindow on the screen.
 */
- (UIWindow *)getUIWindow;
@end
