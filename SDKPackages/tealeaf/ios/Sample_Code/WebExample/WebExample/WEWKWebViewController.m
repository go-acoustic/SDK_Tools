//
//  Licensed Materials - Property of IBM
//  (C) Copyright IBM Corp. 2016
//  US Government Users Restricted Rights - Use, duplication or disclosure
//  restricted by GSA ADP Schedule Contract with IBM Corp.
//

#define BRIDGE_API_TEST

#import "WEWKWebViewController.h"

@interface WEWKWebViewController ()
@property(nonatomic, strong)WKWebView* webView;
@end

@implementation WEWKWebViewController
@synthesize webView;

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    WKWebViewConfiguration *theConfiguration = [[WKWebViewConfiguration alloc] init];
    CGRect frame = self.view.frame;
    frame.origin.y += self.navigationController.navigationBar.frame.size.height;
    frame.size.height -= self.navigationController.navigationBar.frame.size.height;
    self.view.frame = frame;

    self.webView = [[WKWebView alloc] initWithFrame:self.view.frame configuration:theConfiguration];
    self.webView.navigationDelegate = self;
    self.webView.UIDelegate = self;
    [self.view addSubview:self.webView];
    [self loadLocalFiles];
}
-(void)loadLocalFiles
{
#ifdef BRIDGE_API_TEST
    NSString *path = [[NSBundle mainBundle] pathForResource:@"indexWKWebViewTest" ofType:@"html" inDirectory:@"/mobile_domcap/" ];
#else
    NSString *path = [[NSBundle mainBundle] pathForResource:@"embeddedGesturesMenu" ofType:@"html" inDirectory:@"/mobile_domcap/" ];
#endif
    if (path)
    {
        NSURL *url = [NSURL fileURLWithPath:path];
        NSURLRequest *request1 = [NSURLRequest requestWithURL:url];
        [self.webView loadRequest:request1];
    }
}
-(void)loadRemoteFiles
{
    NSURL *url = [NSURL URLWithString:@"http://tealeafdemostore2.demos.ibm.com/webapp/wcs/stores/servlet/en/aurora"];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    [self.webView loadRequest:request];
}
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

-(void)webView:(WKWebView *)webView didStartProvisionalNavigation: (WKNavigation *)navigation {
    NSLog(@"didStartProvisionalNavigation");
}
- (void)webView:(WKWebView *)webView didFinishNavigation: (WKNavigation *)navigation{
    NSLog(@"didFinishNavigation");
}
-(void)webView:(WKWebView *)webView didFailNavigation: (WKNavigation *)navigation withError:(NSError *)error {
    NSLog(@"didFailNavigation");
}
-(void)userContentController:(WKUserContentController *)userContentController didReceiveScriptMessage:(WKScriptMessage *)message
{
    NSLog(@"userContentController");
}
@end
