#ifndef LIST_H
#define LIST_H
#include <iostream>

using namespace std;

template <class T>
struct Item
{
  T value;
  Item<T> *prev, *next;
};

template <class T>
class List
{
  public:
    List ();   // constructor

    ~List ();  // destructor

    int size () const;  // returns the number of elements in the list

    void insert (int position, const T & val);
      // Inserts val into the list before the given position,
        // shifting all later items one position to the right.


    void remove (int position);
      //Removes the item from the given position, shifting all
         //later items one position to the left.


    void set (int position, const T & val);
      //Overwrites the given position with the given value.  

    T& get (int position) const;
      //Returns the item at the given position.




    void print() ;

    private:
      Item<T> *head;

      };


    template <class T>
    List<T>::List() {
        head = NULL;
    }

    template <class T>
    List<T>::~List (){
        Item<T>* temp = head;

        while(temp->next !=NULL){
                temp =temp->next;
                delete temp->prev;
            }
        delete temp;

    }

    template <class T>
    int List<T>::size () const{
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
    void List<T>::print (){
        if (head == NULL) return;

        Item<T> *temp = head;
        cout<<temp->value<<" "; 
        while (temp->next){
            temp = temp->next;
            cout<<temp->value<<" "; 
        }
        cout<<endl;
    }


    template <class T>
    void List<T>::insert (int position, const T & val){
           
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
    void List<T>::remove (int position){

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

            if(temp->prev != NULL){ // beginning check
                temp->prev->next = temp->next;
            }
            else{
                head = temp->next;
            }

            if(temp->next != NULL){// end check
                temp->next->prev = temp->prev;
            }
            else{
                temp->prev->next = NULL;
            }
            delete temp;

        }

    }
      

    template <class T>
    void List<T>::set (int position, const T & val){

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
    T& List<T>::get (int position) const{
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

      
   /* int main(int argc, char const *argv[])
    {
        List<int> list;
       // bool flag =1;

       // while(1){

        list.insert(0,1);
        list.insert(1,5);
        list.insert(2,8);
        list.print();
        list.set(0,6);
        list.print();
        list.remove(0);
        list.print();
        list.remove(0);
        list.print();
        list.remove(0);
        list.print();
        
    // }
        return 0;
    }*/
#endif