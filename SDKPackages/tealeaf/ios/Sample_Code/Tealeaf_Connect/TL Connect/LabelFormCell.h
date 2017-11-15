//
//  LabelFormCell.h
//  TL Connect
//
//  Created by Geoff Heeren on 1/24/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import <UIKit/UIKit.h>
@class RequestViewController;
@interface LabelFormCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *lblLabel;
@property (weak, nonatomic) IBOutlet UILabel *lblValue;
@property (nonatomic) NSMutableDictionary	*dataDict;
@property (nonatomic) UITextField *activeField;
@property (weak,nonatomic) RequestViewController *parent;
-(void)loadDataWithDictionary:(NSMutableDictionary*)dict;
@end
