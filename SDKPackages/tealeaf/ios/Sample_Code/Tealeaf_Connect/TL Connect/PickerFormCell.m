//
//  PickerFormCell.m
//  TL Connect
//
//  Created by Geoff Heeren on 1/15/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "PickerFormCell.h"
@interface PickerFormCell ()
{
	NSArray *aryList;
}
@end
@implementation PickerFormCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
	NSLog(@"initWithStyle");
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
       [self setUp];
    }
    return self;
}
-(id)initWithFrame:(CGRect)frame
{
	NSLog(@"initWithFrame");
	self=[super initWithFrame:frame];
	if(self)
	{
		[self setUp];
	}
	return self;
}
-(id)initWithCoder:(NSCoder *)aDecoder
{
	NSLog(@"initWithCoder");
	self=[super initWithCoder:aDecoder];
	if(self)
	{
		[self setUp];
	}
	return self;
}
-(void)loadDataWithDictionary:(NSMutableDictionary*)dict{
	self.lblLabel.text=dict[kRV_Key_Label];
	self.lblValue.text=dict[kRV_Key_Value];
	self.picker.dataSource=self;
	self.picker.delegate=self;
	self.dataDict=dict;
	aryList=self.dataDict[kRV_Key_List];
}
//-(void)adjustPickerToMatchText
//{
//	NSUInteger index = [aryList indexOfObjectIdenticalTo:self.lblValue.text];
//	NSLog(@"index of %@:%ul",self.lblValue.text,(unsigned long)index);
//	if (index>0 && index < [aryList count])
//	{
//		[self.picker selectRow:index inComponent:1 animated:NO];
//	}
//}
-(void)setUp
{
	self.picker.delegate=self;
	self.picker.dataSource=self;
}
- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}
-(void)showPicker{
	self.lblLabel.hidden=YES;
	self.lblValue.hidden=YES;
	self.picker.hidden=NO;
	NSUInteger index = [aryList indexOfObject:self.lblValue.text];
	//NSLog(@"index of %@:%lu",self.lblValue.text,(unsigned long)index);
	if (index>0 && index < [aryList count])
	{
		[self.picker selectRow:index inComponent:0 animated:NO];
	}
}
-(void)hidePicker{
	self.lblLabel.hidden=NO;
	self.lblValue.hidden=NO;
	self.picker.hidden=YES;
}

#pragma mark - UIPicker methods
-(NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component
{
	return [self.dataDict[kRV_Key_List] count];
}
-(NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView
{
	return 1;
}
- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component
{
	return self.dataDict[kRV_Key_List][row];
}
- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component
{
	self.dataDict[kRV_Key_Value]=self.dataDict[kRV_Key_List][row];
}
//- (CGFloat)pickerView:(UIPickerView *)pickerView rowHeightForComponent:(NSInteger)component
//{
//	return ROW_HEIGHT;
//}
//-(CGFloat)pickerView:(UIPickerView *)pickerView widthForComponent:(NSInteger)component
//{
//	return 320.0;
//}
@end
