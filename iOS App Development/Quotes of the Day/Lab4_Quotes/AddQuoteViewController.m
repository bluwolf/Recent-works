//
//  AddQuoteViewController.m
//  Lab3_Quotes
//
//  Created by Xiaohan Chen on 3/20/15.
//  Copyright (c) 2015 Xiaohan Chen. All rights reserved.
//

#import "AddQuoteViewController.h"



@interface AddQuoteViewController () <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UIBarButtonItem *SaveBarButton;



@property (weak, nonatomic) IBOutlet UITextField *QuoteText;
@property (weak, nonatomic) IBOutlet UITextField *AuthorText;




@end
@implementation AddQuoteViewController

- (BOOL) textFieldShouldReturn:(UITextField *)textField{
    [textField resignFirstResponder];
    
    return YES;
}

-(BOOL) textField: (UITextField *) textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string{
    if(textField == self.QuoteText){
        
        NSString *changedString = [textField.text stringByReplacingCharactersInRange:range withString:string];
        
        [self validateSaveButtonForText1: changedString forText2: self.AuthorText.text];
    }else if(textField == self.AuthorText){
        NSString *changedString = [textField.text stringByReplacingCharactersInRange:range withString:string];
        [self validateSaveButtonForText1: changedString forText2: self.QuoteText.text];
    }
    return YES;
}

-(void) validateSaveButtonForText1: (NSString *) text1 forText2: (NSString *) text2 {
    NSCharacterSet *set = [NSCharacterSet whitespaceCharacterSet];
    
    self.SaveBarButton.enabled = ([[text1 stringByTrimmingCharactersInSet:set] length] > 0) && ([[text2 stringByTrimmingCharactersInSet:set] length] > 0);
}

- (IBAction)CancelButtonTapped:(id)sender {
    if(self.completionHandler){
        self.completionHandler(nil,nil);
    }
}

- (IBAction)saveBarButtonPressed:(id)sender {
    if(self.completionHandler){
       
        self.completionHandler(self.QuoteText.text,self.AuthorText.text);
    }
}








@end
