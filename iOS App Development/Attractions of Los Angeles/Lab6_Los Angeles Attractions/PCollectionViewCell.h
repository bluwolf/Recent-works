//
//  PCollectionViewCell.h
//  Lab6_Los Angeles Attractions
//
//  Created by Xiaohan Chen on 4/24/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PCollectionViewCell : UICollectionViewCell
@property (weak, nonatomic) IBOutlet UIImageView *attractionImage;
@property (weak, nonatomic) IBOutlet UILabel *attractionLabel;
-(void) setCell:(NSDictionary *)place;

@end
