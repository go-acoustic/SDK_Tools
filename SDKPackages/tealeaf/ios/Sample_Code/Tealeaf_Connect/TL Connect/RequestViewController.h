//
//  RequestViewController.h
//  TL Connect
//
//  Created by Geoff Heeren on 1/15/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import <UIKit/UIKit.h>

@interface RequestViewController : UIViewController<UITableViewDataSource,UITableViewDelegate>
@property (strong, nonatomic) IBOutlet UITableView *tableView;
@property (nonatomic) UITextField *activeField;
@property (nonatomic) UITableViewCell *activeCell;
-(void)submitForm;
@end
