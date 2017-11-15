//
//  RequestViewController.m
//  TL Connect
//
//  Created by Geoff Heeren on 1/15/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "RequestViewController.h"
#import "TLConnectData.h"
#import "TextFormCell.h"
#import "PickerFormCell.h"
#import "LabelFormCell.h"
#import "ButtonFormCell.h"
#import "RequestSelectionViewController.h"
@interface RequestViewController ()
{
	NSMutableArray *aryData;
	NSMutableArray *aryControls;
	NSInteger selectedRow;
	CGFloat pickerRowHeight;
	CGFloat frameHeight;
	UIEdgeInsets contentInsets;
}
@end

@implementation RequestViewController

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
	frameHeight=self.view.frame.size.height;
	pickerRowHeight=162.0;
    [super viewDidLoad];
	selectedRow= -1;
	aryData=[NSMutableArray array];
	[self loadFormData];
	aryControls=[NSMutableArray arrayWithCapacity:[aryData count]];
	[self registerForKeyboardNotifications];
}
-(void)viewDidAppear:(BOOL)animated{
	[super viewDidAppear:animated];
	[self.tableView reloadData];
	//[[TLFCustomEvent sharedInstance] logScreenLayoutWithViewController:self viewImagesArray:nil];
}
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
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
-(void)addDataWithLabel:(NSString*)lbl andValue:(NSString*)val andList:(NSArray*)list
{
	[aryData addObject:[NSMutableDictionary dictionaryWithDictionary:@{kRV_Key_Label:lbl,kRV_Key_Value:val,kRV_Key_List:list}]];
}
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Make sure your segue name in storyboard is the same as this line
    if ([[segue identifier] isEqualToString:@"RequestSelection"])
    {
        // Get reference to the destination view controller
        RequestSelectionViewController *vc = [segue destinationViewController];
		NSIndexPath *indexPath=[self.tableView indexPathForCell:sender];
		NSMutableDictionary *dict=aryData[indexPath.row];
		vc.dataDict=dict;
				
    }
}
-(void)submitForm{
	NSMutableString* formData=[NSMutableString string];
	for (NSDictionary *dict in aryData)
	{
		[formData appendString:[NSString stringWithFormat:@"%@%@\n", dict[kRV_Key_Label],dict[kRV_Key_Value]] ];
	}
	[self.navigationController popViewControllerAnimated:YES];
	
}
#pragma mark - UITableViewDataSource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
	return [aryData count]+1;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
	
	if (indexPath.row == [aryData count])
	{
		ButtonFormCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ButtonFormCell"];
		if (cell==nil)
			cell = [[ButtonFormCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"ButtonFormCell"];
		
		cell.accessoryType=UITableViewCellAccessoryNone;
		cell.selectionStyle=UITableViewCellSelectionStyleNone;
		cell.parent=self;
		return cell;
	}
	
	NSMutableDictionary *dict=aryData[indexPath.row];

	if ([dict[kRV_Key_List] count]==0)
	{
		TextFormCell *cell = [tableView dequeueReusableCellWithIdentifier:@"TextFormCell"];
		if (cell==nil)
			cell = [[TextFormCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"TextFormCell"];
		
		cell.accessoryType=UITableViewCellAccessoryNone;
		cell.parent=self;
		[cell loadDataWithDictionary:dict];
		cell.selectionStyle=UITableViewCellSelectionStyleNone;
		return cell;
	}

	else
	{
		LabelFormCell *cell = [tableView dequeueReusableCellWithIdentifier:@"LabelFormCell"];
		if (cell==nil)
			cell = [[LabelFormCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"LabelFormCell"];
		
		cell.accessoryType=UITableViewCellAccessoryDisclosureIndicator;
		cell.parent=self;
		[cell loadDataWithDictionary:dict];
		cell.selectionStyle=UITableViewCellSelectionStyleNone;
		return cell;
	}
	
	
}
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
	return 1;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
	return ROW_HEIGHT;

}
#pragma mark - UITableViewDelegate
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
	[self.view endEditing:YES];
	UITableViewCell *cell=[tableView cellForRowAtIndexPath:indexPath];
	cell.selected=NO;
	NSDictionary *dict=aryData[indexPath.row];
	if ([dict[kRV_Key_List] count]>0)
	{
		
		[self performSegueWithIdentifier:@"RequestSelection" sender:cell];
	}
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
    NSDictionary* info = [aNotification userInfo];
    CGSize kbSize = [[info objectForKey:UIKeyboardFrameBeginUserInfoKey] CGRectValue].size;
	contentInsets=self.tableView .contentInset;
	CGRect aRect = self.view.frame;
	aRect.size.height -= kbSize.height;
	CGPoint origin = CGPointMake(self.activeCell.frame.origin.x, self.activeCell.frame.origin.y + self.activeCell.frame.size.height);
	origin.y -= self.tableView.contentOffset.y;
	if (!CGRectContainsPoint(aRect, origin) ) {
		NSLog(@"Needs to move up");
		CGPoint scrollPoint = CGPointMake(0.0, self.activeCell.frame.origin.y + self.activeCell.frame.size.height -(aRect.size.height));
		[self.tableView setContentOffset:scrollPoint animated:YES];
	}
}

- (void)keyboardWillBeHidden:(NSNotification*)aNotification
{
    self.tableView.contentInset = contentInsets;
}

@end
