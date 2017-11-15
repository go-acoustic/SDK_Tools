//
//  Licensed Materials - Property of IBM
//  (C) Copyright IBM Corp. 2016
//  US Government Users Restricted Rights - Use, duplication or disclosure
//  restricted by GSA ADP Schedule Contract with IBM Corp.
//

#import "SingleWebPageViewController.h"


@interface SingleWebPageViewController ()
{
	BOOL didScrape;
	BOOL webView1DidLoad;

}
@end

@implementation SingleWebPageViewController

- (void)viewDidLoad
{
	didScrape=NO;
	webView1DidLoad=NO;

	[super viewDidLoad];
	[_sessionID setText:[[TLFApplicationHelper sharedInstance] currentSessionId]];
	//[self loadLocalFiles];
    // Turn off screen shots
    //[[TLFCustomEvent sharedInstance] logPrintScreenEvent];
}

-(void)viewDidAppear:(BOOL)animated
{
	[super viewDidAppear:animated];
}

- (void)loadLocalFiles
{
	NSString *path = [[NSBundle mainBundle] pathForResource:@"embeddedGesturesMenu" ofType:@"html" inDirectory:@"/mobile_domcap/" ];
	if (path)
	{
		NSURL *url = [NSURL fileURLWithPath:path];
		NSURLRequest *request1 = [NSURLRequest requestWithURL:url];
		[_webView1 loadRequest:request1];
	}
}

- (void)loadRemoteAddress
{
	NSURL *url = [NSURL URLWithString:@"http://tealeafdemostore2.demos.ibm.com/webapp/wcs/stores/servlet/en/aurora"];
	NSURLRequest *request1 = [NSURLRequest requestWithURL:url];
	[_webView1 loadRequest:request1];
}
// Omar
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


- (IBAction)uploadData:(id)sender
{
	[[TLFApplicationHelper sharedInstance] requestManualServerPost];
}

- (IBAction)back:(id)sender
{
	NSComparisonResult result = [_leftTopButton.title caseInsensitiveCompare:@"Load"];
	if( result == NSOrderedSame )
	{
		[self loadLocalFiles];
		_leftTopButton.title = @"Back";
	}
	else
	{
		[_webView1 goBack];
	}
}
@end
