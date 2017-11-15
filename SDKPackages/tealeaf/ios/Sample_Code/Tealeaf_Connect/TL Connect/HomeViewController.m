//
//  HomeViewController.m
//  TL Connect
//
//  Created by Geoff Heeren on 1/10/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "HomeViewController.h"
#import "FontExplorer.h"

@interface HomeViewController ()
{
    NSMutableArray *tableData;
}
@end

@implementation HomeViewController

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
	[super viewDidLoad];
	//[self.navigationController.navigationBar setBackgroundImage:[UIImage imageNamed:@"TL_Header_Full"] forBarMetrics:UIBarMetricsDefault];
	[self populateTableData];
	NSString *bundleVersion = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleVersion"];
	self.txtBottom.text=[NSString stringWithFormat:@"Version: %@ Framework: %@",bundleVersion,[[TLFApplicationHelper sharedInstance] frameworkVersion]] ;
	self.txtSession.text=[NSString stringWithFormat:@"%@",[[TLFApplicationHelper sharedInstance] currentSessionId]] ;

	// Do any additional setup after loading the view.
}
-(void)viewDidAppear:(BOOL)animated
{
	[super viewDidAppear:animated];

}
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)populateTableData
{
    if (!tableData)
        tableData=[NSMutableArray array];
    
    tableData[0]=@[@"What is Tealeaf?",@"Tealeaf"];
    tableData[1]=@[@"Tealeaf Products",@"ProductList"];
    tableData[2]=@[@"Contact Us",@"ContactUs"];
    tableData[3]=@[@"Request Info",@"RequestForm"];
    tableData[4]=@[@"GeoLocation",@"geoLoc"];
    
}
-(NSString*)getSegueForIndexpath:(NSIndexPath*)indexPath
{
    
    if (indexPath.row<tableData.count)
    {
        NSArray *data=tableData[indexPath.row];
        if (data.count>1)
            return data[1];
    }
    return nil;
}
-(NSString*)getTitleForIndexpath:(NSIndexPath*)indexPath
{
    
    if (indexPath.row<tableData.count)
    {
        NSArray *data=tableData[indexPath.row];
        if (data.count>0)
            return data[0];
    }
    return nil;
}
#pragma mark - UITableViewDelegate
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
	UITableViewCell *cell=[tableView cellForRowAtIndexPath:indexPath];
	cell.selected=NO;
    NSString *segue=[self getSegueForIndexpath:indexPath];
    if (segue)
        [self performSegueWithIdentifier:segue sender:cell];
    else
    {
        NSLog(@"Problem With Table Data");
        return;
	}
	

}

#pragma mark - UITableViewDataSource
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
	return  [tableData count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{

	UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"HomeCell"];
	if (cell==nil)
		cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"HomeCell"];
		
    NSString* title=[self getTitleForIndexpath:indexPath];
    cell.textLabel.text=(title)?title:@"";
	[TLConnectHelper tagTableCell:cell withIndexPath:indexPath];
	cell.accessoryType=UITableViewCellAccessoryDisclosureIndicator;
	cell.accessibilityIdentifier=cell.textLabel.text;
	return cell;
	
}
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
	return 1;
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
	return 46.0;
}
//-(UIView*)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section
//{
//	UIView* theView=[[UIView alloc] initWithFrame:CGRectMake(0, 0, 320, 30)];
//	UILabel *lbl=[[UILabel alloc] initWithFrame:CGRectMake(100, 5, 60, 20)];
//	lbl.text=@"Header";
//	[theView addSubview:lbl];
//	return theView;
//	
//}
//-(UIView*)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section	{
//	UIView* theView=[[UIView alloc] initWithFrame:CGRectMake(0, 0, 320, 30)];
//	UILabel *lbl=[[UILabel alloc] initWithFrame:CGRectMake(100, 5, 60, 20)];
//	lbl.text=@"Footer";
//	[theView addSubview:lbl];
//	return theView;
//}
@end
