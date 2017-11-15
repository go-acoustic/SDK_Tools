/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
#import <Foundation/Foundation.h>
#import "Tag.h"
#import "BaseTag.h"

@interface TagPageView : BaseTag<Tag>

-(id) initTag:(NSString *) pageName sessionStarted:(BOOL) sessionStarted;

@end
