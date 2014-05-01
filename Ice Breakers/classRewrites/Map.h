// necessary includes can go here
#ifndef MAP_H
#define MAP_H
#include <iostream>
//#include "List.h"
using namespace std;


template <class keyType, class valueType>
struct Pair{
  keyType email;
  valueType name;
};

template <class keyType, class valueType>
class Map
{
  public:
    Map ();  // constructor for a new empty map

    ~Map (); // destructor

    int size () const; // returns the number of key-value pairs

    void add (const keyType & key, const valueType & value); 
      /* Adds a new association between the given key and the given value.
         If the key already has an association, it should throw an exception.
      */
 
    void remove (const keyType & key);
     /* Removes the association for the given key.
        If the key has no association, it should throw an exception. */

    valueType& get (const keyType & key) const;
     /* Returns the value associated with the given key.
        If the key has no association, it should throw an exception. */
    int find(const keyType & key) const;

  private:
    List<Pair <keyType, valueType> > internalStorage;

};


    template <class keyType, class valueType>
    Map<keyType, valueType>::Map() {;
    }

    template <class keyType, class valueType>
    Map<keyType, valueType>::~Map (){

    }

    template <class keyType, class valueType>
    int Map<keyType, valueType>::find(const keyType & key) const{ // find the position
      int position=0;
      //Pair <keyType,valueType> *iterate = internalStorage.get(position);
      while(position<internalStorage.size() && internalStorage.get(position).email != key ){
        //cout<<"position: "<<position<<endl;
        position++;
      }
      
      if(position >= internalStorage.size()){ // check found
      return -1;
      } 

      else{
        return position;
      }

    }

    template <class keyType, class valueType>
    void Map<keyType, valueType>::add (const keyType & key, const valueType & value){
      Pair<keyType,valueType> new1;
      new1.email=key;
      new1.name=value;
      internalStorage.insert(0,new1);          
    }
      

    template <class keyType, class valueType>
    void Map<keyType, valueType>::remove (const keyType & key){

      internalStorage. remove(find(key));
        
    }

    template <class keyType, class valueType> 
    valueType& Map<keyType, valueType>::get (const keyType & key) const{

      int position = find(key);
      //cout<<"position in get: "<<position<<endl;
      if(position == -1) throw position;    
      
      else{
        return internalStorage.get(position).name;
      }
        
    }

    #endif