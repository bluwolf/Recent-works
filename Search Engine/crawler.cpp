#include <iostream>
#include <fstream>
#include <string>
#include <cstring>
#include "Webpage.h"
#include "./lib/Set.h"
#include <stack>

//write the output file that contains the name of all pages that it 
// discovers in the crawl. The exploration must happen using the DFS algorithm, 
// implemented either recursively or with a stack. You should write the page names 
// in the order in which you first discover them. 

using namespace std;


int main(int argc, char *argv[])
{

	if(argc !=3){
		cout<<"Please enter the  seed file and the output file"<<endl;
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
		// cout<<"PRINTING NEWLY INITIALIZED PAGE: "<<endl;
		// cout<<"On file: "<<filename<<endl;
		//cout << *newpage <<endl;
		universe.insert(newpage);
	}


	Set<WebPage*>::iterator traverseUniverse;

	// find all incoming links for a webpage and associate them with that webpage
	Set<WebPage*>::iterator traverseName;
	for (traverseUniverse = universe.begin(); traverseUniverse != universe.end(); ++traverseUniverse){
		Set<string> outLinks = (*traverseUniverse)->allOutgoingLinks(); // find all outcoming links


		for (traverseName = universe.begin(); traverseName != universe.end(); ++traverseName){

			string fileName = (*traverseName)->filename();
			if(outLinks.contains(fileName)){
				(*traverseName)->addIncomingLink ((*traverseUniverse)->filename()); // add incoming link
			}
		}
	
	}


	// //test print outgoing links
	// for (traverseUniverse = universe.begin(); traverseUniverse != universe.end(); ++traverseUniverse){
	// 	Set<string> test = (*traverseUniverse)->allOutgoingLinks();
	// 	//if(!test.empty()) cout<<"test not empty"<<endl;
	// 	cout<<"Outgoing of a webpage:"<<endl;
	// 	test.print();
	// 	cout<<endl;
	// }
	// cout<<"~~~~~~~~~~~~~~~~~~~~~"<<endl;
	// //test print incoming links
	// for (traverseUniverse = universe.begin(); traverseUniverse != universe.end(); ++traverseUniverse){
	// 	Set<string> test = (*traverseUniverse)->allIncomingLinks();
	// 	//if(!test.empty()) cout<<"test not empty"<<endl;
	// 	cout<<"Incoming of a webpage:"<<endl;
	// 	test.print();
	// 	cout<<endl;
	// }

	
	//dfsSearch

	ofstream ofile(argv[2]);
	stack<string> dfsSearch;
	Set<WebPage*>::const_iterator traverseSearch = universe.begin();
	string filename1;

	dfsSearch.push((*traverseSearch)->filename());//initialize beginning node;
	(*traverseSearch)->flag =1; //outputed
	filename1 = (*traverseSearch)->filename();

	ofile<< filename1<<endl;


	//while (!dfsSearch.empty()){
		filename1 = dfsSearch.top();
		dfsSearch.pop();



	for (traverseSearch = universe.begin(); traverseSearch != universe.end(); ++traverseSearch){
		
		//if((*traverseSearch)->filename() == filename1){
			Set<string> outLinks = (*traverseSearch)->allOutgoingLinks();
			//if(outLinks.empty()) ++traverseSearch;
			//cout<<"Printing out links:"<<endl;
			//outLinks.print();
			Set<string>::const_iterator searchOutlink; // iterates through filenames

			if(!outLinks.empty()){
				for(searchOutlink = outLinks.begin(); searchOutlink != outLinks.end(); ++searchOutlink){
					//cout<<"searching outlink: "<<*searchOutlink<<endl;
					Set<WebPage*>::iterator traverseUniv;
					for (traverseUniv = universe.begin(); traverseUniv != universe.end(); ++traverseUniv){
						string search = *searchOutlink;

						if(  (*traverseUniv)->filename() == search && (*traverseUniv)->flag == -1 ) {
							(*traverseUniv)->flag = 1; // found
							dfsSearch.push((*traverseUniv)->filename());
							// cout<<"filename:"<<endl;
							// cout<<((*traverseUniv)->filename())<<endl;
							ofile<<((*traverseUniv)->filename())<<endl;
						}

					}

					while(!dfsSearch.empty())	{ // look further
						filename1 = dfsSearch.top();
						dfsSearch.pop();
						Set<WebPage*>::iterator traverseAgain;
						for (traverseAgain = universe.begin(); traverseAgain != universe.end(); ++traverseAgain){
							if(  (*traverseAgain)->filename() == filename1 && (*traverseAgain)->flag == -1 ) {
								(*traverseAgain)->flag = 1; // found
								dfsSearch.push((*traverseAgain)->filename());
								// cout<<"filename:"<<endl;
								// cout<<((*traverseUniv)->filename())<<endl;
								ofile<<((*traverseAgain)->filename())<<endl;
							}
						}

					}

				}
			//break;
			}
		//}
	//}

}


	ofile.close();
	cout<<"Data successifully outputed"<<endl;
	//return app.exec();

	return 0;
}