//
//  DataModel.h
//  Lab3_DataModel
//
//  Created by Xiaohan Chen on 2/11/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface DataModel : NSObject

@property (strong, nonatomic) NSString *secretAnser;

- (NSDictionary *) randomQuote;
- (NSUInteger) numberOfQuotes;
- (NSDictionary *) quoteAtIndex: (NSUInteger) index;
- (void) removeQuoteAtIndex: (NSUInteger) index;
- (void) insertQuote: (NSString *) quote
author: (NSString *) author
             atIndex: (NSUInteger) index;
- (void) insertQuote: (NSDictionary *) quote
             atIndex: (NSUInteger) index;
- (NSDictionary *) nextQuote; - (NSDictionary *) prevQuote;


@end
