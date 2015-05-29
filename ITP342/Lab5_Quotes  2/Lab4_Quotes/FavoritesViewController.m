//
//  FavoritesViewController.m
//  Lab3_Quotes
//
//  Created by Xiaohan Chen on 4/10/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "FavoritesViewController.h"
#import "QuotesModel.h"

@interface FavoritesViewController ()
@property (strong, nonatomic) QuotesModel *model;
@end


@implementation FavoritesViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.model = [QuotesModel sharedModel];

    self.navigationItem.leftBarButtonItem = self.editButtonItem;
    [self.tableView reloadData];
}

- (void)viewDidAppear:(BOOL)animated{

//    [super setEditing:NO animated:NO];
//    [self.tableView reloadData];
}

-(void)viewWillAppear:(BOOL)animated {
    
    [super viewWillAppear:animated];
    
    [self.tableView reloadData];
    
    
}



- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


-(NSInteger) numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}

-(NSInteger) tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [self.model numberOfFavorites];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString * cellIdentifier = @"FavoirtesCells";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    if(cell == nil){
        cell = [[UITableViewCell alloc ] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:cellIdentifier];
    }
    
    cell.textLabel.numberOfLines = 0;;
    NSDictionary * temp= [self.model favoriteAtIndex:indexPath.row];
    
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
        [self.model removeFavoriteAtIndex:indexPath.row];
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    } else if (editingStyle == UITableViewCellEditingStyleInsert) {

    }
}




@end
