#include <iostream> 
#include <sstream>
#include <string>
#include <vector>
#include <stdlib.h>


// output all strings of exactly n length made up of only characters in S

 
using namespace std;
	void C1(string start, int currentLevel, string current, int maxLevel) 
	{
		if(currentLevel == maxLevel){
		cout << current << endl;
		}
		
		if(currentLevel > maxLevel){
		return;
		}
	
		for(unsigned int i =0; i<start.size(); i++){
		C1(start, currentLevel +1, current + start[i],maxLevel);
		}


	}




int main(int argc, char const *argv[])
{
	if (argc<3)
	{
		cout << "please the right number of command line arguments" << endl;
	}

	string s = argv[1];
	int k = atoi(argv[2]);
	string current;


	cout<<"string size: " << s.size()<< endl;

	C1(s, 0, current, k);



	return 0;
}