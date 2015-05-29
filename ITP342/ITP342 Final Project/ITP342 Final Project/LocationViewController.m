//
//  LocationViewController.m
//  ITP342 Final Project
//
//  Created by Xiaohan Chen on 5/8/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "LocationViewController.h"
#import <Foundation/Foundation.h>
#import <MapKit/MapKit.h>
#import <CoreLocation/CoreLocation.h>

@interface LocationViewController ()
@property (strong, nonatomic) IBOutlet UIView *locationMapView;
@property (weak, nonatomic) IBOutlet MKMapView *myMapView;

@end




@implementation LocationViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    CLLocationCoordinate2D location;
    location.latitude = 37.250556;
    location.longitude = -96.358333;
    
    MKCoordinateSpan span;
    span.latitudeDelta = 1.04*(126.766667 - 66.95) ;
    span.longitudeDelta = 1.04*(49.384472 - 24.520833) ;
    
    MKCoordinateRegion region;
    region.span = span;
    region.center = location;
    
    
    
    [_myMapView setRegion:region animated:NO];
    [_myMapView regionThatFits:region];
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

@end
