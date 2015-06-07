#ifndef WEBPAGE_H
#define WEBPAGE_H
#include <iostream>
#include "./lib/Set.h"
//#include <string>
//#include <fstream>
#include <sstream>
#include <map>


using namespace std;

class WebPage {
  public:
    WebPage (); 
    WebPage (string filename);
    ~WebPage ();

    Set<string> allWords () const;

   Set<string> allOutgoingLinks () const; //[anchor text on link](filename_of_link) 

   Set<string> allIncomingLinks () const;


  string filename () const;

  bool found () const;


    void addIncomingLink (string start);


    friend ostream & operator<< (ostream & os, const WebPage & page);

   int flag = -1; // DFS search. -1 for not found, 0 for found, 1 for for already outputed to file (prevent repeats)
  private:
    Set<string>* aPage; // constructor initializes a page
    mutable Set<string> incomingLinks;
    mutable Set<string> outgoingLinks;
    mutable string fromFile; // filename from which this page was read
    mutable string nameofFile; // file name of webpage
    
 
};

 WebPage::WebPage(){
  //cout<<"Page empty: no file was inputed"<< endl;
  //flag = -1; // initialize a not found page

  }

 WebPage::WebPage (string filename){ //initialze webpage and save it in private
    ifstream ifile (filename.c_str());

    nameofFile = filename; // initialize filename
    //fromFile = source;      // initialize filename read from

    // cout<<"Source and name of file:"<<endl;
    // cout << fromFile<<" "<<nameofFile<<endl;
    // cout<<"print incoming link: ";
    // incomingLinks.print();
    //cout<<"Source: "<<fromFile;
    aPage= new Set<string>;
    if(ifile.fail()){
        cout<<"Could not open file"<< endl;
        throw false;
    }

    string temp;
    while (ifile>>temp){
      aPage->insert(temp);
    }
  }

 WebPage::~WebPage(){

  }

  Set<string> WebPage::allWords () const {


    Set<string> filtered;

    for(typename Set<string>::iterator it = aPage->begin(); it != aPage->end(); ++it){
    //for(int i =0; i<aPage.size();i++){ // outer loops returns every string in a struct
      // cout<<"Printing Page:"<<endl;
      // aPage.print();
      string temp = *it;
      string regroup;
      int length = temp.length();
      //cout<<"temp: "<<temp<<endl;
      //cout<<"I'm here1"<<endl;    
      
      for(int i =0; i<length; i++){
        //cout<<"I'm here2"<<endl;
        char s = temp[i];
        //cout<<s<<" ";
        if(((s>='0') && (s<='9')) || ((s>='A') && (s<='Z')) || ((s>='a') && (s<='z'))){
          if((s>='A') && (s<='Z')){
            s = (tolower(s));
          }
          regroup = regroup + s;
        }
        if(!(((s>='0') && (s<='9')) || ((s>='A') && (s<='Z')) || ((s>='a') && (s<='z')))){
          regroup = regroup + " ";
        }
      }
      //cout<<"regroup is: "<< regroup<<endl;
     filtered.insert(regroup);  
    }

    Set<string> furtherFilter; // have to filter more cause this filter was uncomplete
    for(typename Set<string>::iterator it = filtered.begin(); it != filtered.end(); ++it){
    //for(int i =0; i<filtered.size();i++){ 
      string temp = *it;
      stringstream ss(temp);
      while(ss>>temp){
        furtherFilter.insert(temp);
       // cout<<"Word: "<<temp<<endl;
      }
    }
    // cout<<"print further filter: "<<endl;
    //furtherFilter.print();
    return furtherFilter;
  }
    

  ostream & operator<< (ostream & os, const WebPage & page){
        //page.aPage.print();
        for (Set<string>:: iterator it= page.aPage->begin(); it != page.aPage->end(); ++it) os << *it<<" ";
        return os;
  }

  Set<string> WebPage::allOutgoingLinks () const {
    //cout <<"hello"<<endl;
    for(typename Set<string>::iterator it = aPage->begin(); it != aPage->end(); ++it){
      string temp = *it;

      string regroup;
      int length = temp.length();
      //cout<<"temp: "<<temp<<endl;
       
      for(int i =0; i<length; i++){
        char s = temp[i];
        //cout<<s<<" ";
        if(s == ']'){ // found a flag
          s = temp[++i];
          if( s== '('){ // check next flag
            s = temp[++i];
            while( s != ')'){
              regroup = regroup + s;
              s = temp[++i];     
            }
            outgoingLinks.insert(regroup);
          }          
        }
      }
    }
    return outgoingLinks;
  }

   Set<string> WebPage::allIncomingLinks () const {

    return incomingLinks;
  }

  string WebPage::filename () const{
    return nameofFile;
  }


  void WebPage::addIncomingLink (string start){
    incomingLinks.insert(start);

  }


 bool WebPage::found () const{
    return flag == 1;

  }



  // int main(int argc, char const *argv[])
  // {
  //   WebPage page("webpage.txt");
  //   page.allWords();
  //   // cout<<"Page is: "<<endl;
  //   // cout<<page;
  //   return 0;
  // }

  #endif