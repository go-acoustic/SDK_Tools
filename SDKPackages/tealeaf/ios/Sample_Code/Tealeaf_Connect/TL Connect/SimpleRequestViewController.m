//
//  SimpleRequestViewController.m
//  TL Connect
//
//  Created by Geoff Heeren on 1/27/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "SimpleRequestViewController.h"
#import "RequestSelectionViewController.h"
#import "TLConnectData.h"

@interface SimpleRequestViewController ()
{
	NSMutableArray *aryData;
	NSMutableArray *aryControls;
	UIEdgeInsets contentInsets;
}
@end

@implementation SimpleRequestViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
	UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
								   initWithTarget:self
								   action:@selector(dismissKeyboard)];
	

	[self.view addGestureRecognizer:tap];
	aryData=[NSMutableArray array];
	[self loadFormData];
	aryControls=[NSMutableArray arrayWithCapacity:[aryData count]];
    [super viewDidLoad];
	[self registerForKeyboardNotifications];

}
- (void)viewDidAppear:(BOOL)animated{
	[self loadScreenData];
	[super viewDidAppear:animated];
	//[[TLFCustomEvent sharedInstance] logScreenLayoutWithViewController:self viewImagesArray:nil];
	
}
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)dismissKeyboard {
	[self.view endEditing:YES];
}
- (IBAction)btnSubMenuClick:(id)sender {
	[self performSegueWithIdentifier:@"RequestSelection" sender:sender];
}

- (IBAction)btnSubmitFormClick:(id)sender {
	UIAlertView *alert=[[UIAlertView alloc] initWithTitle:@"Thank You!" message:@"We will be in touch with you soon." delegate:self cancelButtonTitle:@"Ok" otherButtonTitles: nil];
	[alert show];
}
-(void)loadFormData
{
	[self addDataWithLabel:@"First Name:" andValue:@"" andList:@[]];
	[self addDataWithLabel:@"Last Name:" andValue:@"" andList:@[]];
	[self addDataWithLabel:@"Work Email:" andValue:@"" andList:@[]];
	[self addDataWithLabel:@"Phone:" andValue:@"" andList:@[]];
	[self addDataWithLabel:@"Job Title:" andValue:@"Select One" andList:[TLConnectData getTitles]];
	[self addDataWithLabel:@"Job Role:" andValue:@"Select One" andList:[TLConnectData getRoles]];
	[self addDataWithLabel:@"Company:" andValue:@"" andList:@[]];
	[self addDataWithLabel:@"Industry:" andValue:@"Select One" andList:[TLConnectData getIndustries]];
	[self addDataWithLabel:@"Country:" andValue:@"United States" andList:[TLConnectData getCountries]];
	[self addDataWithLabel:@"State/Province:" andValue:@"Select One" andList:[TLConnectData getStates]];
	
}
-(void)loadScreenData
{
	self.lblValueJobTitle.text=aryData[4][kRV_Key_Value];
	self.lblValueJobRole.text=aryData[5][kRV_Key_Value];
	self.lblValueIndustry.text=aryData[7][kRV_Key_Value];
	self.lblValueCountry.text=aryData[8][kRV_Key_Value];
	self.lblValueState.text=aryData[9][kRV_Key_Value];
	
}
-(void)submitForm{
//	NSMutableString* formData=[NSMutableString string];
//	for (NSDictionary *dict in aryData)
//	{
//		[formData appendString:[NSString stringWithFormat:@"%@%@\n", dict[kRV_Key_Label],dict[kRV_Key_Value]] ];
//	}
//	NSLog(@"%@",formData);
	[self.navigationController popViewControllerAnimated:YES];
	
}
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Make sure your segue name in storyboard is the same as this line
    if ([[segue identifier] isEqualToString:@"RequestSelection"])
    {
        // Get reference to the destination view controller
        RequestSelectionViewController *vc = [segue destinationViewController];
		NSInteger tag=((UIControl*)sender).tag;
		NSMutableDictionary *dict=aryData[tag];
		vc.dataDict=dict;
		
    }
}
-(void)addDataWithLabel:(NSString*)lbl andValue:(NSString*)val andList:(NSArray*)list
{
	[aryData addObject:[NSMutableDictionary dictionaryWithDictionary:@{kRV_Key_Label:lbl,kRV_Key_Value:val,kRV_Key_List:list}]];
}

#pragma mark UITextFieldDelegate

- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField{
	//NSLog(@"textFieldShouldBeginEditing:%@",textField.text);
	return YES;
}
- (void)textFieldDidBeginEditing:(UITextField *)textField
{

}
- (BOOL)textFieldShouldEndEditing:(UITextField *)textField{
	//NSLog(@"textFieldShouldEndEditing:%@",textField.text);
	return YES;
}
- (void)textFieldDidEndEditing:(UITextField *)textField{
    //NSLog(@"textFieldDidEndEditing:%@",textField.text);
	if (textField==self.txtFirstName)
		aryData[0][kRV_Key_Value]=textField.text;
	else if (textField==self.txtLastName)
		aryData[1][kRV_Key_Value]=textField.text;
	else if (textField==self.txtEmail)
		aryData[2][kRV_Key_Value]=textField.text;
	else if (textField==self.txtPhone)
		aryData[3][kRV_Key_Value]=textField.text;
	else if (textField==self.txtCompany)
		aryData[6][kRV_Key_Value]=textField.text;
		
	[textField resignFirstResponder];
}

- (BOOL)textFieldShouldClear:(UITextField *)textField{
	return YES;
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField{
	[textField resignFirstResponder];
	return YES;
}
- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string
{
	return YES;
}
#pragma mark - Keyboard-Fu
// Call this method somewhere in your view controller setup code.
- (void)registerForKeyboardNotifications
{
    [[NSNotificationCenter defaultCenter] addObserver:self
											 selector:@selector(keyboardWasShown:)
												 name:UIKeyboardDidShowNotification object:nil];
	
	[[NSNotificationCenter defaultCenter] addObserver:self
											 selector:@selector(keyboardWillBeHidden:)
												 name:UIKeyboardWillHideNotification object:nil];
	
}

// Called when the UIKeyboardDidShowNotification is sent.
- (void)keyboardWasShown:(NSNotification*)aNotification
{

}

- (void)keyboardWillBeHidden:(NSNotification*)aNotification
{

}
#pragma mark - UIAlertVeiwDelegate
- (void)didPresentAlertView:(UIAlertView *)alertView
{
}
- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
	[self submitForm];
}
@end
