//
//  SimpleRequestViewController.h
//  TL Connect
//
//  Created by Geoff Heeren on 1/27/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import <UIKit/UIKit.h>
@interface SimpleRequestViewController : UIViewController<UITextFieldDelegate,UIAlertViewDelegate>
@property (weak, nonatomic) IBOutlet UITextField *txtFirstName;
@property (weak, nonatomic) IBOutlet UITextField *txtLastName;
@property (weak, nonatomic) IBOutlet UITextField *txtEmail;
@property (weak, nonatomic) IBOutlet UITextField *txtPhone;
@property (weak, nonatomic) IBOutlet UITextField *txtCompany;
@property (weak, nonatomic) IBOutlet UILabel *lblValueJobTitle;
@property (weak, nonatomic) IBOutlet UILabel *lblValueJobRole;
@property (weak, nonatomic) IBOutlet UILabel *lblValueIndustry;
@property (weak, nonatomic) IBOutlet UILabel *lblValueCountry;

@property (weak, nonatomic) IBOutlet UILabel *lblValueState;


- (IBAction)btnSubMenuClick:(id)sender;

- (IBAction)btnSubmitFormClick:(id)sender;
@end
