//
//  ButtonFormCell.h
//  TL Connect
//
//  Created by Geoff Heeren on 1/26/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import <UIKit/UIKit.h>
@class RequestViewController;
@interface ButtonFormCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UIButton *btnSubmit;
@property (weak,nonatomic) RequestViewController *parent;
- (IBAction)btnPressed:(id)sender;

@end
