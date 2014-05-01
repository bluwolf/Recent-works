#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <string>
#include <vector>
#include <math.h>
#include <sstream>

/*
Programming Objective: apply different filters to numbers inserted in text file
*/

using namespace std;

struct Item {
    int val;
    Item* next;
};

void append(Item*& head, int v)
{
    if(head ==NULL) { // no element in list
        head = new Item;
        head->val=v;
        head->next=NULL;
    }

    else { // at least one element in list
        Item* temp = head;
        while(temp->next) { // find tail
            temp = temp->next; // temp is now tail
        }
        Item* last=new Item;
        last->val=v;
        last->next=NULL ;
        temp->next = last; // tail is now last
    }
}

//bool is even(A); is square(B); is Prime(C)

bool isEven(int n) { // calculating square
    if(n == 0) return true;
    else if(n<0) return false;
    else if(n%2 == 0) return true;
    else return false;
}

bool isSquare(int n) {
    int root = sqrt(n);
    if(root*root == n) return true;
    else return false;
}

bool isPrime(int n) {
    if(n<2) return false;
    else if(n == 2 || n == 3 ) return true;

    for(int i =2; i<n; i++) {
        if(n%i == 0) {
            return false;
        }
    }

    return true;
}

// instead of generating a whole new list, I just browsd through my original list and
//printed out the items that matched the predicates. 

Item* filter(Item *currListHead, bool (*method)(int) ) {

    if(method(currListHead->val)) { // if condition is met, print value 
        cout << currListHead->val<< endl;
    }
    if(currListHead->next == NULL) return currListHead; // base case

    else{
    return filter(currListHead->next, method);
	}
}


// first argument = filename; second = filter name

int main(int argc, char const *argv[])
{
    if (argc !=3)
    {
        cout <<"please the right number of command line arguments"<< endl;
        return 0;
    }

    int number;

    Item* head = NULL;
    ifstream ifile(argv[1]);
    while(ifile>>number) append(head,number); //if number read successful, read into list

    Item* currListHead = head;

    char letter;
    stringstream ss;
    ss<<argv[2]; //read in choice
    ss>>letter;

    //choose the method
    if(letter == 'A') Item* newListHead = filter (currListHead, &isEven); 
    else if(letter == 'B') Item* newListHead = filter (currListHead, &isSquare);
    else if(letter == 'C') Item* newListHead = filter (currListHead, &isPrime);

    //delete list
    while(head->next!=NULL){
    Item* temp = head;
    head = head->next;
    delete temp;
	}

    return 0;
}