//
//  DescriptionViewController.m
//  ITP342 Final Project
//
//  Created by Xiaohan Chen on 5/8/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "DescriptionViewController.h"

@interface DescriptionViewController ()

@property (weak, nonatomic) IBOutlet UIImageView *wolfImageView;

@property (weak, nonatomic) IBOutlet UILabel *descriptionTextField;

@end

NSUInteger *wolfId;

@implementation DescriptionViewController

- (void)viewDidLoad {
    [super viewDidLoad];
//    wolfId = (NSUInteger)1;
    NSDictionary * temp= [self.model wolfAtIndex:0];
    NSString *description = temp[@"description"];
    _descriptionTextField.text = description;
    
    
    UIImage *wolfImage = [UIImage imageNamed:temp[@"image"]];
    self.wolfImageView.image = wolfImage;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void) setWolfId: (NSUInteger) index{
    wolfId = &index;
//    NSLog(@"wolfid = %@", wolfId);
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
