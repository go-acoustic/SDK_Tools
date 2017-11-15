#import "TealeafDynamicLoad.h"
#import "Tealeaf.framework/Headers/TLFApplicationHelper.h"
@implementation TealeafDynamicLoad
+ (void)load {
#ifdef DEBUG
    setenv("EODebug", "1", 1);
    setenv("TLF_DEBUG", "1", 1);
    //setenv("EOCORE_MICLOUD_MACHINE", "1", 1);
#endif
NSLog(@"Tealeaf SDK Loaded");
[[TLFApplicationHelper sharedInstance] enableTealeafFramework];
}
@end
