//
//  AddQuoteViewController.h
//  Lab3_Quotes
//
//  Created by Xiaohan Chen on 3/20/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import <Foundation/Foundation.h>
typedef void(^AddQuoteCompletionHandler)(NSString *QuoteTextField, NSString *AuthorTextField);

@interface AddQuoteViewController : UIViewController

@property(copy, nonatomic) AddQuoteCompletionHandler completionHandler;

@end
