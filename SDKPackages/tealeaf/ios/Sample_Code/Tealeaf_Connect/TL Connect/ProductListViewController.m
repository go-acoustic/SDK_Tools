//
//  ProductListViewController.m
//  TL Connect
//
//  Created by Geoff Heeren on 1/13/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "ProductListViewController.h"
#import "ProductDetailViewController.h"
#import "Product.h"
@interface ProductListViewController ()
{
	NSArray *aryProducts;
}
@end

@implementation ProductListViewController

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
	aryProducts=[TLConnectHelper getProducts];
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
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Make sure your segue name in storyboard is the same as this line
    if ([[segue identifier] isEqualToString:@"ProductDetail"])
    {
        // Get reference to the destination view controller
        ProductDetailViewController *vc = [segue destinationViewController];
		NSIndexPath *indexPath=[self.tableView indexPathForCell:sender];
		switch (indexPath.row) {
			case 1:
				vc.productType=TLProduct_Mobile;
				break;
			case 2:
				vc.productType=TLProduct_Impact;
				break;
			case 3:
				vc.productType=TLProduct_Overstat;
				break;
			case 4:
				vc.productType=TLProduct_Reveal;
				break;
			case 0:
			default:
				vc.productType=TLProduct_CX;
				break;
		}
		
    }
}
#pragma mark - UITableViewDelegate
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
	UITableViewCell *cell=[tableView cellForRowAtIndexPath:indexPath];
	cell.selected=NO;
	NSString *segue=@"ProductDetail";
	[self performSegueWithIdentifier:segue sender:cell];
	
}
#pragma mark - UITableViewDataSource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
	return [aryProducts count];
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
	
	UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"HomeCell"];
	if (cell==nil)
		cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:@"HomeCell"];
	Product *prod=aryProducts[indexPath.row];

	[TLConnectHelper tagTableCell:cell withIndexPath:indexPath];
	cell.textLabel.text=prod.name;
	cell.detailTextLabel.text=prod.subtitle;
	cell.accessoryType=UITableViewCellAccessoryDisclosureIndicator;
	
	return cell;
	
}
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
	return 1;
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
	return ROW_HEIGHT;
}
@end
