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
@property(strong, nonatomic)NSMutableArray *favoriteQuotes;
@property (strong, nonatomic) NSDictionary *dictionaryObject1;
@property (strong, nonatomic) NSDictionary *dictionaryObject2;
@property (strong, nonatomic) NSDictionary *dictionaryObject3;
@property (strong, nonatomic) NSDictionary *dictionaryObject4;
@property (strong, nonatomic) NSDictionary *dictionaryObject5;
@property (assign) NSUInteger currentIndex;

@property (strong, nonatomic) NSString *filepath;

@property (strong, nonatomic) NSString *favoritesFilepath;




@end

NSString *const QuotesPlist = @"quotes.plist";
NSString *const FavoritesPlist = @"favorites.plist";



@implementation QuotesModel

+(instancetype) sharedModel {
    static QuotesModel *_sharedModel = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _sharedModel = [[self alloc] init];
        
    });
    
    return _sharedModel;
}






- (instancetype)init
{
    self = [super init];
    if (self) {
        
        NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
        NSString *documentsDirectory = [paths objectAtIndex:0];
        _filepath = [documentsDirectory stringByAppendingPathComponent:QuotesPlist];
        _mutableQuotes = [NSMutableArray arrayWithContentsOfFile:_filepath];
        _favoritesFilepath = [documentsDirectory stringByAppendingPathComponent:FavoritesPlist];
        _favoriteQuotes = [NSMutableArray arrayWithContentsOfFile:_favoritesFilepath];
        
        
        self.currentIndex = 0;

        
        
        _dictionaryObject1 = [NSDictionary dictionaryWithObjectsAndKeys:
                   @"The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart.", @"Helen Keller", nil];
        _dictionaryObject2 = [NSDictionary dictionaryWithObjectsAndKeys:
                              @"Start by doing what's necessary; then do wht's possible; and suddenly you are doing the impossible.", @"Francis of Assisi", nil];
        _dictionaryObject3 = [NSDictionary dictionaryWithObjectsAndKeys:
                              @"Keep your face always toward the sunshine- and shadows will fall behind you.", @"Walt Whitman", nil];
        _dictionaryObject4 = [NSDictionary dictionaryWithObjectsAndKeys:
                              @"Don't cry because it's over, smile because it happened.", @"Dr.Seuss", nil];
        _dictionaryObject5 = [NSDictionary dictionaryWithObjectsAndKeys:
                              @"Be yourself; everyone else is already taken.", @"Oscar Wilde", nil];
       
        
        _mutableQuotes = [ [NSMutableArray alloc] initWithObjects: _dictionaryObject1,
                          _dictionaryObject2, _dictionaryObject3, _dictionaryObject4, _dictionaryObject5, nil];
//        _favoriteQuotes = [[NSMutableArray alloc] init];
        _favoriteQuotes = [ [NSMutableArray alloc] initWithObjects: _dictionaryObject1,
                           nil];

    }
    return self;
}

-(void) save{
    [self.mutableQuotes writeToFile:self.filepath atomically:YES];
    [self.favoriteQuotes writeToFile:self.favoritesFilepath atomically:YES];
}


- (NSDictionary *) randomQuote{
 
    NSUInteger index = random() % [self numberOfQuotes];
    _currentIndex = index; // assign current index
    NSLog(@"Current Index: %lu", (unsigned long)_currentIndex);
    
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
        [self save];
    }
    
}
- (void) insertQuote: (NSDictionary *) quote
             atIndex: (NSUInteger) index{
    NSUInteger numQuotes = [self numberOfQuotes];
    if (index<=numQuotes) {
        NSDictionary *newdictionaryObect = [NSDictionary dictionaryWithObjectsAndKeys:quote, NULL,nil];
        [self.mutableQuotes insertObject: newdictionaryObect atIndex:index];
        [self save];
    }
    
}
- (NSDictionary *) nextQuote{
    if(self.currentIndex == 0){
        self.currentIndex = [self numberOfQuotes] -1;
    }else{
        self.currentIndex = (self.currentIndex - 1);
    }
    
    return [self.mutableQuotes objectAtIndex:self.currentIndex];
    


}
- (NSDictionary *) prevQuote{
    
    self.currentIndex = (self.currentIndex + 1) % [self numberOfQuotes];
    return [self.mutableQuotes objectAtIndex:self.currentIndex];
    

    
}

- (void) removeFavoriteAtIndex: (NSUInteger) index{
    NSUInteger numberOfFavorites = [self numberOfFavorites];
    if(index < numberOfFavorites){
        [self.favoriteQuotes removeObjectAtIndex:index];
        [self save];
    }
}

- (NSUInteger) numberOfFavorites{
    return [self.favoriteQuotes count];
}




- (void) insertFavorite: (NSString *) quote author: (NSString *) author atIndex: (NSUInteger) index{
    NSUInteger numOfFavorites = [self numberOfFavorites];
    if(index <= numOfFavorites){
        NSDictionary *newdictionaryObect = [NSDictionary dictionaryWithObjectsAndKeys: quote, author,nil];
        [self.favoriteQuotes insertObject: newdictionaryObect atIndex:index];
        [self save];

    }
}

- (void) insertFavorite: (NSDictionary *) quote atIndex: (NSUInteger) index{
    NSUInteger numOfFavorites = [self numberOfFavorites];
    if(index <= numOfFavorites){
//        NSDictionary *newdictionaryObect = [NSDictionary dictionaryWithObjectsAndKeys:quote, NULL,nil];
        [self.favoriteQuotes insertObject: quote atIndex:index];
        [self save];

    }
}

- (NSDictionary *) favoriteAtIndex: (NSUInteger) index{
    return [self.favoriteQuotes objectAtIndex:index];
}


-(void) addQuoteToFavorites{
    if([self numberOfQuotes])
    {
        NSLog(@"ADD QUote FunCTION");
        NSDictionary *currentQuote = [self quoteAtIndex:self.currentIndex];
        
//        if(![self favoritesContainsQuote:currentQuote]){
            [self insertFavorite:currentQuote atIndex:0];
//        }
        NSUInteger numOfFavorites = [self numberOfFavorites];
        
        NSLog(@"favorite size: %lu", (unsigned long)numOfFavorites);
    }
}

-(BOOL) favoritesContainsQuote: (NSDictionary *) currentQuote{
    for(NSDictionary *quoteDict in self.favoriteQuotes){
        NSString *quote = quoteDict[@"quote"];
        if([currentQuote[@"quote"] isEqualToString:quote]){
            return YES;
        }
    }
    return NO;
}



@end
