/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
#import "CheckoutDataViewController.h"
#import "ShoppingCart.h"
#import "TagOrderCompletion.h"

@interface CheckoutDataViewController ()

@end

@implementation CheckoutDataViewController

@synthesize customerId;
@synthesize customerCity;
@synthesize customerState;
@synthesize customerZip;

- (void)viewDidLoad {
    [super viewDidLoad];
      // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Navigation
/*
// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

-(IBAction)collectData:(id)sender
{
    NSString *customerIdStr = self.customerId.text;
    NSString *customerCityStr = self.customerCity.text;
    NSString *customerStateStr = self.customerState.text;
    NSString *customerZipStr = self.customerZip.text;
    
    ShoppingCart *cart = [ShoppingCart instance];
    Order *order = [cart generateOrder];
    order.customerId = customerIdStr;
    order.customerCity = customerCityStr;
    order.customerState = customerStateStr;
    order.customerZip = customerZipStr;
    
    //TAGGING: Tag the successful placement of an Order and corresponding ShopAction9 Tags
    NSString *pageName = NSStringFromClass([self class]);
    id<Tag> orderCompletionTag = [[TagOrderCompletion alloc] initTag:pageName shoppingCart:cart order:order];
    [orderCompletionTag executeTag];
    
    //Add a slight delay and then re-direct back for a brand new session
    int duration = 2; // duration in seconds
    
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, duration * NSEC_PER_SEC), dispatch_get_main_queue(), ^{
        
        [self dismissViewControllerAnimated:NO completion:^{
            [[NSNotificationCenter defaultCenter] postNotificationName:@"gohome" object:self userInfo:nil];
        }];
        
    });
}

-(IBAction)cancel:(id)sender
{
    [self dismissViewControllerAnimated:NO completion:nil];
}

#pragma - mark TextField Delegate Methods
-(BOOL) textFieldShouldBeginEditing:(UITextField *)textField
{
    return YES;
}

-(BOOL)textFieldShouldReturn:(UITextField *)textField
{
    [textField resignFirstResponder];
    return YES;
}
@end
