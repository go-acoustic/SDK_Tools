/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
#import "CheckoutController.h"
#import "ShoppingCart.h"

@interface CheckoutController ()

@property (nonatomic,strong) NSArray *cartItems;

@end

@implementation CheckoutController

@synthesize cartItems;

- (void)viewDidLoad {
    [super viewDidLoad];
    
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
    
    ShoppingCart *cart = [ShoppingCart instance];
    self.cartItems = [cart.products allObjects];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    // Return the number of rows in the section.
    return [self.cartItems count]+1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CartItemCell" forIndexPath:indexPath];
    NSUInteger row = indexPath.row;
    
    if(row == [self.cartItems count])
    {
        ShoppingCart *cart = [ShoppingCart instance];
        NSString *subtotal = [NSString stringWithFormat:@"%0.2f",[cart calculateSubtotal]];
        cell.textLabel.text = [NSString stringWithFormat:@"Subtotal: $%@",subtotal];
    }
    else
    {
        //Get the Product that was added to the shopping cart
        Product *cartItem = [self.cartItems objectAtIndex:row];
    
        UILabel *quantityLabel = [[UILabel alloc]initWithFrame:CGRectMake(20,-20,50,80)];
        UILabel *nameLabel = [[UILabel alloc]initWithFrame:CGRectMake(80,-20,100,80)];
        UILabel *priceLabel = [[UILabel alloc]initWithFrame:CGRectMake(200,-20,100,80)];
    
        quantityLabel.text = [NSString stringWithFormat:@"%d",cartItem.quantity];
        nameLabel.text = cartItem.name;
        float totalPrice = [cartItem totalPrice];
        NSString *totalPriceStr = [NSString stringWithFormat:@"%0.2f",totalPrice];
        priceLabel.text = [NSString stringWithFormat:@"$%@", totalPriceStr];
    
        [cell.contentView addSubview:quantityLabel];
        [cell.contentView addSubview:nameLabel];
        [cell.contentView addSubview:priceLabel];
    }
    
    return cell;
}

/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/

/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    } else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/

/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath {
}
*/

/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
