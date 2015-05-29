//
//  QuotesModel.m
//  Lab4_Quotes
//
//  Created by Student on 2/25/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "QuotesModel.h"

// class extension
@interface QuotesModel ()
// private properties
//@property (strong, nonatomic) NSArray *quotes;

@property (strong, nonatomic) NSMutableArray *mutableQuotes;
@property (strong, nonatomic) NSDictionary *dictionaryObject1;
@property (strong, nonatomic) NSDictionary *dictionaryObject2;
@property (strong, nonatomic) NSDictionary *dictionaryObject3;
@property (assign) NSUInteger currentIndex;


@end



@implementation QuotesModel
- (instancetype)init
{
    self = [super init];
    if (self) {
//        _quotes = [[NSMutableArray alloc] initWithObjects:
//                   @"The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart.",
//                   @"Start by doing what's necessary; then do wht's possible; and suddenly you are doing the impossible.",
//                   @"Perfection is not attainable, but if we chase perfection we can catch excellence."
//                   ,
//                   @"I can't change the direction of the wind, but I  can adjust my sails to always reach my destination",
//                   @"Nothing is impossible, the lord itself says 'I'm possible!'",
//                   @"Put your heart, mind, and soul into even your smallest acts. This is the secret of success.",
//                   @"Change your thoughts and you change your world.",
//                   @"We can't help everyone, but everyone can help someone.",
//                   @"The meansure of who we are is what we do with what we have.",
//                   @"Keep your face always toward the sunshine- and shadows will fall behind you.",
//                   nil];
//        _authors = [[NSMutableArray alloc] initWithObjects:
//                   @"Helen Keller",
//                   @"Francis of Assisi",
//                   @"Vince Lombardi",
//                   @"Jimmy Dean",
//                   @"Audrey Hepburn",
//                   @"Swami Sivananda",
//                   @"Norman Vincent Peale",
//                   @"Ronald Reagan",
//                   @"Vince Lombardi",
//                   @"Walt Whitman",
//                    nil];
//        _dictionaryObjects = [NSMutableDictionary
//                             dictionaryWithObjects: _quotes
//                             forKeys:_authors];
        
        
        _dictionaryObject1 = [NSDictionary dictionaryWithObjectsAndKeys:
                   @"The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart.", @"Helen Keller", nil];
        _dictionaryObject2 = [NSDictionary dictionaryWithObjectsAndKeys:
                              @"Start by doing what's necessary; then do wht's possible; and suddenly you are doing the impossible.", @"Francis of Assisi", nil];
        _dictionaryObject3 = [NSDictionary dictionaryWithObjectsAndKeys:
                              @"Keep your face always toward the sunshine- and shadows will fall behind you.", @"Walt Whitman", nil];
       
        
        _mutableQuotes = [ [NSMutableArray alloc] initWithObjects: _dictionaryObject1,
                          _dictionaryObject2, _dictionaryObject3, nil];

    }
    return self;
}


- (NSDictionary *) randomQuote{
//    NSLog(@"Current Index: %d", _currentIndex);
    NSUInteger index = random() % [self numberOfQuotes];
    _currentIndex = index; // assign current index
    
    return [self.mutableQuotes objectAtIndex:index];
    
}
- (NSUInteger) numberOfQuotes{
    return [self.mutableQuotes count];
    
}
- (NSDictionary *) quoteAtIndex: (NSUInteger) index{
    return [self.mutableQuotes objectAtIndex:index];
    
}
- (void) removeQuoteAtIndex: (NSUInteger) index{
    NSUInteger numQuotes = [self numberOfQuotes];
    if(index < numQuotes){
        [self.mutableQuotes removeObjectAtIndex: index];
    }
    
}
- (void) insertQuote: (NSString *) quote
              author: (NSString *) author
             atIndex: (NSUInteger) index{
    NSUInteger numQuotes = [self numberOfQuotes];
    if (index<=numQuotes) {
        NSDictionary *newdictionaryObect = [NSDictionary dictionaryWithObjectsAndKeys: quote, author,nil];
        [self.mutableQuotes insertObject: newdictionaryObect atIndex:index];
    }
    
}
- (void) insertQuote: (NSDictionary *) quote
             atIndex: (NSUInteger) index{
    NSUInteger numQuotes = [self numberOfQuotes];
    if (index<=numQuotes) {
        NSDictionary *newdictionaryObect = [NSDictionary dictionaryWithObjectsAndKeys:quote, NULL,nil];
        [self.mutableQuotes insertObject: newdictionaryObect atIndex:index];
    }
    
}
- (NSDictionary *) nextQuote{
    NSLog(@"Current Index: %d", _currentIndex);
    NSUInteger index = _currentIndex+1;
    
    if (index< self.numberOfQuotes) {
        _currentIndex++;
        return [self.mutableQuotes objectAtIndex:0];

    }
    else{
        _currentIndex=0;
        return [self.mutableQuotes objectAtIndex:0];
    }

}
- (NSDictionary *) prevQuote{
    NSUInteger index = _currentIndex-1;
    
    if (index>-1) {
        _currentIndex--;
        return [self.mutableQuotes objectAtIndex:index];
    }
    else{
        NSUInteger numQuotes = [self numberOfQuotes];
        _currentIndex = numQuotes-1;
        return [self.mutableQuotes objectAtIndex:self.numberOfQuotes];
    }
    
    
}



@end
