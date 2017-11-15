//
//  Licensed Materials - Property of IBM
//  (C) Copyright IBM Corp. 2016
//  US Government Users Restricted Rights - Use, duplication or disclosure
//  restricted by GSA ADP Schedule Contract with IBM Corp.
//

#import "StartViewController.h"

@interface StartViewController ()
- (IBAction)btnSingleWebView:(id)sender;
- (IBAction)btnDoubleView:(id)sender;

@end

@implementation StartViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    // Turn off screen shots
    //[[TLFCustomEvent sharedInstance] logPrintScreenEvent];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (IBAction)btnSingleWebView:(id)sender {
	[self performSegueWithIdentifier:@"singleWebView" sender:sender];
}
- (IBAction)btnDoubleView:(id)sender {
	[self performSegueWithIdentifier:@"doubleWebView" sender:sender];
}
@end
