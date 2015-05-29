//
//  QuotesViewController.m
//  Lab4_Quotes
//
//  Created by Student on 2/25/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import <AudioToolbox/AudioToolbox.h>
#import <AVFoundation/AVFoundation.h>

#import "QuotesViewController.h"

#import "QuotesModel.h"

@interface QuotesViewController ()




//private properties
@property (readonly) SystemSoundID soundFileID;
@property (strong,nonatomic) AVAudioPlayer *audioPlayer;


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

    [self.audioPlayer play];
    
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
    self.view.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"desktop-autumn-wallpaper-1080p.jpg"]];
    
   
    
    
    
    NSString *soundFilePath = [[NSBundle mainBundle] pathForResource:@"TaDa" ofType:@"wav"];
    NSURL *soundURL = [NSURL fileURLWithPath:soundFilePath];
    AudioServicesCreateSystemSoundID((__bridge CFURLRef)(soundURL), &_soundFileID);
    
    NSString *path = [NSString stringWithFormat:@"%@tone.mp3", [[NSBundle mainBundle] resourcePath]];
    NSURL *toneURL = [NSURL fileURLWithPath:path];
    
    NSError *error;
    self.audioPlayer = [[AVAudioPlayer alloc] initWithContentsOfURL:toneURL error:&error];
    [self.audioPlayer prepareToPlay];

    
    
    
	// Do any additional setup after loading the view, typically from a nib.
    self.model = [[QuotesModel alloc] init];
    
    
    _QuotesTextArea.backgroundColor = [UIColor clearColor];
    _AuthorTextView.backgroundColor = [UIColor clearColor];
  
    
    
    
    UITapGestureRecognizer *singleTap =
    [[UITapGestureRecognizer alloc]
     initWithTarget:self
     action:@selector(singleTapRecognized:)];
    [self.view addGestureRecognizer:singleTap];
    
    UITapGestureRecognizer *doubleTap =
    [[UITapGestureRecognizer alloc]
     initWithTarget:self
     action:@selector(doubleTapRecognized:)];
    [self.view addGestureRecognizer:doubleTap];
    doubleTap.numberOfTapsRequired = 2;
    [self.view addGestureRecognizer:doubleTap];
    [singleTap requireGestureRecognizerToFail:doubleTap];
    
    
    
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
    
    
    
     NSLog(@"Documents Directory: %@", [[[NSFileManager defaultManager] URLsForDirectory:NSDocumentDirectory inDomains:NSUserDomainMask] lastObject]);
    
    NSLog(@"After init");
    NSLog(@"Number of quotes : %lu", (unsigned long)[self.model numberOfQuotes]);
    NSLog(@"call insert");
    [self.model insertQuote:@"I'm a random quote inserted on init" author:@"xiaohan" atIndex:0];
    NSLog(@"Number of quotes : %lu", (unsigned long)[self.model numberOfQuotes]);
    NSLog(@"call remove");
    [self.model removeQuoteAtIndex:0];
    NSLog(@"Number of quotes : %lu", (unsigned long)[self.model numberOfQuotes]);
}

-(void) singleTapRecognized: (UITapGestureRecognizer *)
recognizer{
    
    AudioServicesPlaySystemSound(self.soundFileID);
    
    
    
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

-(void) doubleTapRecognized: (UITapGestureRecognizer *)
recognizer{
    
    NSLog(@"double tapped");
    [self.model addQuoteToFavorites];
    
    
    
    
}

-(void) RSwipeGestureRecognized: (UITapGestureRecognizer *)
recognizer{
    [self.audioPlayer play];
    
    NSDictionary *temp = [self.model nextQuote];
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
    [self.audioPlayer play];
    NSDictionary *temp = [self.model prevQuote];
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
