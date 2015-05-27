#ifndef LIST_H
#define LIST_H
#include <iostream>
#include "ilist.h"

using namespace std;

template <class T>
struct Item
{
  T value;
  Item<T> *prev, *next;
};

template <class T>
//unbounded, dynamic array-based list
class LinkedList : public IList<T>
{
  public:
    LinkedList();   // constructor

    virtual ~LinkedList ();  // destructor

    int size () const;  // returns the number of elements in the list

    void insert (int position, const T & val);

    void remove (int position);

    void set (int position, const T & val);

    T& get (int position) const;

    void print() ;
    int capacityP(){return size()-1;}

    LinkedList (const LinkedList<T> & other);

    LinkedList<T> & operator= (const LinkedList<T> & other);

    private:
      Item<T> *head;
      };


    template <class T>
    LinkedList<T>::LinkedList() {
        head = NULL;
    }

    template <class T>
    LinkedList<T>::~LinkedList (){
        if(head !=NULL){
            Item<T>* temp = head;

            while(temp->next !=NULL){
                    temp =temp->next;
                    delete temp->prev;
                }
            delete temp;     
        }
    }
    //copy constructor
    template <class T>
    LinkedList<T>::LinkedList (const LinkedList<T> & other){

        Item<T>* temp = other.head;

            //initialize head
            head = new Item<T>; 
            head->value = temp->value;
            head->prev = NULL;
            head->next = NULL;
            Item<T>* previous = head;


            // copu the whole list
            while(temp->next){
                temp = temp->next;// renew
                Item<T> *newItem = new Item<T>; 
                newItem->value = temp->value;
                newItem->prev = previous;
                newItem-> next = NULL;
                previous->next = newItem;
                previous = previous->next;
            }

}


    // operator = overload
    template <class T>
    LinkedList<T> & LinkedList<T>::operator= (const LinkedList<T> & other){
        if(this == &other) return *this;
        
        Item<T>* temp = other.head;

            //initialize head
            head = new Item<T>; 
            head->value = temp->value;
            head->prev = NULL;
            head->next = NULL;
            Item<T>* previous = head; // keep track of previous item


            // copu the whole list
            while(temp->next){
                temp = temp->next;// renew
                Item<T> *newItem = new Item<T>; 
                newItem->value = temp->value;
                newItem->prev = previous;
                newItem-> next = NULL;
                previous->next = newItem;
                previous = previous->next;
            }
        return *this;

    }


    template <class T>
    int LinkedList<T>::size () const{
        if(head == NULL) return 0;

            else{
            int size=1; 
            Item<T>* temp = head;
            while (temp->next){
                temp = temp->next;
                size++; 
            }
            return size;
        }

    }


    template <class T>
    void LinkedList<T>::print (){
        if (head == NULL) return;

        Item<T> *temp = head;
        cout<<temp->value<<" "; 
        while (temp->next){
            temp = temp->next;
            cout<<temp->value<<" "; 
        }
        //cout<<endl;
    }


    template <class T>
    void LinkedList<T>::insert (int position, const T & val){
           
        //error check
        if(position < 0 || position > size()) throw position;

        Item<T> *newItem = new Item<T>; // start initializing new node
        newItem->value = val;


        if (head == NULL)
        {
            newItem->next = NULL;
            newItem->prev = NULL;
            head = newItem;
        }
       else if (position == 0){
        newItem->next = head;
        newItem->prev = NULL;
        head->prev = newItem;
        head = newItem;

       }


        else{
            int count = 0; // find the node that the position is pointing to
            Item<T> *temp = head;
            while (count !=position-1){
                temp = temp->next;
                count++; 
            }

            newItem->prev = temp;
            newItem->next = temp->next;

            if(temp->next !=NULL){
                temp->next->prev= newItem;
            }
            temp->next = newItem; // link
             

        }

    }
      

    template <class T>
    void LinkedList<T>::remove (int position){

        //error check
        if(position < 0 || position >= size()) throw position;
        

        if (head == NULL)
        {
            cout<<"List is empty"<< endl;
        }

        else{
            int count =0; // find the node that the position is pointing to
           
            Item<T> *temp = head;
            while (count !=position){
                //cout<<position<<" "<<count<<endl;
                temp = temp->next;
                count++; 
            }


            if(temp->prev != NULL){ // beginning check
                temp->prev->next = temp->next;
            }
            else{
                head = temp->next;
            }
            if(temp->next != NULL){// end check
                temp->next->prev = temp->prev;
            }
            delete temp;

        }

    }
      

    template <class T>
    void LinkedList<T>::set (int position, const T & val){

        //error check
        if(position < 0 || position >= size()) throw position;
    

        if (head == NULL)
        {
            cout<<"List is empty"<< endl;
        }

        else{
            int count; // find the node that the position is pointing to
            Item<T> *temp = head;
            while (count !=position){
                temp = temp->next;
                count++; 
            }

            // overwirite
            temp->value = val;
        }


    }
   
    template <class T>  
    T& LinkedList<T>::get (int position) const{
        //error check
        if(position < 0 || position > size()) throw position;
        

        if (head == NULL)
        {
            cout<<"List is empty"<< endl;
            throw;
            
        }


        int count=0; // find the node that the position is pointing to
        Item<T> *temp = head;
        while (count !=position){
            temp = temp->next;
            count++; 
        }
        // return item;
        return temp->value;
           
    }

      
    // int main(int argc, char const *argv[])
    // {
    //     List<int> list;
    //    // bool flag =1;

       // while(1){

        // list.insert(0,1);
        // list.insert(1,5);
        // list.insert(2,8);
        // list.print();
        // list.set(0,6);
        // list.print();
        // list.remove(0);
        // list.print();
        // list.remove(0);
        // list.print();

        // List<int> list2 = list;
        // // list2.print();
        
        // list2.print();
        // list2.remove(0);
        // list.print();
        // list2.print();



        
    //     //}
    //     return 0;
    // }
#endif
