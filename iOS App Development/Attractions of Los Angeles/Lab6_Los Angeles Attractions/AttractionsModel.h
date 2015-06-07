//
//  AttractionsModel.h
//  Lab6_Los Angeles Attractions
//
//  Created by Xiaohan Chen on 4/24/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface AttractionsModel : NSObject

+ (instancetype) sharedModel;
- (NSUInteger) numberOfAttractions;
- (NSDictionary *) attractionAtIndex: (NSUInteger) index;

@end
