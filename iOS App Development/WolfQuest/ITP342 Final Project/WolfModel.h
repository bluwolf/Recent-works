//
//  WolfModel.h
//  ITP342 Final Project
//
//  Created by Xiaohan Chen on 5/8/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface WolfModel : NSObject

+ (instancetype) sharedModel;
@property(strong, nonatomic)NSString *wolfType;
-(NSString*) renturnWolfType;
- (NSUInteger) numberOfWolves;
-(instancetype)initWithType:(NSString *)message_;

- (NSDictionary *) wolfAtIndex: (NSUInteger) index;


@end
