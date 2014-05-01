#include <iostream>
#include <vector>
#include <cstdlib>
#include <fstream>
#include <string>
#include <math.h>
#include <ctime>

using namespace std;

struct intCompare{

  bool operator()(int _lhs, int _rhs){return _lhs < _rhs;}
};


template <class T, class Comparator>

  class MaxHeap {
     public:
       MaxHeap (int d, Comparator comp);
       // Constructor that builds a d-ary Max Heap using the given
       //    comparator to compare elements and decide which one is
       //    larger. 


       ~MaxHeap ();

       void add (const T & item);

       const T & peek () const;
          // returns the element with maximum priority.
          //   Throws an exception if the heap is empty. 

       void remove ();
         /* removes the element with maximum priority. */

       bool isEmpty ();
         /* returns true iff there are no elements on the heap. */

       void exchange (vector<int>& vec, int i, int j);

       void print();

       void trickleDown (T pos);

       bool ifChildren(int pos); // check if children exists (if it is a leaf node)

      private:
        vector<T> _heap;
        int _d; //d-ary heap: each node has d children
  };

  template <class T, class Comparator>
  MaxHeap<T, Comparator>::MaxHeap(int d, Comparator comp){
    _d = d; // initialize heap structure
  }

  template <class T, class Comparator>
   MaxHeap<T, Comparator>::~MaxHeap(){

  }

  template <class T, class Comparator>
  void MaxHeap<T, Comparator>::print(){
    for(vector<int>::const_iterator j = _heap.begin(); j != _heap.end(); j++) cout<<*j<<" ";
    cout<<endl;
  }

  template <class T, class Comparator>
  void MaxHeap<T, Comparator>::exchange(vector<int>& vec, int i, int j){
    int temp = vec[i];
    vec[i] = vec[j];
    vec[j] = temp;
  }

  template <class T, class Comparator>
  void MaxHeap<T, Comparator>::add(const T & item){
    if(isEmpty()){
      _heap.push_back(item);
      return;
    }

    else{
      _heap.push_back(item);
      int childIndex = _heap.size()-1;
      int parentIndex = floor( (childIndex-1)/_d );

      while((parentIndex >=0) && (_heap[childIndex] > _heap[parentIndex])){
        exchange(_heap, childIndex, parentIndex);
        childIndex = parentIndex;
        parentIndex = floor( (childIndex-1)/_d );
      }
      // cout<<"print after rearrangement:  "<<endl;
      // print();
    }

  }

  template <class T, class Comparator>
  const T & MaxHeap<T, Comparator>::peek () const{
    if(isEmpty()) throw false;
    else return _heap[0];

  }

  template <class T, class Comparator>
  void MaxHeap<T, Comparator>::remove(){
    exchange(_heap, 0, _heap.size()-1);
    _heap.pop_back();
    trickleDown(0);

  }

  template <class T, class Comparator>
  void MaxHeap<T, Comparator>::trickleDown (T pos){
    if( ifChildren(pos) ){

      int highIndex = _d*pos+1; // initialize highest priority to be the first child

      for(int find =1; find <=_d; find++){
        if( ((_d*pos+find) <_heap.size()) && _heap[_d*pos+find] > _heap[pos] ){
          highIndex = _d*pos+find;
        }
      }

      if(_heap[highIndex] > _heap[pos]) {
        exchange(_heap, highIndex, pos);
        trickleDown(highIndex);
      }

    }
  }

  template <class T, class Comparator>
  bool MaxHeap<T, Comparator>::ifChildren(int pos){ // returns false if leaf
    if( (_d*pos+1) <_heap.size()) {return true;}
    else return false;
   
  }

  template <class T, class Comparator>
  bool MaxHeap<T, Comparator>::isEmpty(){
    if(_heap.size() ==0) return true;
    else return false;

  }


int main(int argc, char const *argv[])
{ 

  srand(time(0));
  intCompare comp;
  

  int tests[7] = {2,3,4,10,20,50,100}; // test different value of d's
  int degreeIndex =0;

  while(degreeIndex <7){

    MaxHeap<int, intCompare> firstHeap(tests[degreeIndex], comp);
    cout<<"Current d= "<< tests[degreeIndex]<<endl;
    double avgInsertion;
    double avgRemoval;

    for(int i =0; i<10; i++){ // 10 trials to homogenize result

      clock_t start=0, finish=0;
      double dur=0;
      start = clock();

      for (int j = 0; j <10000; j++){ // add 100000 integers
        int rand1 = rand()% 100;
        firstHeap.add(rand1);
      }

      finish = clock();
      dur = (double)(finish - start);
      dur /= CLOCKS_PER_SEC;
      //cout <<"insertion time: "<< dur << endl;

      avgInsertion += dur;


      start=0, finish=0, dur=0;
      start = clock();

      for (int k =0; k <10000; k++ ) {firstHeap.remove(); }// remove all

      finish = clock();
      dur = (double)(finish - start);
      dur /= CLOCKS_PER_SEC;
      //cout <<"removal time: "<< dur << endl;

      avgRemoval += dur;
    
    }
    avgInsertion = avgInsertion/10;
    avgRemoval = avgRemoval/10;

    cout<<"Average Insertion time is: "<<avgInsertion<<endl;
    cout<<"Average Removal time is: "<<avgRemoval<<endl;


    degreeIndex++;
  }

  return 0;
}