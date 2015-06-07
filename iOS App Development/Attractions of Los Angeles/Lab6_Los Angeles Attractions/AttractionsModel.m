//
//  AttractionsModel.m
//  Lab6_Los Angeles Attractions
//
//  Created by Xiaohan Chen on 4/24/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "AttractionsModel.h"

@interface AttractionsModel ()

@property(strong, nonatomic)NSMutableArray *attractions;
@property(strong, nonatomic)NSString *attractionsPath;


@end


NSString *const NameKey = @"name";
NSString *const WebsiteKey = @"website";
NSString *const ImageKey = @"image";

//NSString *const PlacesPlist = @"places.plist";


@implementation AttractionsModel

+(instancetype) sharedModel{
    static AttractionsModel  *_sharedModel =nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _sharedModel = [[self alloc] init];
    });
    return _sharedModel;
}

- (instancetype) init
{
    
    self = [super init];
    if (self) {
        _attractionsPath = [[NSBundle mainBundle] pathForResource:@"attractions" ofType:@"plist" ];
        _attractions = [NSMutableArray arrayWithContentsOfFile:_attractionsPath];
    
        if(_attractions){
            NSLog(@"attractions Loaded");
//            NSDictionary *temp = [self.attractions objectAtIndex:1];
//            NSLog(@"%@", temp);
            
        }
        else{
            NSLog(@"loading attractions failed");
        }
    
        
    }

    
    return self;
}

- (NSUInteger) numberOfAttractions{
    return [self.attractions count];
}

- (NSDictionary *) attractionAtIndex: (NSUInteger) index{
    return [self.attractions objectAtIndex:index];
}



@end
