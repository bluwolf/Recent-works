//
//  WolfModel.m
//  ITP342 Final Project
//
//  Created by Xiaohan Chen on 5/8/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "WolfModel.h"

@interface WolfModel ()

@property(strong, nonatomic)NSMutableArray *wolves;
@property(strong, nonatomic)NSString *wolvesPath;





@end

@implementation WolfModel

+(instancetype) sharedModel{
    static WolfModel  *_sharedModel =nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _sharedModel = [[self alloc] init];
    });
    return _sharedModel;
}


//- (instancetype) init
//{
//    
//    self = [super init];
//    if (self) {
//        _wolvesPath = [[NSBundle mainBundle] pathForResource:_wolfType ofType:@"plist" ];
//        _wolves= [NSMutableArray arrayWithContentsOfFile:_wolvesPath];
//        
//        if(_wolves){
//            NSLog(@"wolves Loaded");
//            //            NSDictionary *temp = [self.attractions objectAtIndex:1];
//            //            NSLog(@"%@", temp);
//            
//        }
//        else{
//            NSLog(@"loading wolves failed");
//        }
//        
//        
//    }
//    
//    
//    return self;
//}
-(instancetype)initWithType:(NSString *)message_
{
    self = [super init];
    if (self) {
        self.wolfType = message_;
        _wolvesPath = [[NSBundle mainBundle] pathForResource:_wolfType ofType:@"plist" ];
        _wolves= [NSMutableArray arrayWithContentsOfFile:_wolvesPath];
        
        if(_wolves){
            NSLog(@"wolves Loaded");
            //            NSDictionary *temp = [self.attractions objectAtIndex:1];
            //            NSLog(@"%@", temp);
            
        }
        else{
            NSLog(@"loading wolves failed");
        }
    }
    return self;
}

-(NSString*) renturnWolfType{
    return self.wolfType;
}

- (NSUInteger) numberOfWolves{
    return [self.wolves count];
}

- (NSDictionary *) wolfAtIndex: (NSUInteger) index{
    return [self.wolves objectAtIndex:index];
}

@end
