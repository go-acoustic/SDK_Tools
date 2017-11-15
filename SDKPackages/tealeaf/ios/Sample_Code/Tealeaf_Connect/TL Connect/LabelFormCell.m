//
//  LabelFormCell.m
//  TL Connect
//
//  Created by Geoff Heeren on 1/24/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "LabelFormCell.h"
#import "RequestViewController.h"


@implementation LabelFormCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}
-(void)loadDataWithDictionary:(NSMutableDictionary*)dict
{
	self.lblLabel.text=dict[kRV_Key_Label];
	self.lblValue.text=dict[kRV_Key_Value];
	self.dataDict=dict;

}

@end
