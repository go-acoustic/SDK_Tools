//
//  AppDelegate.m
//  TL Connect
//
//  Created by Geoff Heeren on 1/10/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "AppDelegate.h"
#import <AdSupport/ASIdentifierManager.h>
@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    
//    setenv("EODebug", "1", 1);
//    setenv("TLF_DEBUG", "1", 1);
    [[TLFApplicationHelper sharedInstance] setCXAAdvertisingId:[[[ASIdentifierManager sharedManager]advertisingIdentifier] UUIDString]];
	[[TLFApplicationHelper sharedInstance] enableTealeafFramework];
	[[TLFCustomEvent sharedInstance] logEvent:@"Application" value:[[NSBundle mainBundle] bundleIdentifier] ];
	[[TLFCustomEvent sharedInstance] logEvent:@"Device" value:[[UIDevice currentDevice] model] ];
	[[TLFCustomEvent sharedInstance] logEvent:@"Name" value:[[UIDevice currentDevice] name] ];
	NSString *sessionID=[[TLFApplicationHelper sharedInstance] currentSessionId];
	NSLog(@"TLF Session Id: %@", sessionID);
	NSLog(@"System Name:%@",[[UIDevice currentDevice] systemName]);
	NSLog(@"System Version:%@",[[UIDevice currentDevice] systemVersion]);
	NSLog(@"Vendor ID:%@",[[UIDevice currentDevice] identifierForVendor]);
	return YES;
}

- (void)applicationWillResignActive:(UIApplication *)application
{
	// Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
	// Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
	// Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
	// If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
	// Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
	// Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
	// Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

@end
