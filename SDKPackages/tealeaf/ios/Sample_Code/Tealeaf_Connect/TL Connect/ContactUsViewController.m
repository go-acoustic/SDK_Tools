//
//  ContactUsViewController.m
//  TL Connect
//
//  Created by Geoff Heeren on 1/13/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "ContactUsViewController.h"
#import "SocialViewController.h"
typedef enum {Contact_Email=0,Contact_Phone=1,Contact_Facebook=2,Contact_LinkedIn=3}Contact_Rows;
@interface ContactUsViewController ()
{
	NSArray *aryContact;
	NSInteger fbRow;
	NSInteger liRow;
	NSInteger callRow;
	NSInteger emailRow;
}
@end

@implementation ContactUsViewController

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
	aryContact=@[@"Email",@"Phone",@"Facebook",@"LinkedIn"];
    [super viewDidLoad];
	// Do any additional setup after loading the view.
}
-(void)viewDidAppear:(BOOL)animated
{
	[super viewDidAppear:animated];
	//[[TLFCustomEvent sharedInstance] logScreenLayoutWithViewController:self viewImagesArray:nil];
}
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
-(void)showEmail
{
	MFMailComposeViewController *mail=[[MFMailComposeViewController alloc] init];
	mail.mailComposeDelegate=self;
	mail.title=@"Interested in Tealeaf";
	[mail setToRecipients:@[[TLConnectHelper contactEmail]]];
	
	[self presentViewController:mail animated:YES completion:nil];
}
-(void)askToCall
{
	UIAlertView *alert=[[UIAlertView alloc] initWithTitle:@"Call Tealeaf?" message:@"Would you like to call Tealeaf?" delegate:self cancelButtonTitle:@"Cancel" otherButtonTitles:@"Ok", nil];
	[alert show];
}
#pragma mark - UITableViewDelegate
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
	UITableViewCell *cell=[tableView cellForRowAtIndexPath:indexPath];
	cell.selected=NO;
	switch (indexPath.row) {
		case Contact_Email:
		{
			[self showEmail];
		}
			break;
		case Contact_Phone:
		{
			[self askToCall];
		}
			break;
		case Contact_Facebook:
		{
			if (![self openFacebook])
				[self performSegueWithIdentifier:@"Social" sender:cell];
		}
			break;
		case Contact_LinkedIn:
		{
			if (![self openInLinkedIn])
				[self performSegueWithIdentifier:@"Social" sender:cell];
		
		}
			break;
			
		default:
			break;
	}
}
-(BOOL)openInLinkedIn
{
	//Stupid LinkedIn will not open a company profile page in their app
	return NO;
	NSString *url = [NSString stringWithFormat:@"%@",[TLConnectHelper getSocialAppUrl:SocialFeed_LinkedIn]];
	[[UIApplication sharedApplication] openURL:[NSURL URLWithString:url]];
}
-(BOOL)openFacebook
{
	NSString *url = [NSString stringWithFormat:@"%@",[TLConnectHelper getSocialAppUrl:SocialFeed_Facebook]];
	if ([[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:url]])
	{
		[[UIApplication sharedApplication] openURL:[NSURL URLWithString:url]];
		return YES;
	} else
		return NO;
}
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Make sure your segue name in storyboard is the same as this line
    if ([[segue identifier] isEqualToString:@"Social"])
    {
        // Get reference to the destination view controller
        SocialViewController *vc = [segue destinationViewController];
		NSIndexPath *indexPath=[self.tableView indexPathForCell:sender];
		if (indexPath.row==Contact_Facebook)
			vc.socialType=SocialFeed_Facebook;
		else if (indexPath.row==Contact_LinkedIn)
			vc.socialType=SocialFeed_LinkedIn;
       
    }
}
#pragma mark - UITableViewDataSource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
	return [aryContact count];
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
	
	if (indexPath.row==Contact_Email || indexPath.row==Contact_Phone)
	{
		UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ContactCell"];
		if (cell==nil)
			cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:@"ContactCell"];
			
		[TLConnectHelper tagTableCell:cell withIndexPath:indexPath];
		cell.accessoryType=UITableViewCellAccessoryNone;
		cell.textLabel.text=aryContact[indexPath.row];
		if (indexPath.row==Contact_Phone)
			cell.detailTextLabel.text=[TLConnectHelper contactPhone];
		else
			cell.detailTextLabel.text=[TLConnectHelper contactEmail];
		return cell;
	}
	else
	{
		UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"SocialCell"];
		if (cell==nil)
			cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"SocialCell"];
		
		cell.accessoryType=UITableViewCellAccessoryDisclosureIndicator;
		cell.textLabel.text=aryContact[indexPath.row];
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
#pragma mark - MFMailComposeViewControllerDelegate
-(void)mailComposeController:(MFMailComposeViewController *)controller didFinishWithResult:(MFMailComposeResult)result error:(NSError *)error{
	switch (result) {
		case MFMailComposeResultCancelled:
			NSLog(@"Canceled");
			break;
		case MFMailComposeResultFailed:
			NSLog(@"Failed");
			break;
		case MFMailComposeResultSaved:
			NSLog(@"Saved");
			break;
		case MFMailComposeResultSent:
			NSLog(@"Sent");
			break;
			
		default:
			break;
	}
	[self dismissViewControllerAnimated:YES completion:nil];
}
#pragma mark - UIAlertViewDelegate
- (void)alertView:(UIAlertView *)alertView didDismissWithButtonIndex:(NSInteger)buttonIndex {
	if (buttonIndex!=1)
		return;
	
	UIDevice *device = [UIDevice currentDevice];
	if ([[device model] isEqualToString:@"iPhone"] ) {
		[[UIApplication sharedApplication] openURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@",[TLConnectHelper contactPhone]]]];
	} else {
		UIAlertView *Notpermitted=[[UIAlertView alloc] initWithTitle:@"Alert" message:@"Your device doesn't support this feature." delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
		[Notpermitted show];
	}
}

@end
