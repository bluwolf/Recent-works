#include <iostream>
#include "./lists/LList.h"
#include <fstream>
#include <string>
#include <cstring>
#include <vector>
#include <sstream>
#include <stdlib.h>
#include <cstdint>

template <typename T>
class Stack : public LinkedList<T> // implement using linked list
{
 public:
  // add appropriate constructors and destructors
  Stack(){}
   ~Stack(){}

  bool isEmpty() const{return LinkedList<T>::size()==0;}

  void push(const T& val){LinkedList<T>::insert(LinkedList<T>::size(),val);}

  void pop(){LinkedList<T>::remove(LinkedList<T>::size()-1);}

  T const & top() const{return LinkedList<T>::get(LinkedList<T>::size()-1);}

  void print(){LinkedList<T>::print();}
};

int main(int argc, char const *argv[])
{
  if(argc !=3){
    cout<<"Please enter the program with 2 file names"<<endl;
    return 0;
  }

  fstream ifile(argv[2]);
  long int value;
  char assignment;
  vector<long int> expression; // expression maps to evaluation
  vector<bool> evaluation;


  while(ifile>>value && ifile>>assignment){
    expression.push_back(value);
    if(assignment == 'F') evaluation.push_back(false);
    else evaluation.push_back(true);
  }
  
  fstream ifile2(argv[1]);
  string line;
  char t;

  while(getline(ifile2,line)){

  try{

    int operatorCount=0; // checks invalidity of number of operator exceeds variables
    int variableCount=0;
    ///vector<bool> compare;
    Stack<long int> stac;
    stringstream ss(line);
    stringstream ss1(line);
    cout<<line<<endl;
    int leftParenCount=0;
    int rightParenthCount=0;

      while(ss1>>t){ // expression error check
        if(t =='(') leftParenCount++;
        if(t == ')') rightParenthCount++;
        if(t == '|') operatorCount++;
        if(t == '&') operatorCount++;
        if(((t>='0') && (t<='9'))) variableCount++;
      }
      int parenthCount = leftParenCount +rightParenthCount;
      if(leftParenCount != rightParenthCount) throw rightParenthCount;
      if(operatorCount >= variableCount) throw operatorCount;
      if(operatorCount >= parenthCount) throw operatorCount;

      string regroup;
      
      //int evaluate;
      Stack<int> tempo; // used for evaluation
      ss>>t; 
      stac.push(-10);
    while(!stac.isEmpty()){// special value of int for special character
      //cout<<"testing value of t:"<<t<<endl;
       //cout<<"Printing current stack: "; stac.print(); cout<<endl;
      if(t == '(') stac.push(-10); //5000
      // if(t == '[') stac.push(-20);//6000
      // if(t == '{') stac.push(-30);//7000
      if(t == '|') stac.push(-100);//8000
      if(t == '&') stac.push(-200);//9000
      if(t == '~') stac.push(-300);//10000
      if(((t>='0') && (t<='9'))) regroup = regroup + t;

      int64_t s;
      stringstream read(regroup);
      read>>s;
       
        for (unsigned int i = 0; i < expression.size(); ++i)
        {
          if (expression[i] == s)
          { 
            regroup.clear();          
            //cout<<"Expression: "<<s<<endl; 
            stac.push(evaluation[i]); //strait input bool values
            s = -1; // flag to reinitialize s
            break;
          }
        }
  
     // stac.print();
      if(t == ')'){
        stac.pop();
        int temp = stac.top();

        while(temp != -10){
          tempo.push(temp);
          stac.pop();
          temp = stac.top();
        }

          int variable1 = tempo.top(); tempo.pop();
          int operate = tempo.top(); tempo.pop();
          int variable2 = tempo.top(); tempo.pop();

          if(operate ==-100){ // or case
           
            if(variable1 && variable2) tempo.push(true);
            if(variable1 && !variable2) tempo.push(false);
            if(variable1 != variable2) tempo.push(true);
          }
          else if(operate ==-200){ // and case
           
            if(variable1 && variable2) tempo.push(true);
            if(!variable1 && !variable2) tempo.push(false);
            if(variable1!= variable2) tempo.push(false);
          }
         else if(operate ==-300){// not case
            
            if(variable1 ==1) tempo.push(false);
            if(variable2 ==0) tempo.push(true);
          } 
          // cout<<endl;
          // cout<< "Tempo: "<<endl;
          // tempo.print();
      } // end of if ")"
      ss>>t;
      stac.push(t);
    } // end of while
      if (tempo.top() == 1) cout<<"True"<<endl;
      if (tempo.top() == 0) cout<< "False"<<endl;
    //cout<<"Printing final stack: "; stac.print(); cout<<endl;
  }// end of try

  catch(int error) { cout<<" Malformed" <<endl;}

  }// end of getline


  return 0;
}