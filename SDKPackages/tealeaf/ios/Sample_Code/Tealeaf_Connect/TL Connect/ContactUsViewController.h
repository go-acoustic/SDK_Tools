//
//  ContactUsViewController.h
//  TL Connect
//
//  Created by Geoff Heeren on 1/13/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import <UIKit/UIKit.h>
#import <MessageUI/MessageUI.h>
@interface ContactUsViewController : UIViewController<UITableViewDelegate,UITableViewDataSource,MFMailComposeViewControllerDelegate,UIAlertViewDelegate>
@property (weak, nonatomic) IBOutlet UITableView *tableView;

@end
