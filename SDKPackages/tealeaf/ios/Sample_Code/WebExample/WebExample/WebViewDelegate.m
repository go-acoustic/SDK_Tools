//
//  Licensed Materials - Property of IBM
//  (C) Copyright IBM Corp. 2015
//  US Government Users Restricted Rights - Use, duplication or disclosure
//  restricted by GSA ADP Schedule Contract with IBM Corp.
//

#import "WebViewDelegate.h"

@implementation WebViewDelegate
-(void)webViewDidFinishLoad:(UIWebView *)webView
{
	//    if (webView==self.webView2)
	//        webView2DidLoad=YES;
	//    else if (webView==self.webView1)
	//        webView1DidLoad=YES;
	//    if (webView1DidLoad && webView2DidLoad && !didScrape)
	//    {
	//        didScrape=YES;
	//        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, 1 * NSEC_PER_SEC),dispatch_get_main_queue(), ^{
	//            [[TLFCustomEvent sharedInstance] logScreenLayoutWithViewController:self];
	//        });
	//    }
}

- (BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType
{
	return YES;
}
@end
