/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2017
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

import UIKit
class DataViewController: UIViewController {
    @IBOutlet weak var dataLabel: UILabel!
    var dataObject: String = ""
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.dataLabel!.text = dataObject
    }
    override func viewDidAppear(_ animated: Bool) {
        if( (self.dataLabel != nil) && (self.dataLabel.text != nil) )
        {
            if( (self.dataLabel.text?.contains("Page View") == true) )
            {
                self.firePageView();
            }
            else if( (self.dataLabel.text?.contains("Product View") == true) )
            {
                self.fireProductView();
            }
            else if( (self.dataLabel.text?.contains("Shop Action5") == true) )
            {
                self.fireShopAction5();
            }
            else if( (self.dataLabel.text?.contains("Shop Action9") == true) )
            {
                self.fireShopAction9();
            }
            else if( (self.dataLabel.text?.contains("Order") == true) )
            {
                self.fireOrder();
            }
            else if( (self.dataLabel.text?.contains("Registration") == true) )
            {
                self.fireRegistration();
            }
            else if( (self.dataLabel.text?.contains("Element") == true) )
            {
                self.fireElement();
            }
            else if( (self.dataLabel.text?.contains("Conversion") == true) )
            {
                self.fireConversion();
            }
            else if( (self.dataLabel.text?.contains("Link") == true) )
            {
                self.fireLink();
            }
            else if( (self.dataLabel.text?.contains("Impression") == true) )
            {
                self.fireImpression();
            }
            else
            {
                NSLog( "Current sessionId: %@", DigitalAnalytics.sessionId() );
                NSLog("Not known page, starting new session")
                DigitalAnalytics.startNewSession();
                NSLog( "New sessionId: %@", DigitalAnalytics.sessionId() );
            }
        }
        else
        {
            NSLog("invalid page, not firing any API")
        }
    }
    func firePageView(){
        let attributesArray: [AnyObject] = ["att1" as AnyObject, "att2" as AnyObject, "att3" as AnyObject];
        let cm_mmcArray: [AnyObject] = ["cm_mmc" as AnyObject];
        
        let bFired = DigitalAnalytics.firePageView("SwiftPageView", category: "SwiftApp", searchTerm: "SwiftSearch", searchResult: "10", attributes: attributesArray, cmmmc: cm_mmcArray)
        if( bFired )
        {
            NSLog("page view fired")
        }
        else
        {
            NSLog("page view NOT fired")
        }
    }
    func fireProductView(){
        let attributesArray: [AnyObject] = ["att1" as AnyObject, "att2" as AnyObject, "att3" as AnyObject];
        
        let bFired = DigitalAnalytics.fireProductview("SwiftProductView", productId: "100", productName: "CameraPhone", category: "SwiftApp", attributes: attributesArray);
        if( bFired )
        {
            NSLog("product view fired")
        }
        else
        {
            NSLog("product view NOT fired")
        }
    }
    func fireShopAction5(){
        let attributesArray: [AnyObject] = ["att1" as AnyObject, "att2" as AnyObject, "att3" as AnyObject];
        
        let bFired = DigitalAnalytics.fireShopAction5("101", productName: "Computer", quantity: "10", baseUnitPrice: "1000", category: "SwiftApp", currencyCode: "1", attributes: attributesArray)
        if( bFired )
        {
            NSLog("shop action5 fired")
        }
        else
        {
            NSLog("shop action5 NOT fired")
        }
    }
    func fireShopAction9(){
        let attributesArray: [AnyObject] = ["att1" as AnyObject, "att2" as AnyObject, "att3" as AnyObject];
        
        let bFired = DigitalAnalytics.fireShopAction9("102", productName: "Laptop", quantity: "11", baseUnitPrice: "1001", category: "Swiftapp", orderId: "100", orderSubTotal: "11011", customerId: "2001", currencyCode: "1", attributes: attributesArray)
        if( bFired )
        {
            NSLog("shop action9 fired")
        }
        else
        {
            NSLog("shop action9 NOT fired")
        }
    }
    func fireOrder(){
        let attributesArray: [AnyObject] = ["att1" as AnyObject, "att2" as AnyObject, "att3" as AnyObject];

        let bFired = DigitalAnalytics.fireOrder("101", subtotal: "11022", shippingCharge: "7", customerId: "3001", customerCity: "San Francisco", customerState: "CA", customerZip: "94085", currencyCode: "1", attributes: attributesArray)
        if( bFired )
        {
            NSLog("order fired")
        }
        else
        {
            NSLog("order NOT fired")
        }
    }
    func fireRegistration(){
        let attributesArray: [AnyObject] = ["att1" as AnyObject, "att2" as AnyObject, "att3" as AnyObject];
        
        let bFired = DigitalAnalytics.fireRegistration("4001", email: "4001@customer4001.com", customerCity: "San Jose", customerState: "CA", customerZip: "94001", customerCountry: "USA", attributes: attributesArray)
        if( bFired )
        {
            NSLog("registration fired")
        }
        else
        {
            NSLog("registration NOT fired")
        }
    }
    func fireElement(){
        let attributesArray: [AnyObject] = ["att1" as AnyObject, "att2" as AnyObject, "att3" as AnyObject];
        
        let bFired = DigitalAnalytics.fireElement("500", elementCategory: "SwiftAppElements", attributes: attributesArray)
        if( bFired )
        {
            NSLog("element fired")
        }
        else
        {
            NSLog("element NOT fired")
        }
    }
    func fireConversion(){
        let attributesArray: [AnyObject] = ["att1" as AnyObject, "att2" as AnyObject, "att3" as AnyObject];
        
        let bFired = DigitalAnalytics.fireConversionEvent("501", actionType: "1", eventCategory: "SwiftAppEvent", eventPoints: "10", attributes: attributesArray)
        if( bFired )
        {
            NSLog("conversion fired")
        }
        else
        {
            NSLog("conversion NOT fired")
        }
    }
    func fireLink(){
        let bFired = DigitalAnalytics.fireLinkClick("1002", linkName: "TheDALink", link: "www.ibm.com")
        if( bFired )
        {
            NSLog("link fired")
        }
        else
        {
            NSLog("link NOT fired")
        }
    }
    func fireImpression(){
        let cmsp: [AnyObject] = ["cmsp1" as AnyObject];
        let cmre: [AnyObject] = ["cmre1" as AnyObject];
        let bFired = DigitalAnalytics.fireImpression("1003", cmsp: cmsp, cmre: cmre)
        if( bFired )
        {
            NSLog("impression fired")
        }
        else
        {
            NSLog("impression NOT fired")
        }
    }
}
