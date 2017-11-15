//
//  WebViewHandler.m
//  TLFUILab_Tabbed
//
//  Created by Geoff Heeren on 11/25/13.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "WebViewHandler.h"

@implementation WebViewHandler

- (BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType{
	NSLog(@"webView:shouldStartLoadWithRequest:%@",[[request URL] absoluteString]);
	return YES;
}
- (void)webViewDidStartLoad:(UIWebView *)webView{
	
	
}
- (void)webViewDidFinishLoad:(UIWebView *)webView {
	
}
- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error{
	
}
@end
