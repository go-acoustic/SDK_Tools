//
//  TextFieldHandler.m
//  TLFUILab_Tabbed
//
//  Created by Geoff Heeren on 11/25/13.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "TextFieldHandler.h"

@implementation TextFieldHandler
#pragma mark UITextFieldDelegate

- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField{
	NSLog(@"textFieldShouldBeginEditing:");
	return YES;
}
- (void)textFieldDidBeginEditing:(UITextField *)textField
{
	NSLog(@"textFieldDidBeginEditing:");
}
- (BOOL)textFieldShouldEndEditing:(UITextField *)textField{
	NSLog(@"textFieldShouldEndEditing:");
	return YES;
}
- (void)textFieldDidEndEditing:(UITextField *)textField{
	[textField resignFirstResponder];
	NSLog(@"textFieldDidEndEditing:");
}

- (BOOL)textFieldShouldClear:(UITextField *)textField{
	NSLog(@"textFieldShouldClear:");
	return YES;
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField{
	[textField resignFirstResponder];
	NSLog(@"textFieldShouldReturn:");
	return YES;
}
- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string
{
	NSLog(@"textField:shouldChangeCharactersInRange:");
	return YES;
}
#pragma mark UITextViewDelegate
- (BOOL)textViewShouldBeginEditing:(UITextView *)textView{
	NSLog(@"textViewShouldBeginEditing:");
	return YES;
}
- (void)textViewDidBeginEditing:(UITextView *)textView
{
	NSLog(@"textViewDidBeginEditing:");
}
- (BOOL)textViewShouldEndEditing:(UITextView *)textView{
	NSLog(@"textViewShouldEndEditing:");
	return YES;
}
- (void)textViewDidEndEditing:(UITextView *)textView{
	[textView resignFirstResponder];
	NSLog(@"textViewDidEndEditing:");
}
- (BOOL)textView:(UITextView *)textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *)text{

	NSLog(@"textView:shouldChangeTextInRange:%@",text);
	return YES;
}
- (void)textViewDidChangeSelection:(UITextView *)textView{
	NSLog(@"textViewDidChangeSelection:");
}
- (BOOL)textView:(UITextView *)textView shouldInteractWithTextAttachment:(NSTextAttachment *)textAttachment inRange:(NSRange)characterRange{
	NSLog(@"textView:shouldInteractWithTextAttachment:");
	return YES;
}
- (BOOL)textView:(UITextView *)textView shouldInteractWithURL:(NSURL *)URL inRange:(NSRange)characterRange{
	NSLog(@"textView:shouldInteractWithURL:%@",[URL absoluteString]);
	return YES;
}
@end
