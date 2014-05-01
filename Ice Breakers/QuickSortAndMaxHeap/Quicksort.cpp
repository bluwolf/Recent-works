#include <iostream>
#include <vector>
#include <ctime>
#include <cstdlib>


using namespace std;

// prototypes
void QuickSort1 (vector<int>& vec, int l, int r); // random positoin
void QuickSort2 (vector<int>& vec, int l, int r); // last position
int partition (vector<int>& vec, int l, int r, int p);
void exchange (vector<int>& vec, int i, int j);
void print(vector<int>& vec);
void initAndRandom(vector<int>& vec, int size); // initialize, randomize, and get the running time of quick sort



void QuickSort1 (vector<int>& vec, int l, int r) { // uses random position as subarray

	if(l >=r) return;
	int p = l + (rand()% (r-l+1)); // random pivot

	int m = partition(vec, l, r, p); 
	QuickSort1(vec, l, m);
	QuickSort1(vec, m+1, r);

}

void QuickSort2 (vector<int>& vec, int l, int r) { // using the last position of the current array

	if(l >=r) return;
	int p = r;

	int m = partition(vec, l, r, p); 
	QuickSort2(vec, l, m);
	QuickSort2(vec, m+1, r);


}

int partition (vector<int>& vec, int l, int r, int p) {// l = leftbound; r = rightbound and pivot
	int pivot = vec[p];
	exchange(vec,p,r);
	
	int left = l; int right = r-1;
	while(left<right){	

		while((vec[left] <= pivot) && (left <right)){left++;}
		while((vec[right] >= pivot) && (left < right)){right--;};

		if(left <right) {
			exchange(vec,left, right); 

			// cout<<"left: "<<left<< " right: "<<right<<endl;
			// cout<<vec[left]<<" and "<< vec[right]<<" swapped"<<endl;
			// cout<<"Printing out current list after swap"<<endl;
			// print(vec);
		}
		
	}

	if(vec[right]>= vec[r]){
		exchange(vec, right, r);
	}
	// cout<<"put back pivot: "<<endl;
	// print(vec);
	// cout<<"partition ends here"<<endl;
	//  cout<<"right: "<<right<<endl<<endl;
	return right;
}


void exchange (vector<int>& vec, int i, int j){
	int temp = vec[i];
	vec[i] = vec[j];
	vec[j] = temp;
}


void print(vector<int>& vec){
	for(vector<int>::const_iterator j = vec.begin(); j != vec.end(); j++) cout<<*j<<" ";
	cout<<endl;
}

void initAndRandom(vector<int>& vec, int t){ // initialize, randomize, and get the running time of quick sort
	for (int i = 0; i<10000; i++){
		vec.push_back(i);
	}
	double tMethod1=0, tMethod2 =0;

	for( int run =0; run < 20; run++){ // run a lot of tests to homogenize result

		for(int i = 0; i<t; i++){ // mix
			int rand1 = rand()% 10000;
			int rand2 = rand()% 10000;
			exchange(vec, rand1, rand2);
		}

		clock_t start=0, finish=0;
		double dur1=0;
		start = clock();
		QuickSort1(vec, 0, vec.size()-1); // do operation
		finish = clock();
		dur1 = (double)(finish - start);
		dur1 /= CLOCKS_PER_SEC;
		tMethod1 += dur1; 

		for(int i = 0; i<t; i++){ // mix again
			int rand1 = rand()% 10000;
			int rand2 = rand()% 10000;
			exchange(vec, rand1, rand2);
		}

		start=0, finish=0;
		double dur2=0;
		start = clock();
		QuickSort2(vec, 0, vec.size()-1); 
		finish = clock();
		dur2 = (double)(finish - start);
		dur2 /= CLOCKS_PER_SEC;
		tMethod2 += dur2;

	}

	cout << "Avg time of sorting "<<t<< "-mixed vector (random pivot): "<< tMethod1/20 << endl;
	cout << "Avg time of sorting "<<t<<"-mixed vector (end pivot): "<< tMethod2/20<< endl;
	cout<<endl;

}

int main(int argc, char const *argv[])
{
	srand(time(0));
	vector<int> Unsorted;

	initAndRandom(Unsorted, 50);
	initAndRandom(Unsorted, 100);
	initAndRandom(Unsorted, 300);
	initAndRandom(Unsorted, 500);
	initAndRandom(Unsorted, 1000);
	initAndRandom(Unsorted, 2000);
	initAndRandom(Unsorted, 5000);
	initAndRandom(Unsorted, 10000);
	initAndRandom(Unsorted, 20000);
	

	


	// for (int i = 0; i <30; i++){
	// 	Unsorted.push_back(rand()% 50);
	// }


	// // // printing out initialized list

	// cout<<"Printing out initialized list:"<<endl;
	// print(Unsorted);
	// cout<<endl;
	

	// QuickSort1(Unsorted, 0, Unsorted.size()-1);

	// cout<<"Printing out sorted list:"<<endl;
	// print(Unsorted);


	return 0;
}