//
//  Licensed Materials - Property of IBM
//  (C) Copyright IBM Corp. 2018
//  US Government Users Restricted Rights - Use, duplication or disclosure
//  restricted by GSA ADP Schedule Contract with IBM Corp.
//

#import <UIKit/UIKit.h>
#import <JavaScriptCore/JavaScriptCore.h>
#import <Foundation/Foundation.h>


#define kTLFCoreModule @"TLFCoreModule"
#define kConfigurableItemEventLevels                    @"EventLevels"
#define kConfigurableItemTreatJsonDictionariesAsString  @"TreatJsonDictionariesAsString"

#define kItemLogLevels @"ItemLogLevels"

typedef NS_ENUM(NSUInteger, TLFScreenViewType) {
    TLFScreenViewTypeVisit = 3,
    TLFScreenViewTypeLoad = 2,
    TLFScreenViewTypeUnload = 1,
    TLFScreenViewTypeNil = 0
};

//Enums
#ifndef NS_ENUM
#import <Foundation/Foundation.h>
#endif
typedef NS_ENUM(int, kTLFMonitoringLevelType) {
    kTLFMonitoringLevelIgnore = 0,
    kTLFMonitoringLevelError = 1,
    kTLFMonitoringLevelWarning = 2,
    kTLFMonitoringLevelVerbose = 3,
    kTLFMonitoringLevelInfo = 4,
    kTLFMonitoringLevelDebug = 5,
    
    //Deprecated
    kTLFMonitoringLevelUnknown = kTLFMonitoringLevelIgnore,
    kTLFMonitoringLevel0 = kTLFMonitoringLevelIgnore,
    kTLFMonitoringLevel1 = kTLFMonitoringLevelError,
    kTLFMonitoringLevel2 = kTLFMonitoringLevelWarning,
    kTLFMonitoringLevel3 = kTLFMonitoringLevelVerbose,
    kTLFMonitoringLevelNotLog = kTLFMonitoringLevelIgnore
};

extern NSString* kTLFButtonClickEvent;
extern NSString* kTLFToggleButtonClickEvent;
extern NSString* kTLFSliderValueChangeEvent;
extern NSString* kTLFStepperValueChangeEvent;
extern NSString* kTLFSelectListValueChangeEvent;
extern NSString* kTLFDatePickerDateChangeEvent;
extern NSString* kTLFTextBoxTextChangeEvent;
extern NSString* kTLFScrollerScrollChangeEvent;
extern NSString* kTLFActionSheetButtonIndexEvent;
extern NSString* kTLFActionSheetShowEvent;
extern NSString* kTLFAlertviewButtonIndexEvent;
extern NSString* kTLFAlertViewShowEvent;
extern NSString* kTLFPrintscreenEvent;
extern NSString* kTLFCustomEventEvent;
extern NSString* kTLFExceptionEvent;
extern NSString* kTLFConnectionEvent;
extern NSString* kTLFMobileStateEvent;

// The configurable items, needs to match with keys of "TLFResources.bundle/TLFConfigurableItems.plist" file.
#define kConfigurableItemLoggingLevel                                       @"LoggingLevel"
#define kConfigurableItemCachingLevel                                       @"CachingLevel"
#define kConfigurableItemCachedFileMaxBytesSize                             @"CachedFileMaxBytesSize"
#define kConfigurableItemHasToPersistLocalCache                             @"HasToPersistLocalCache"
#define kConfigurableItemPostMessageLevelWifi                               @"PostMessageLevelWiFi"
#define kConfigurableItemPostMessageLevelCellular                           @"PostMessageLevelCellular"
//#define kConfigurableItemPostMessageUrl                                   @"PostMessageURL"
#define kConfigurableItemPostMessageTimeIntervals                           @"PostMessageTimeIntervals"
#define kConfigurableItemPostMessageMaxBytesSize                            @"PostMessageMaxBytesSize"
#define kConfigurableItemPostMessageSecondLevel                             @"PostMessageSecondLevel"
#define kConfigurableItemDoPostOnIntervals                                  @"DoPostOnIntervals"
#define kConfigurableItemDoPostAppIsLaunched                                @"DoPostAppIsLaunched"
#define kConfigurableItemDoPostAppGoesToBackground                          @"DoPostAppGoesToBackground"
#define kConfigurableItemDoPostAppGoesToClose                               @"DoPostAppGoesToClose"
#define kConfigurableItemDoPostAppComesFromBackground                       @"DoPostAppComesFromBackground"
#define kConfigurableItemKillSwitchEnabled                                  @"KillSwitchEnabled"
#define kConfigurableItemKillSwitchUrl                                      @"KillSwitchUrl"
#define kConfigurableItemKillSwitchTimeout                                  @"KillSwitchTimeout"
#define kConfigurableItemKillSwitchMaxNumberOfTries                         @"KillSwitchMaxNumberOfTries"
#define kConfigurableItemKillSwitchTimeInterval                             @"KillSwitchTimeInterval"
#define kConfigurableItemUseWhiteList                                       @"UseWhiteList"
#define kConfigurableItemWhiteListParam                                     @"WhiteListParam"
#define kConfigurableItemManualPostEnabled                                  @"ManualPostEnabled"
#define kConfigurableItemDelayTimeOfTLFInitialization                       @"DelayTimeOfTLFInitialization"
#define kConfigurableItemDoPostOnScreenChange                               @"DoPostOnScreenChange"
#define kConfigurableItemPostMessageTimeout                                 @"PostMessageTimeout"
#define kConfigurableItemMaxStringsLength                                   @"MaxStringsLength"
#define kConfigurableItemDisableAutoInstrumentation                         @"DisableAutoInstrumentation"
#define kConfigurableItemCompressPostMessage                                @"CompressPostMessage"
#define kConfigurableItemPercentOfScreenshotsSize                           @"PercentOfScreenshotsSize"
#define kConfigurableItemDisableTLFFrameworkFlush                           @"DisableTLFFrameworkFlush"
#define kConfigurableItemScreenshotFormat                                   @"ScreenshotFormat"
#define kConfigurableItemJavaScriptInjectionDelay                           @"JavaScriptInjectionDelay"
#define kConfigurableItemFilterMessageTypes                                 @"FilterMessageTypes"
#define kConfigurableItemMessageTypesToBeFiltered                           @"MessageTypes"
#define kConfigurableItemSessionTimeout                                     @"SessionTimeout"
#define kConfigurableItemAddMessageTypeHeader                               @"AddMessageTypeHeader"
#define kConfigurableItemMessageTypeHeader                                  @"MessageTypeHeader"
#define kConfigurableItemLogViewLayoutOnScreenTransition	                @"LogViewLayoutOnScreenTransition"
#define kConfigurableItemGetImageDataOnScreenLayout                         @"GetImageDataOnScreenLayout"
#define kConfigurableItemSessionizationCookieName                           @"SessionizationCookieName"
#define kConfigurableItemSetGestureDetector                                 @"SetGestureDetector"
#define kConfigurableItemCaptureNativeGesturesOnWebview                     @"CaptureNativeGesturesOnWebview"
#define kConfigurableItemLogLocationEnabled                                 @"LogLocationEnabled"
#define kConfigurableItemSaasAppKey                                         @"AppKey"
#define kConfigurableItemAppendMapIDs                                       @"AppendMapIds"
#define kConfigurableItemAutoLayout                                         @"AutoLayout"
#define kConfigurableItemRemoveIp                                           @"RemoveIp"
#define kConfigurableItemIpPlaceholder                                      @"IpPlaceholder"
#define kConfigurableItemUseJPGForReplayImagesExtension                     @"UseJPGForReplayImagesExtension"
#define kConfigurableItemAutoWebViewCaptureDelay                            @"AutoWebViewCaptureDelay"
#define kConfigurableItemDefaultAutoLayoutDelay                             @"DefaultAutoLayoutDelay"
#define kConfigurableItemDisableKeyboardCapture                             @"DisableKeyboardCapture"
#define kConfigurableItemEnableWebViewInjectionForDisabledAutoCapture       @"EnableWebViewInjectionForDisabledAutoCapture"
#define kConfigurableItemDisableAlertAutoCapture                            @"DisableAlertAutoCapture"  
#define kConfigurableItemDisableAlertBackgroundForDisabledLogViewLayout     @"DisableAlertBackgroundForDisabledLogViewLayout"
#define kConfigurableItemInitialZIndex                                      @"InitialZIndex"
#define kConfigurableItemUseXpathId                                         @"UseXpathId"

// TLF Public Protocols
@protocol TLFSavePrintScreenOperationDelegate <NSObject>
@optional
- (void)savePrintScreenOnTemporaryDirectoryOperationDidFinishSuccessfullyWithImageName:(NSString*)imageName;

@end
@protocol TLFLibDelegate <NSObject>
@optional
/**
 After set a delegate to your TLFApplication implement this callback to generate your custom Session ID
 */
- (NSString*)sessionIdGeneration;

@end
@protocol TLFApplicationHelperJSProtocol <JSExport>
- (BOOL)enableTealeafFramework;
- (void)disableTealeafFramework;
- (void)requestManualServerPost;
- (BOOL)startNewTLFSession;
- (NSString*)currentSessionId;
- (BOOL)setConfigurableItem:(NSString*)configItem value:(id)value;
- (id)valueForConfigurableItem:(NSString*)configItem;
- (id)defaultValueForConfigurableItem:(NSString*)configItem;
- (void)addAdditionalHttpHeader:(NSString*)value forName:(NSString*)name;
@end
@protocol TLFCustomControlDelegate <NSObject>
@optional
- (BOOL)isTLFCustomControlHidden;
- (NSInteger)tagTLFCustomControl;
- (id)parentTLFCustomControl;
- (CGRect)frameTLFCustomControl;
- (UIColor*)colorTLFCustomControl;
- (UIColor*)backgroundColorTLFCustomControl;

- (NSString*)textTLFCustomControl;
- (BOOL)isTLFCustomControlTextHidden;
- (CGRect)textFrameTLFCustomControl;
- (UIColor*)textTLFCustomControlColor;
- (UIColor*)textTLFCustomControlBackgroundColor;

- (UIImage*)imageTLFCustomControl;
- (BOOL)isTLFCustomControlImageHidden;
- (CGRect)imageFrameTLFCustomControl;
- (UIColor*)imageOpacityTLFCustomControl;
- (UIColor*)imageBackgroundColorTLFCustomControl;

//- (UIImage*)controlBackgroundImageTLFCustomControl;
@end

@protocol TLFCustomControlDelegateX <NSObject>
@optional
- (NSArray*) imageViewsTLFCustomControl;
- (NSArray*) controlsTLFCustomControl;
@end

@protocol TLFCustomEventJSProtocol <JSExport>
- (BOOL)logEvent:(NSString*)eventName;
- (BOOL)logEvent:(NSString*)eventName values:(NSDictionary*)values;
- (BOOL)logPrintScreenEvent;
- (BOOL)logJSONMessagePayloadStr:(NSString*)payload;
@end
