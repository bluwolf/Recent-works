#ifndef SET_H
#define SET_H
#include <iostream>
//#include "List.h"
#include <set>
using namespace std;

template <class T>
class Set : public set<T>
{
  public: 
    Set ();            
    //Set (const Set<T> & other); 
    ~Set ();                  
;

     void print ();

    Set<T> setIntersection (const Set<T> & other) const;

    Set<T> setUnion (const Set<T> & other) const;

    bool contains (T search) const;

};

  template <class T>
  Set<T>::Set (){
  }

  template <class T>
  Set<T>::~Set (){

  }

  template <class T>
  void Set<T>::print (){
  typename Set<T>::iterator it;

  for(it = this->begin(); it != this->end(); it++) cout<<*it<<" ";
    
  }


  template <class T>
  bool Set<T>:: contains (T search) const
  {
    return this->find(search) != this->end();
  }

  template <class T>
  Set<T> Set<T>::setIntersection (const Set<T> & other) const{

     if(other.empty()) throw other.empty(); // if empty then no intersection
     
     Set<T> findIntersection; // initialize
     typename Set<T>::iterator iterateThis = this->begin();
     typename Set<T>::iterator iterateOther = other.begin();


     while (iterateOther != other.end()){ 
        while (iterateThis != this->end()){
          if(*iterateThis == *iterateOther){
            findIntersection.insert(*iterateOther);
            break;
          }
          iterateThis++;
        }
        iterateOther++;
      }
      findIntersection.print();
      return findIntersection;
    }


  template <class T>
  Set<T> Set<T>::setUnion (const Set<T> & other) const{

    Set<T> findUnion; // initialize
    typename Set<T>::iterator iterateThis;
    iterateThis = this->begin();
    typename Set<T>::iterator iterateOther;
    iterateOther = other.begin();

    while (iterateOther != other.end()){
      if(!findUnion.find(iterateOther) ){//problems
            findUnion.add(iterateOther);
        }
        iterateOther++;
    }

    while (iterateThis != this->end()){ 
      if(!findUnion.find(iterateThis)){
        findUnion.add(iterateThis);
      }
      iterateThis++;
    }

      //findUnion.print();
      return findUnion;
    }


  #endif