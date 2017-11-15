//
//  TextFormCell.h
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
@class RequestViewController;
@interface TextFormCell : UITableViewCell<UITextFieldDelegate>
@property (weak, nonatomic) IBOutlet UILabel *lblLabel;
@property (weak, nonatomic) IBOutlet UITextField *txtText;
@property (weak, nonatomic) NSMutableDictionary	*dataDict;
@property (weak, nonatomic) UITextField *activeField;
@property (weak,nonatomic) RequestViewController *parent;
-(void)loadDataWithDictionary:(NSMutableDictionary*)dict;
@end
