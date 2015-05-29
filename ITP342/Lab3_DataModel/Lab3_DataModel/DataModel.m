//
//  DataModel.m
//  Lab3_DataModel
//
//  Created by Xiaohan Chen on 2/11/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "DataModel.h"

// class extension
@interface DataModel ()

//private property
@property (strong,nonatomic) NSMutableArray *quotes;


@end




@implementation DataModel

- (instancetype)init
{
    self = [super init];
    if (self) {
        <#statements#>
    }
    return self;
}

- (NSDictionary *) randomQuote{
    
}

- (NSUInteger) numberOfQuotes{
    
}

- (NSDictionary *) quoteAtIndex: (NSUInteger) index{
    
}

- (void) removeQuoteAtIndex: (NSUInteger) index{
    
}

- (void) insertQuote: (NSString *) quote
              author: (NSString *) author
             atIndex: (NSUInteger) index{
    
}

- (void) insertQuote: (NSDictionary *) quote
             atIndex: (NSUInteger) index{
    
}

- (NSDictionary *) nextQuote; - (NSDictionary *) prevQuote{
    
}














- (instancetype) init{
    self = [super init];
    if(self){
        _names =[[NSMutableArray alloc ]
                 initWithObjects:@"Janelle", @"Kristen", @"Chen",nil];
        
    }
}

-(NSString *) randomName{
    NSUInteger num = random() % [self numberOfNames];
    return self.names[num];
}

-(NSString*) nameAtIndex: (NSUinteger) index{
    return [self.names objecAtIndex: index];
}

-(void) insertName: (NSString*) name atIndex: (NSUInteger) index{
    if (index<= self.name.count) {
        if(index==0){
            self.currentIndex=0;
        }else{
            self.currentIndex=index=1;
        }
        [self.names removeObjectAtIndex: index];
    }
    
}

-(void) removeNameAtIndex: (NSUInteger) index{
    if (index < self.names.count) {
        [self.names removeObjectAtIndex: index];
    }
}

@end
