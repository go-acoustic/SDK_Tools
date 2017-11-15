/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "LocationViewController.h"
#import <CoreLocation/CoreLocation.h>
@interface LocationViewController ()
{
    CLLocation *lastLocation;
}
@property (nonatomic) CLLocationManager *locationManager;
@end

@implementation LocationViewController

- (void)viewDidLoad {
    NSLog(@"Location Service Available: %@",[CLLocationManager locationServicesEnabled]?@"Yes":@"No");
    NSLog(@"Authorization Status: %@",[self getDescriptionFromStatus:[CLLocationManager authorizationStatus]]);
    self.locationManager=[[CLLocationManager alloc] init];
    self.locationManager.distanceFilter=15.0;;
    self.locationManager.delegate=self;
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}
-(void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    if ([CLLocationManager locationServicesEnabled])
    {
        if ([self.locationManager respondsToSelector:@selector(requestAlwaysAuthorization)])
            [self.locationManager requestAlwaysAuthorization];
        else
            [self startLocation];
    }
    NSLog(@"Authorization Status: %@",[self getDescriptionFromStatus:[CLLocationManager authorizationStatus]]);

}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
-(void)startLocation
{
    [self.locationManager startUpdatingLocation];
}
-(NSString*)getDescriptionFromStatus:(CLAuthorizationStatus)status
{
    NSString *descr;
    switch (status) {
        case kCLAuthorizationStatusAuthorizedWhenInUse:
            descr=@"kCLAuthorizationStatusAuthorizedWhenInUse";
            break;
        case kCLAuthorizationStatusDenied:
            descr=@"kCLAuthorizationStatusDenied";
            break;
        case kCLAuthorizationStatusAuthorizedAlways:
            descr=@"kCLAuthorizationStatusAuthorizedAlways";
            break;
            
        case kCLAuthorizationStatusRestricted:
            descr=@"kCLAuthorizationStatusRestricted";
            break;
        case kCLAuthorizationStatusNotDetermined:
        default:
            descr=@"kCLAuthorizationStatusNotDetermined";
            break;
    }
    return descr;
}
-(void)logCLLocation:(CLLocation*)location
{
    //[[TLFCustomEvent sharedInstance] logLocation:location];
    NSLog(@"Latitude: %f ",location.coordinate.latitude);
    NSLog(@"Longitude: %f",location.coordinate.longitude);
    NSLog(@"Altitude: %f meters",location.altitude);
}
#pragma mark CL Location Manager Delegate
/*
 *  locationManager:didUpdateLocations:
 *
 *  Discussion:
 *    Invoked when new locations are available.  Required for delivery of
 *    deferred locations.  If implemented, updates will
 *    not be delivered to locationManager:didUpdateToLocation:fromLocation:
 *
 *    locations is an array of CLLocation objects in chronological order.
 */
- (void)locationManager:(CLLocationManager *)manager didUpdateLocations:(NSArray *)locations {
    
//    if (lastLocation)
//        NSLog(@"Distance Traveled:%f",[lastLocation distanceFromLocation:manager.location]);
    lastLocation=manager.location;
    
    //[self logCLLocation:manager.location];
    
}

/*
 *  locationManager:didRangeBeacons:inRegion:
 *
 *  Discussion:
 *    Invoked when a new set of beacons are available in the specified region.
 *    beacons is an array of CLBeacon objects.
 *    If beacons is empty, it may be assumed no beacons that match the specified region are nearby.
 *    Similarly if a specific beacon no longer appears in beacons, it may be assumed the beacon is no longer received
 *    by the device.
 */
- (void)locationManager:(CLLocationManager *)manager
        didRangeBeacons:(NSArray *)beacons inRegion:(CLBeaconRegion *)region {
    
}

/*
 *  locationManager:rangingBeaconsDidFailForRegion:withError:
 *
 *  Discussion:
 *    Invoked when an error has occurred ranging beacons in a region. Error types are defined in "CLError.h".
 */
- (void)locationManager:(CLLocationManager *)manager
rangingBeaconsDidFailForRegion:(CLBeaconRegion *)region
              withError:(NSError *)error {
    
}

/*
 *  locationManager:didFailWithError:
 *
 *  Discussion:
 *    Invoked when an error has occurred. Error types are defined in "CLError.h".
 */
- (void)locationManager:(CLLocationManager *)manager
       didFailWithError:(NSError *)error
{
    NSLog(@"Location Manager didFailWithErrors:%@",[error localizedDescription]);
}


/*
 *  locationManager:didChangeAuthorizationStatus:
 *
 *  Discussion:
 *    Invoked when the authorization status changes for this application.
 */
- (void)locationManager:(CLLocationManager *)manager didChangeAuthorizationStatus:(CLAuthorizationStatus) status{

    NSLog(@"locationManager didChangeAuthorizationStatus: %@",[self getDescriptionFromStatus:status]);
    if ([manager respondsToSelector:@selector(requestAlwaysAuthorization)] && (status== kCLAuthorizationStatusAuthorized || status== kCLAuthorizationStatusAuthorizedWhenInUse))
    {
       [self startLocation];
    }
    
}



/*
 *  Discussion:
 *    Invoked when location updates are automatically paused.
 */
- (void)locationManagerDidPauseLocationUpdates:(CLLocationManager *)manager
{
    NSLog(@"locationManagerDidPauseLocationUpdates");
}

/*
 *  Discussion:
 *    Invoked when location updates are automatically resumed.
 *
 *    In the event that your application is terminated while suspended, you will
 *	  not receive this notification.
 */
- (void)locationManagerDidResumeLocationUpdates:(CLLocationManager *)manager
{
    NSLog(@"locationManagerDidResumeLocationUpdates");
}
@end
