//
//  PCollectionViewCell.m
//  Lab6_Los Angeles Attractions
//
//  Created by Xiaohan Chen on 4/24/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "PCollectionViewCell.h"
#import "AttractionsModel.h"

@interface PCollectionViewCell()
//@property (weak, nonatomic) IBOutlet UIImageView *attractionImage;
//@property (weak, nonatomic) IBOutlet UILabel *attractionLabel;
@property (strong, nonatomic) AttractionsModel *model;



@end


@implementation PCollectionViewCell

-(void) setCell:(NSDictionary *) place{
    UIImage *placeImage = [UIImage imageNamed:place[@"image"]];
    self.attractionImage.image = placeImage;
    self.attractionLabel.text = place[@"name"];
    
}


@end

