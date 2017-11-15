//
//  PickerFormCell.h
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

@interface PickerFormCell : UITableViewCell<UIPickerViewDataSource,UIPickerViewDelegate>
@property (weak, nonatomic) IBOutlet UILabel *lblLabel;
@property (weak, nonatomic) IBOutlet UILabel *lblValue;
@property (weak, nonatomic) IBOutlet UIPickerView *picker;
@property (weak, nonatomic) NSMutableDictionary	*dataDict;
@property (assign,readonly) BOOL pickerIsExanded;
-(void)showPicker;
-(void)hidePicker;
-(void)loadDataWithDictionary:(NSMutableDictionary*)dict;

@end
