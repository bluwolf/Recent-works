//
//  WolfSelectionViewController.m
//  ITP342 Final Project
//
//  Created by Xiaohan Chen on 5/8/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "WolfSelectionViewController.h"
#import "SelectAWolfTableViewController.h"
#import "WolfModel.h"

@interface WolfSelectionViewController ()
@property (weak, nonatomic) IBOutlet UISegmentedControl *typeSelectionSegment;
@property (weak, nonatomic) IBOutlet UIButton *goButton;
@property (strong, nonatomic) WolfModel *model;
@property(strong, nonatomic)NSString *wolfType;

@property(strong, nonatomic)NSMutableArray *wolfArray;

@end

@implementation WolfSelectionViewController

- (void)viewDidLoad {
    [super viewDidLoad];
   _wolfType = @"lupus";
    // Do any additional setup after loading the view.
   
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];

}

- (IBAction)typeSelectionSegmentChanged:(id)sender {
    switch ([sender selectedSegmentIndex]) {
        case 0:
            _wolfType = @"lupus";
            break;
        case 1:
             _wolfType = @"rufus";
            break;
        case 2:
             _wolfType = @"lycaon";
            
        default:
            break;
    }
    
}
- (IBAction)goButtonClicked:(id)sender {
    self.model.wolfType = _wolfType;
    NSLog(@"wolf type is %@", self.model.wolfType);
    self.model = [[WolfModel alloc] initWithType: _wolfType];
    
    NSLog(@"Number of wolves : %lu", (unsigned long)[self.model numberOfWolves]);
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if([segue.identifier isEqualToString:@"showDetailSegue"]){
//        NSLog(@"segue valid");
        SelectAWolfTableViewController *controller = (SelectAWolfTableViewController *)segue.destinationViewController;
        controller.model = self.model;
    }
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
