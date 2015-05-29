//
//  QuotesViewController.m
//  Lab4_Quotes
//
//  Created by Student on 2/25/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "QuotesViewController.h"

#import "QuotesModel.h"

@interface QuotesViewController ()




//private properties
@property (strong, nonatomic) QuotesModel *model;
@property (weak, nonatomic) IBOutlet UITextView *QuotesTextArea;
@property (weak, nonatomic) IBOutlet UITextView *AuthorTextView;




@end

@implementation QuotesViewController

- (BOOL)canBecomeFirstResponder
{
    return YES;
}

- (void)motionEnded:(UIEventSubtype)motion withEvent:(UIEvent *)event
{
    if (motion == UIEventSubtypeMotionShake)
    {
       [self showAlert];
    } 
}

-(IBAction)showAlert
{
//    UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"Hello World" message:@"This is my first app!" delegate:nil cancelButtonTitle:@"Awesome" otherButtonTitles:nil];
//    
//    [alertView show];
    NSDictionary *temp = [self.model randomQuote];
    NSArray* author=[temp allKeys];
    NSArray* quote = [temp allValues];
    NSString * aString = [author objectAtIndex:0];
    NSString * qString = [quote objectAtIndex:0];
    
    self.QuotesTextArea.alpha=0;
    self.AuthorTextView.alpha=0;
    self.QuotesTextArea.text= qString;
    self.AuthorTextView.text= aString;
    
    [UIView animateWithDuration:1.0 animations:^{
        self.QuotesTextArea.alpha=1;
        self.AuthorTextView.alpha=1;
    }];
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"cool-hd-widescreen-moving-wallpapers1.jpg"]];
    
	// Do any additional setup after loading the view, typically from a nib.
    self.model = [[QuotesModel alloc] init];
    
    
    _QuotesTextArea.backgroundColor = [UIColor clearColor];
    _AuthorTextView.backgroundColor = [UIColor clearColor];
  
    
    
    UITapGestureRecognizer *singleTap =
    [[UITapGestureRecognizer alloc]
     initWithTarget:self
     action:@selector(singleTapRecognized:)];
    [self.view addGestureRecognizer:singleTap];
    
    UISwipeGestureRecognizer *swipeLeft =
    [[UISwipeGestureRecognizer alloc] initWithTarget:self
        action:@selector(LSwipeGestureRecognized:)];
    swipeLeft.direction = UISwipeGestureRecognizerDirectionLeft;
    [self.view addGestureRecognizer:swipeLeft];
    
    
    UISwipeGestureRecognizer *swipeRight =
    [[UISwipeGestureRecognizer alloc] initWithTarget:self
        action:@selector(RSwipeGestureRecognized:)];
    swipeRight.direction = UISwipeGestureRecognizerDirectionRight;
    [self.view addGestureRecognizer:swipeRight];
    
    
    
    NSLog(@"After init");
    NSLog(@"Number of quotes : %d", [self.model numberOfQuotes]);
    NSLog(@"call insert");
    [self.model insertQuote:@"I'm a random quote inserted on init" author:@"xiaohan" atIndex:0];
    NSLog(@"Number of quotes : %d", [self.model numberOfQuotes]);
    NSLog(@"call remove");
    [self.model removeQuoteAtIndex:0];
    NSLog(@"Number of quotes : %d", [self.model numberOfQuotes]);
}

-(void) singleTapRecognized: (UITapGestureRecognizer *)
recognizer{
    NSDictionary *temp = [self.model randomQuote];
    NSArray* author=[temp allKeys];
    NSArray* quote = [temp allValues];
    NSString * aString = [author objectAtIndex:0];
    NSString * qString = [quote objectAtIndex:0];
    
    
    self.QuotesTextArea.alpha=0;
    self.AuthorTextView.alpha=0;
    self.QuotesTextArea.text= qString;
    self.AuthorTextView.text= aString;
    
    [UIView animateWithDuration:1.0 animations:^{
        self.QuotesTextArea.alpha=1;
        self.AuthorTextView.alpha=1;
    }];
    
    
}

-(void) RSwipeGestureRecognized: (UITapGestureRecognizer *)
recognizer{
    NSDictionary *temp = [self.model randomQuote];
    NSArray* author=[temp allKeys];
    NSArray* quote = [temp allValues];
    NSString * aString = [author objectAtIndex:0];
    NSString * qString = [quote objectAtIndex:0];
    
    self.QuotesTextArea.alpha=0;
    self.AuthorTextView.alpha=0;
    self.QuotesTextArea.text= qString;
    self.AuthorTextView.text= aString;
    
    [UIView animateWithDuration:1.0 animations:^{
        self.QuotesTextArea.alpha=1;
        self.AuthorTextView.alpha=1;
    }];
    
}

-(void) LSwipeGestureRecognized: (UITapGestureRecognizer *)
recognizer{
    NSDictionary *temp = [self.model randomQuote];
    NSArray* author=[temp allKeys];
    NSArray* quote = [temp allValues];
    NSString * aString = [author objectAtIndex:0];
    NSString * qString = [quote objectAtIndex:0];
    
    self.QuotesTextArea.alpha=0;
    self.AuthorTextView.alpha=0;
    self.QuotesTextArea.text= qString;
    self.AuthorTextView.text= aString;
    
    [UIView animateWithDuration:1.0 animations:^{
        self.QuotesTextArea.alpha=1;
        self.AuthorTextView.alpha=1;
    }];
    
}



- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
