//
//  QuotesModel.h
//  Lab4_Quotes
//
//  Created by Student on 2/25/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface QuotesModel : NSObject

//public methods
- (NSDictionary *) randomQuote;
+ (instancetype) sharedModel;
- (NSUInteger) numberOfQuotes;
- (NSDictionary *) quoteAtIndex: (NSUInteger) index;
- (void) removeQuoteAtIndex: (NSUInteger) index;
- (void) insertQuote: (NSString *) quote
              author: (NSString *) author
             atIndex: (NSUInteger) index;
- (void) insertQuote: (NSDictionary *) quote
             atIndex: (NSUInteger) index;
- (NSDictionary *) nextQuote; - (NSDictionary *) prevQuote;
- (void) removeFavoriteAtIndex: (NSUInteger) index;
- (NSUInteger) numberOfFavorites;
- (void) insertFavorite: (NSString *) quote author: (NSString *) author atIndex: (NSUInteger) index;
- (void) insertFavorite: (NSDictionary *) quote atIndex: (NSUInteger) index;
- (NSDictionary *) favoriteAtIndex: (NSUInteger) index;
-(void) addQuoteToFavorites;
-(BOOL) favoritesContainsQuote: (NSDictionary *) currentQuote;

@end
