/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
#import <UIKit/UIKit.h>

@interface CheckoutDataViewController : UIViewController<UITextFieldDelegate>

@property (nonatomic,weak) IBOutlet UITextField *customerId;
@property (nonatomic,weak) IBOutlet UITextField *customerCity;
@property (nonatomic,weak) IBOutlet UITextField *customerState;
@property (nonatomic,weak) IBOutlet UITextField *customerZip;


-(IBAction)collectData:(id)sender;
-(IBAction)cancel:(id)sender;

@end
