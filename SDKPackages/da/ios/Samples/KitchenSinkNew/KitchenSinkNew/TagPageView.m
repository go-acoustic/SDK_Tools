/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
#import "TagPageView.h"

#import <UIKit/UIKit.h>
#import "DigitalAnalytics.h"

@interface TagPageView ()

@property (nonatomic,strong) NSString *pageName;
@property (nonatomic) BOOL sessionStarted;

@end

@implementation TagPageView

@synthesize pageName;
@synthesize sessionStarted;

-(id) initTag:(NSString *) pageNameParam sessionStarted:(BOOL) sessionStartedParam
{
    self = [super init];
    if(self)
    {
        self.pageName = pageNameParam;
        self.sessionStarted = sessionStartedParam;
    }
    return self;
}

-(void) executeTag
{
    if(self.sessionStarted)
    {
        NSLog(@"KitchenSinkNew is Starting New Digital Analytics Session");
        [DigitalAnalytics startNewSession];
    }
    
    BOOL success = [DigitalAnalytics firePageView:self.pageName category:@"iossdk" searchTerm:nil searchResult:nil attributes:nil cmmmc:nil];
    
    [self finish:success];
}
@end
