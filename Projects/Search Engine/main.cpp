#include <iostream>
#include <fstream>
#include <string>
#include <cstring>
#include "Webpage.h"
#include "./lib/Set.h"
#include <vector>
// #include "mainWindow.h"
// #include <QApplication>
//#include <QWidget>
// #include <QMainWindow>
//  #include <QPushButton>
//  #include <QMessageBox>
//  #include <QPlainTextEdit>
//  #include <QHBoxLayout>
//  #include <QVBoxLayout>
//  #include <QString>
// #include <QApplication>
//  #include <QFont>
//  #include <QVBoxLayout>
//  #include <QWidget>





using namespace std;



Set<string> filterInput() // filters user input into pure words and put them into a Set
{
	Set<string> filtered;
	string temp;
	Set<string>::iterator it;

  try{
    while(cin>>temp){

      string regroup;
      
      for(unsigned int i =0; i<temp.length(); i++){
        char s = temp[i];
       if (s == ')'){
       	filtered.insert(regroup);
       	throw false;
       } 
        //cout<<s<<" ";

        if(((s>='0') && (s<='9')) || ((s>='A') && (s<='Z')) || ((s>='a') && (s<='z'))){
        	if((s>='A') && (s<='Z')){
            s = (tolower(s));
            //cout<<"Printing character:"<<endl;
          	}
          regroup = regroup + s;

        }

        if(!(((s>='0') && (s<='9')) || ((s>='A') && (s<='Z')) || ((s>='a') && (s<='z')))){
          regroup = regroup + " ";
        }
      }
       //cout<<"User input regroup is: "<< regroup<<endl;
     filtered.insert(regroup);
    }
	}

    catch (bool goodBool){
    	//cout<<"input successifully read"<<endl;
    }
    //cout<<"Print filter:"<<endl;
    //filtered.print();

    Set<string> furtherFilter; // have to filter more cause this filter was uncomplete
    for(it = filtered.begin(); it != filtered.end(); ++it){
      string temp = *it;
      stringstream ss(temp);
      while(ss>>temp){
        furtherFilter.insert(temp);
      //  cout<<"Word: "<<temp<<endl;
      }
    }
     // cout<<"print further filter: "<<endl;
     //furtherFilter.print();
    return furtherFilter;
}

int main(int argc, char *argv[])
{

	if(argc !=2){
		cout<<"Please enter a filename"<<endl;
		return 0;
	}

	Set<WebPage*> universe;
	ifstream ifile(argv[1]);
	string filename;

	while(ifile>>filename){ //initialize network of webpages
		//char* name = new char[filename.length()+1];
		//strcpy(name, filename.c_str());
		WebPage* newpage = new WebPage(filename);
		//cout<<endl<<endl<<endl;
		//cout<<"PRINTING NEWLY INITIALIZED PAGE: "<<endl;
		//cout<<"On file: "<<filename<<endl;
		//cout << *newpage <<endl;
		universe.insert(newpage);
	}


	Set<WebPage*>::iterator traverseUniverse;

	// find all incoming links for a webpage and associate them with that webpage
	Set<WebPage*>::iterator traverseName;
	for (traverseUniverse = universe.begin(); traverseUniverse != universe.end(); ++traverseUniverse){
		Set<string> outLinks = (*traverseUniverse)->allOutgoingLinks();


		for (traverseName = universe.begin(); traverseName != universe.end(); ++traverseName){

			string fileName = (*traverseName)->filename();
			if(outLinks.contains(fileName)){
				(*traverseName)->addIncomingLink ((*traverseUniverse)->filename());
			}
		}
	
	}


	//test print outgoing links
	for (traverseUniverse = universe.begin(); traverseUniverse != universe.end(); ++traverseUniverse){
		Set<string> test = (*traverseUniverse)->allOutgoingLinks();
		//if(!test.empty()) cout<<"test not empty"<<endl;
		cout<<"Outgoing of a webpage:"<<endl;
		test.print();
		cout<<endl;
	}
	cout<<"~~~~~~~~~~~~~~~~~~~~~"<<endl;
	//test print incoming links
	for (traverseUniverse = universe.begin(); traverseUniverse != universe.end(); ++traverseUniverse){
		Set<string> test = (*traverseUniverse)->allIncomingLinks();
		//if(!test.empty()) cout<<"test not empty"<<endl;
		cout<<"Incoming of a webpage:"<<endl;
		test.print();
		cout<<endl;
	}


	//***********************PageRank****************
	class page{
	public:
		page(string name, Set<string> incoming1, Set<string> outgoing1, double Rank){
		 pageName = name;
		 currentRank  = Rank;
		 incoming = incoming1;
		 outgoing = outgoing1;
		}

		string pageName;
		double currentRank ;
		Set<string> incoming;
		Set<string> outgoing;
	};

	//start with arbitrary node (the first one)
	traverseUniverse = universe.begin();
	//initialize small network
	vector<page> network;

	Set<string> in = (*traverseUniverse)->allIncomingLinks();
	Set<string> out = (*traverseUniverse)->allOutgoingLinks();
	string name = (*traverseUniverse)->filename();
	double totalNode = (in.size() + out.size() +1.0);
	double rank = 1.0/ totalNode; //total number of nodes
	page onePage(name, in, out, rank);
	network.push_back(onePage);

	cout<<"cow"<<endl;
	// initialize all ranks to 1/n
	//network set for incoming links
	Set<string>::iterator tString;
	 for (tString = in.begin(); tString != in.end(); ++tString){
	 	for (traverseUniverse = universe.begin(); traverseUniverse != universe.end(); ++traverseUniverse){
	 		string aName = *tString;
	 		cout<<aName<<endl;
	 		if( (*traverseUniverse)->filename() == aName ) {
	 			in = (*traverseUniverse)->allIncomingLinks();
				out = (*traverseUniverse)->allOutgoingLinks();
				name = (*traverseUniverse)->filename();
				cout<<"cow"<<endl;
				page onePage(name, in, out, rank);
				network.push_back(onePage);
	 		}
	 	}
	 }

	 cout<<"cow"<<endl;
	 // network set for outgoing links
	 for (tString = out.begin(); tString != out.end(); ++tString){
	 	for (traverseUniverse = universe.begin(); traverseUniverse != universe.end(); ++traverseUniverse){
	 		string aName = *tString;
	 		if( (*traverseUniverse)->filename() == aName ) {
	 			in = (*traverseUniverse)->allIncomingLinks();
				out = (*traverseUniverse)->allOutgoingLinks();
				name = (*traverseUniverse)->filename();
				page onePage(name, in, out, rank);
				network.push_back(onePage);
	 		}
	 	}
	 }

 
	 cout<<"vector size: ";
	 cout<<network.size()<<endl;
	 //perform ranking operation
	 for(int iterate = 0; iterate < 30; iterate++){
	 	for (unsigned int j = 0; j< network.size(); j++){
	 		page currentPage = network[j];
	 		double currRank = currentPage.currentRank;
	 		double value1 = 0.85*currRank/(currentPage.outgoing.size());
	 		double value2 = 0.15* totalNode;
	 		currRank = value1 +value2;
	 		currentPage.currentRank = currRank;
	 	}
	 }






//page rank ends here*******************************************************
		
	// QApplication app(argc, argv);
	// mainWindow window;
	// window.show();
	// return app.exec();

	string choice;
	//char c;
	while(1){

		cout<<endl;
		cout<<"============================================"<<endl;
		cout<<"Please enter query in the right format:"<<endl;
		cin>>choice;

		if(choice == ".") {
			cout<<"Quitting program"<<endl;
			break;
		}

		 else if(choice == "AND"){

			Set<string> keywords = filterInput();
			//cout<<"Printing keywords set:"<<endl;
			//keywords.print();
			for (traverseUniverse = universe.begin(); traverseUniverse != universe.end(); ++traverseUniverse){
				// cout<<endl;
				// cout<<"******Printing current universe: "<<endl;
				// cout<<**traverseUniverse;
				Set<string> intersect=  ((*traverseUniverse)->allWords()).setIntersection(keywords);
				//intersect.print();
				if(!intersect.empty()){
					cout<<endl;
					cout<<"PRINTING CORRESPONDING WEBPAGE:"<<endl;
					cout<<**traverseUniverse<<endl;
					cout<<"---------------------------"<<endl;
					cout<<endl;
				}
				else{
					cout<<"Page not found"<<endl;
				}
			}

			
		}

		else if(choice == "OR"){
			Set<string> keywords = filterInput();
			Set<string>::iterator traverseKeywords;

			for(traverseKeywords = keywords.begin(); traverseKeywords != keywords.end(); ++traverseKeywords){
				string word = *traverseKeywords;
				for (traverseUniverse = universe.begin(); traverseUniverse != universe.end(); traverseUniverse++){
					Set<string> toCompare = (*traverseUniverse)->allWords();
					if(toCompare.find(word) != toCompare.end()){
						cout<<endl;
						cout<<"PRINTING CORRESPONDING WEBPAGE:"<<endl;
						cout<<**traverseUniverse<<endl;
						cout<<"---------------------------"<<endl;
						cout<<endl;
					}
				}
			}
			
		}

		else{
			for(unsigned int j=0; j<choice.size();j++)
				{
					char c;
					c = choice[j];
					choice[j] = (tolower(c));
				}
				//cout<<"Choice is:"<< choice<<endl;
			for (traverseUniverse = universe.begin(); traverseUniverse != universe.end(); ++traverseUniverse)
			{	
				Set<string> toCompare = (*traverseUniverse)->allWords();
				//cout<<"printing found website: "<<endl;
				//toCompare.print();
				cout<<endl;
				if(toCompare.find(choice) != toCompare.end()){
					cout<<"PRINTING CORRESPONDING WEBPAGE:"<<endl;
					cout<<**traverseUniverse<<endl;
					cout<<"-------------------------------"<<endl;
					cout<<endl;
				}
			}

		}
	}// end of while (1)


	return 0;
}