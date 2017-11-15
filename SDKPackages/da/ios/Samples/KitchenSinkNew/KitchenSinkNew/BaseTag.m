/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "BaseTag.h"
#import <UIKit/UIKit.h>

@implementation BaseTag

-(void) finish:(BOOL)success
{
    NSString *tagClass = NSStringFromClass([self class]);
    NSString *message = nil;
    if(success)
    {
        message = [NSString stringWithFormat:@"%@ Success",tagClass];
    }
    else
    {
        message = [NSString stringWithFormat:@"%@ Failed",tagClass];
    }
    
    UIAlertView *toast = [[UIAlertView alloc] initWithTitle:nil
                                                    message:message
                                                   delegate:nil
                                          cancelButtonTitle:nil
                                          otherButtonTitles:@"Close", nil];
    [toast show];
    
    /*int duration = 1; // duration in seconds
    
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, duration * NSEC_PER_SEC), dispatch_get_main_queue(), ^{
        [toast dismissWithClickedButtonIndex:0 animated:YES];
    });*/
}

@end
