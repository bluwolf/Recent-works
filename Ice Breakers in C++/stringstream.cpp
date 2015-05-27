#include <iostream> 
#include <fstream>
#include <sstream>
#include <string>

using namespace std;

int main(int argc, char const *argv[])
{
	if (argc<2)
	{
		cout << "please insert a filename" << endl;
	}

	int lineCount;
	string line;
	string word;
	int numLetters; // number of letters in a word
	int numWords=0;
	int index = 0;


	ifstream ifile (argv[1]);
	ifile >> lineCount;
	//cout <<"line count: "<< lineCount<< endl;
	int *numberarray = new int[lineCount-1];
	

		getline(ifile, line);
		getline(ifile, line);
		//cout<<"line: "<<line<<endl;
		stringstream ss(line);
		while(ss>>word){
			//cout <<"word: "<<word<< endl;
			numWords++;
		}
		numberarray[index] = numWords;
		//cout<<"number array at index  "<< index << " is: "<< numberarray[index]<< endl;
		index++;
		numWords=0;
	

	

	for (int i = 0; i < lineCount-1; i++)
	{
		getline(ifile, line);
		//cout<<"line: "<<line<<endl;
		stringstream ss(line);
		while(ss>>word){
			//cout <<"word: "<<word<< endl;
			numWords++;
		}

		numberarray[index] = numWords;
		//cout<<"number array at index  "<< index << " is: "<< numberarray[index]<< endl;
		index++;
		numWords=0;

	
	}

	for (int i = index-1; i >= 0; i--)
	{
		cout <<numberarray[i]<<endl;
	}

	delete [] numberarray;

	return 0;
}