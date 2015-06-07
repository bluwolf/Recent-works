//
//  ViewController.m
//  Lab2_CreateStory
//
//  Created by Xiaohan Chen on 2/2/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()
@property (weak, nonatomic) IBOutlet UITextField *nameTField;

@property (weak, nonatomic) IBOutlet UITextField *ageTextFeld;
@property (weak, nonatomic) IBOutlet UITextField *occupationTextField;
@property (weak, nonatomic) IBOutlet UISegmentedControl *lessMoreSegment;
@property (weak, nonatomic) IBOutlet UISlider *dragonsSlider;
@property (weak, nonatomic) IBOutlet UISegmentedControl *animalSegment;
@property (weak, nonatomic) IBOutlet UIStepper *happinessStepper;
@property (weak, nonatomic) IBOutlet UISwitch *endingSwitch;
@property (weak, nonatomic) IBOutlet UITextField *dragonNumberText;
@property (weak, nonatomic) IBOutlet UITextField *degreeHappinessText;
@property (weak, nonatomic) IBOutlet UIButton *backGroundButton;
@property (weak, nonatomic) IBOutlet UIView *moreView;
@property (weak, nonatomic) IBOutlet UIButton *storyCreateButton;

@property (weak, nonatomic) IBOutlet UIButton *newbackGButton;





@end

@implementation ViewController
 NSString *animalTitle;

- (IBAction)newBackButtonTouched:(id)sender {
    [self.nameTField resignFirstResponder];
    [self.ageTextFeld resignFirstResponder];
    [self.occupationTextField resignFirstResponder];
    
    
    
}
-(IBAction)textFieldExit:(id)sender{
    [sender resignFirstResponder];
}

//-(IBAction)keyboardDone:(id){
//    [sender resignFirstResponder];
//}
//- (IBAction)backGroundButtonATouched:(id)sender {
//    [self.nameTField resignFirstResponder];
//    [self.ageTextFeld resignFirstResponder];
//    [self.occupationTextField resignFirstResponder];
//}


- (IBAction)dragonSliderChanged:(id)sender {
    if(sender==_dragonsSlider){
        NSUInteger index = (NSUInteger)(_dragonsSlider.value + 0.5);
        [_dragonsSlider setValue:index animated:YES];
        _dragonNumberText.text=[NSString stringWithFormat:@"%lu",(unsigned long)index];
    }
}

- (IBAction)hapinessStepperChanged:(id)sender {
    if(sender==_happinessStepper){
        NSUInteger index = (NSUInteger)(_happinessStepper.value);
        _degreeHappinessText.text=[NSString stringWithFormat:@"%lu",(unsigned long)index];
    }

}
- (IBAction)segmentSwitch:(id)sender { // controls less more view
    UISegmentedControl *segmentedControl = (UISegmentedControl *) sender;
    NSInteger selectedSegment = segmentedControl.selectedSegmentIndex;
    
    if (selectedSegment == 0) {
        [_moreView setHidden:YES];

    }
    else{
        [_moreView setHidden:NO];
    }
}


- (IBAction)createClicked:(id)sender {
    if ([self.nameTField.text  isEqual:@""] || [self.ageTextFeld.text  isEqual:@""]||[self.occupationTextField.text  isEqual:@""]) {

        UIAlertView *alertView = [[UIAlertView alloc]
                                  initWithTitle:@"Error"
                                  message:@"Enter information in all textfields"
                                  delegate:self
                                  cancelButtonTitle:@"Cancel"
                                  otherButtonTitles:@"OK", nil];
        [alertView show];
    }
    
    else{
            UIActionSheet *actionSheet = [[UIActionSheet alloc]
    
                                          initWithTitle:@"Are you ready for your story?"
                                          delegate:self
                                          cancelButtonTitle:@"Not Yet!"
                                          destructiveButtonTitle:@"Yes"
                                          otherButtonTitles: nil];
    
            [actionSheet showInView:self.view];
    }
    
}



-(void) actionSheet:(UIActionSheet*)actionSheet
    didDismissWithButtonIndex:(NSInteger)buttonIndex{
    if(buttonIndex!=[actionSheet cancelButtonIndex]){
        [self createStory];
    }
}
- (IBAction)animalSegmentChanged:(id)sender {
    animalTitle = [_animalSegment titleForSegmentAtIndex:_animalSegment.selectedSegmentIndex];
    
}

-(void) createStory{
    if (_moreView.hidden) {
        NSString *message = [[NSString alloc] initWithFormat: @" %@. You are just a normal %@ year(s) old guy who is doing %@ for a living.", self.nameTField.text, _ageTextFeld.text,_occupationTextField.text];
        UIAlertView *alertView = [[UIAlertView alloc]
                                  initWithTitle:@"Your Story"
                                  message:message
                                  delegate:self
                                  cancelButtonTitle:@"Cancel"
                                  otherButtonTitles:@"OK", nil];
        [alertView show];
        
        
        
    }
    else{
        NSString *titleAnimal = [_animalSegment titleForSegmentAtIndex:_animalSegment.selectedSegmentIndex];

        if (!_endingSwitch.isOn) {
            
            NSString *message1 = [[NSString alloc] initWithFormat: @" %@. You are just a normal %@ year(s) old guy who is doing %@ for a living. But one day, bravely slayed %@ dragon(s). After that, you became a hero in the kingdom of Snowville. But eventually, you were murder by a %@ widow who has %@ monsterous children.", self.nameTField.text, self.ageTextFeld.text,_occupationTextField.text,_dragonNumberText.text,titleAnimal,_degreeHappinessText.text];
            UIAlertView *alertView = [[UIAlertView alloc]
                                      initWithTitle:@"Your Story"
                                      message:message1
                                      delegate:self
                                      cancelButtonTitle:@"Cancel"
                                      otherButtonTitles:@"OK", nil];
            [alertView show];
        }
        else{
            NSString *message1 = [[NSString alloc] initWithFormat: @" %@. You are just a normal %@ years old guy who is doing %@ for a living. But one day, you bravely slayed %@ dragons. After that, you became a hero in the kingdom of Snowville. But eventually, you married a %@ princess and had %@ children with her. Congratulations, you and her lived happily ever after. ", self.nameTField.text, self.ageTextFeld.text,_occupationTextField.text,_dragonNumberText.text,titleAnimal,_degreeHappinessText.text];
            UIAlertView *alertView = [[UIAlertView alloc]
                                      initWithTitle:@"Your Story"
                                      message:message1
                                      delegate:self
                                      cancelButtonTitle:@"Cancel"
                                      otherButtonTitles:@"OK", nil];
            [alertView show];
            
        }
    }
    
    
}






- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
