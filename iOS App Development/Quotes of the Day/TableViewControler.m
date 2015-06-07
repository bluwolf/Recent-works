//
//  TableViewControler.m
//  Lab3_Quotes
//
//  Created by Xiaohan Chen on 3/20/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "TableViewControler.h"
#import "QuotesViewController.h"
#import "QuotesModel.h"
#import "AddQuoteViewController.h"


@interface TableViewControler ()
@property (strong, nonatomic) QuotesModel *model;
@end


@implementation TableViewControler


- (void)viewDidLoad {
    [super viewDidLoad];
//    self.model = [QuotesModel sharedModel];
    self.model = [[QuotesModel alloc] init];

    self.navigationItem.leftBarButtonItem = self.editButtonItem;
}






-(NSInteger) numberOfSectionsInTableView:(UITableView *)tableView{
     return 1;
}

-(NSInteger) tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [self.model numberOfQuotes];
}



- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString * cellIdentifier = @"aCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    if(cell == nil){
//        cell = [[UITableViewCell alloc ] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:cellIdentifier];
        cell = [[UITableViewCell alloc ] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:cellIdentifier];
    }

    cell.textLabel.numberOfLines = 0;;
    NSDictionary * temp= [self.model quoteAtIndex:indexPath.row];
    
    NSArray* author=[temp allKeys];
    NSArray* quote = [temp allValues];
    NSString * aString = [author objectAtIndex:0];
    NSString * qString = [quote objectAtIndex:0];

    
    cell.detailTextLabel.text = aString;
    cell.textLabel.text = qString;
    
    return cell;
}



- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        [self.model removeQuoteAtIndex:indexPath.row];
        
       
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    } else if (editingStyle == UITableViewCellEditingStyleInsert) {

    }
}

- (void) prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    
   AddQuoteViewController *inputVC = segue.destinationViewController;
    inputVC.completionHandler = ^(NSString *quoteText, NSString *authorText){
        if(quoteText != nil){

  
            
            [self.model insertQuote:quoteText author:authorText atIndex:0];
            
            NSIndexPath  *indexPath = [NSIndexPath indexPathForRow:0 inSection:0];
            [self.tableView insertRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
            
        }
        [self dismissViewControllerAnimated:YES completion:nil];
    };
}



@end
