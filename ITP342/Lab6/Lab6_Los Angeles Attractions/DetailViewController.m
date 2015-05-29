//
//  DetailViewController.m
//  Lab6_Los Angeles Attractions
//
//  Created by Xiaohan Chen on 4/24/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "DetailViewController.h"
#import "PCollectionViewCell.h"


@interface DetailViewController ()

@property (strong, nonatomic) NSDictionary *attraction;

@property (weak, nonatomic) IBOutlet UIWebView *websiteView;


@end

@implementation DetailViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    self.title = self.attraction[@"name"];
    NSURL *url = [NSURL URLWithString:self.attraction[@"website"]];
     NSLog(@"weburl: %@", url);
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    [_websiteView loadRequest:request];
    [_websiteView setScalesPageToFit:YES];
//    [[self websiteView] addSubview:websiteView];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void) setAttraction:(NSDictionary *)attraction{
    _attraction = attraction;
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
