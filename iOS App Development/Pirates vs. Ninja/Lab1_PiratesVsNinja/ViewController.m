//
//  ViewController.m
//  Lab1_PiratesVsNinja
//
//  Created by Xiaohan Chen on 1/24/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@property (weak, nonatomic) IBOutlet UILabel *messageLabel;
@property (weak, nonatomic) IBOutlet UITextField *nameTextField;

@end



@implementation ViewController

- (IBAction)backgroundButtonTouched:(id)sender {
    [self.nameTextField resignFirstResponder];
}// disable textfield when background button touched

- (IBAction)exitKeyboardOnReturn:(id)sender {
    [self.nameTextField resignFirstResponder];
} // disable textfield when return button pressed

- (IBAction)ninjaButtonTouched:(id)sender {
    if ([self.nameTextField.text  isEqual:@""]) {
        NSString *message = [[NSString alloc] initWithFormat: @"Please enter your name"];
        self.messageLabel.text = message;
    }
    
    else{
        NSString *message = [[NSString alloc] initWithFormat: @"Shinobi %@. We are all servants of the great Yamato. Someday you are going to become Shinobi no Ou", self.nameTextField.text];
    self.messageLabel.text = message;
    }
}// display message in textfield when ninja button pressed

- (IBAction)pirateButtonTouched:(id)sender {
    if ([self.nameTextField.text  isEqual:@""]) {
        NSString *message = [[NSString alloc] initWithFormat: @"Please enter your name"];
        self.messageLabel.text = message;
    }
    else{
        NSString *message = [[NSString alloc] initWithFormat:   @"Arrrrr %@. You are going to become the king of    pirates!", self.nameTextField.text];
        self.messageLabel.text = message;
    }
} // display message in textfield when pirate  button pressed


- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
