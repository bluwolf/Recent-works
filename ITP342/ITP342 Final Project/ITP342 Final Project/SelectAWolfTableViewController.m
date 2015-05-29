//
//  SelectAWolfTableViewController.m
//  ITP342 Final Project
//
//  Created by Xiaohan Chen on 5/8/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "SelectAWolfTableViewController.h"
#import "ThreeScenesTabBarViewController.h"
#import "DescriptionViewController.h"
#import "WolfModel.h"

@interface SelectAWolfTableViewController ()


@end

@implementation SelectAWolfTableViewController

- (void)viewDidLoad {
    [super viewDidLoad];
//    self.model = [[WolfModel alloc] initWithType: self.model.renturnWolfType];
//     NSLog(@"I'm in viewdidLoad of table view %@", self.model.renturnWolfType);
    
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
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
    return [self.model numberOfWolves];
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString * cellIdentifier = @"aCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    if(cell == nil){
        //        cell = [[UITableViewCell alloc ] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:cellIdentifier];
        cell = [[UITableViewCell alloc ] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:cellIdentifier];
    }
    
//    cell.textLabel.numberOfLines = 0;;
    NSDictionary * temp= [self.model wolfAtIndex:indexPath.row];
    NSString *wolfName = temp[@"name"];
//    NSLog(@"I'm here");
    
//    NSArray* author=[temp allKeys];
//    NSArray* quote = [temp allValues];
//    NSString * aString = [author objectAtIndex:0];
//    NSString * qString = [quote objectAtIndex:0];
    
    
//    cell.detailTextLabel.text = aString;
    cell.textLabel.text = wolfName;
    return cell;
}


-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if([segue.identifier isEqualToString:@"pushToTabViewSegue"]){
                NSLog(@"tab view segue valid");
        ThreeScenesTabBarViewController *controller = (ThreeScenesTabBarViewController *)segue.destinationViewController;
        DescriptionViewController *destinationViewController = controller.viewControllers[0];
     
        destinationViewController.model = self.model;
        NSUInteger temp = [self.tableView indexPathForSelectedRow].row;
        [destinationViewController setWolfId:temp];
    }
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
