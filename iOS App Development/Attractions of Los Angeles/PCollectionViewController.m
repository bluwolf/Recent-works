//
//  PCollectionViewController.m
//  Lab6_Los Angeles Attractions
//
//  Created by Xiaohan Chen on 4/24/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "PCollectionViewController.h"
#import "AttractionsModel.h"
#import "PCollectionViewCell.h"
#import "DetailViewController.h"

@interface PCollectionViewController()
@property (strong, nonatomic) AttractionsModel *model;
//@property (weak, nonatomic) IBOutlet UIImageView *attractionImage;
//@property (weak, nonatomic) IBOutlet UILabel *attractionLabel;

@end


@implementation PCollectionViewController

-(void) viewDidLoad{
    [super viewDidLoad];
    self.model = [[AttractionsModel alloc] init];

    NSLog(@"Number of places : %lu", (unsigned long)[self.model numberOfAttractions]);

    
    
//                NSDictionary *temp = [self.model attractionAtIndex:0];
//                NSLog(@"%@", temp);

}

-(NSInteger) numberOfSectionsInCollectionView:(UICollectionView *)collectionView{
    return 1;
}

- (NSInteger) collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
//    NSLog(@"number of attractions :%ld", (long)[self.model numberOfAttractions]);
    return [self.model numberOfAttractions];
}



- (UICollectionViewCell *) collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    
    
    PCollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"Place" forIndexPath:indexPath];
    NSLog(@"%ld", (long)indexPath.row);
    
    NSDictionary *attraction = [self.model attractionAtIndex:indexPath.row];
//    NSDictionary *attraction = [self.model attractionAtIndex:1];
    cell.backgroundColor = [UIColor whiteColor];
    
//    NSString *imageName = [attraction valueForKey:@"image"];
//    cell.attractionImage.image = [UIImage imageNamed:imageName];
    
    
//    UIImage *placeImage = [UIImage imageNamed:attraction[@"image"]];
//    cell.attractionImage.image = placeImage;
//    cell.attractionLabel.text = attraction[@"name"];
//    cell.attractionLabel.text = @"@hi";
    

    [cell setCell:attraction];
    
    return cell;
    
}

- (void) willRotateToInterfaceOrientation:(UIInterfaceOrientation)toInterfaceOrientation duration:(NSTimeInterval)duration{
    UICollectionViewFlowLayout *layout = (UICollectionViewFlowLayout *) [self.collectionView collectionViewLayout];
    if (toInterfaceOrientation == UIInterfaceOrientationLandscapeLeft || toInterfaceOrientation ==UIInterfaceOrientationLandscapeRight) {
        layout.scrollDirection = UICollectionViewScrollDirectionHorizontal;
    } else {
        layout.scrollDirection = UICollectionViewScrollDirectionVertical;
    }
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    DetailViewController *detailVC = (DetailViewController *)segue.destinationViewController;
    
    NSIndexPath *indexPath = self.collectionView.indexPathsForSelectedItems[0];
    NSDictionary *attraction = [self.model attractionAtIndex:indexPath.row];
    [detailVC setAttraction:attraction];
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}

@end
