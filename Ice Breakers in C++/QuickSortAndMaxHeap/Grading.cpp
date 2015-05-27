#include <iostream>
#include <vector>
#include <ctime>
#include <cstdlib>
#include <fstream>
#include <string>
#include <deque>

using namespace std;

void QuickSort1 (deque<int>& vec, int l, int r); // random position
int partition (deque<int>& vec, int l, int r, int p);
void exchange (deque<int>& vec, int i, int j);
void print(deque<int>& vec);

void QuickSort1 (deque<int>& vec, int l, int r) { // using the random position of the current array
	//cout<<"I'm here"<<endl;

	if(l >=r) return;
	int p = l + (rand()% (r-l+1));

	//cout<<"Printing p = "<<p<<endl;
	int m = partition(vec, l, r, p); // 0 used to be pivot
	QuickSort1(vec, l, m);
	QuickSort1(vec, m+1, r);
	//cout<<"Printing process:"<<endl;
	//print(vec);

}

void exchange (deque<int>& vec, int i, int j){
	int temp = vec[i];
	vec[i] = vec[j];
	vec[j] = temp;
}

int partition (deque<int>& vec, int l, int r, int p) {// l = leftbound; r = rightbound and pivot
	int pivot = vec[p];
	exchange(vec,p,r);
	
	int left = l; int right = r-1;
	while(left<right){
		
		while((vec[left] <= pivot) && (left <right)){left++;}
		while((vec[right] >= pivot) && (left < right)){right--;}

		if(left <right) {
			exchange(vec,left, right); 
		}	
	}

	if(vec[right]>= vec[r]){
		exchange(vec, right, r);
	}

	return right;
}


void print(vector<int>& vec){
	for(vector<int>::const_iterator j = vec.begin(); j != vec.end(); j++) cout<<*j<<" ";
	cout<<endl;
}



struct Student{
	Student(string n, int s): _name(n), _score(s){}
	string _name;
	int _score;
 
};

int main(int argc, char const *argv[])
{
	if(argc!=3) cout<<"Please enter the right number of command line arguments"<<endl;

	ifstream ifile(argv[1]);


	int numPeople;
	ifile>>numPeople;

	string name;
	int score;

	vector<Student> allStudents;
	deque<int> scoreDatabase;

	for (int i =0; i<numPeople; i++){
		if(ifile>>name && ifile>>score){
			Student newStudent(name, score);
			allStudents.push_back(newStudent);
			//scoreDatabase.push_back(score);
		}
	}

	for (int i = 0; i<numPeople; i++){
		if(ifile>>score) scoreDatabase.push_back(score);
	}

	// cout<<"cout socre database: "<<endl;
	// for (int i = 0; i<numPeople; i++){
	// 	cout<<scoreDatabase[i]<<" ";
	// }
	// cout<<endl;

	QuickSort1(scoreDatabase, 0, scoreDatabase.size()-1); // use quicksort to sort scores

	deque<int> temp = scoreDatabase;
	for (unsigned int i =0; i<allStudents.size(); i++){
		int tScore = allStudents[i]._score;

		cout<<"Looking at "<< tScore<<endl;
		
		
		while(!(temp.size()==0)){
			
			cout<<"Comparing with "<<temp.front()<<endl;

			if(temp.front()>= tScore){
				allStudents[i]._score = temp.front(); //overwrite
				cout<<"Assigning "<<temp.front()<< " to "<<allStudents[i]._name<<endl;
				temp.pop_front();	
				break;
			}

			if(temp.front()< tScore){
				temp.pop_front();	
			}

			if((temp.size() ==1) && (temp.front() < tScore)){ //error check
				cout<<"Not enough available grades to give every student a same or better score"<<endl;
				return 0;
			}
		}
	}


	ofstream ofile (argv[2]);

	for(int i=0;i<numPeople; i++){
		ofile<<allStudents[i]._name<<" "<<allStudents[i]._score<<endl;
	}

	return 0;
}