#include <iostream>
#include <sstream>
#include <string>
#include <fstream>
#include "List.h"
#include "Map.h"
using namespace std;


int main(int argc, char const *argv[])
{

	if(argc<2){
	cout<<"please enter a file name"<<endl;
	return 0;
	}

	Map<string, string> map;
	string email, firstName, lastName;
	ifstream ifile (argv[1]);

	//map.add("adf","sdf");

	while(ifile >> email && ifile >> firstName && ifile>>lastName){ // if read successful
		string name = firstName + " "+ lastName;
		//cout<< email<< " "<<name<<endl;
		map.add(email, name);
	}

	
	while(1){
		cout <<"Give me a email and I will give you the name"<< endl;
		string email;
		cin>> email;
		if (email == ".")
		{
			cout<<"program will now quit"<<endl;
			break;
		}
		
		try{
			string name = map.get(email);
			cout <<name<< endl<<endl;
		}
		

		catch (int badValue){
        cout <<"Item does not exist in database. Try again"<<endl<<endl;
      }

	}
	return 0;
}