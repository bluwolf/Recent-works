//
//  SelectAWolfTableViewController.h
//  ITP342 Final Project
//
//  Created by Xiaohan Chen on 5/8/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "WolfModel.h"

@interface SelectAWolfTableViewController : UITableViewController
@property (strong, nonatomic) WolfModel *model;
-(void) setWolfId: (NSUInteger) index;
@end
