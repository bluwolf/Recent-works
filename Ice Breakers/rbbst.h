/*
 * rbbst.h
 *
 *  Created on: Apr 14, 2014
 *      Author: kempe
 */

#include <iostream>
#include <exception>
#include <cstdlib>
#include "bst.h"

/* -----------------------------------------------------
 * Red-Black Nodes 
 ------------------------------------------------------*/

enum Color {red, black};

template <class KeyType, class ValueType>
class RedBlackNode : public Node<KeyType, ValueType>
{
	public:
		RedBlackNode (KeyType k, ValueType v, RedBlackNode<KeyType, ValueType> *p)
		: Node<KeyType, ValueType> (k, v, p)
		  { color = red; }

		virtual ~RedBlackNode () {}

		Color getColor () const
		{ return color; }

		void setColor (Color c)
		{ color = c; }

	        /* The type casting allows us to treat getParent, getLeft, getRight
	           as returning RedBlackNode instead of Node */
		virtual RedBlackNode<KeyType, ValueType> *getParent () const
		{ return (RedBlackNode<KeyType,ValueType>*) this->_parent; }

		virtual RedBlackNode<KeyType, ValueType> *getLeft () const
		{ return (RedBlackNode<KeyType,ValueType>*) this->_left; }

		virtual RedBlackNode<KeyType, ValueType> *getRight () const
		{ return (RedBlackNode<KeyType,ValueType>*) this->_right; }

	protected:
		Color color;
};

/* -----------------------------------------------------
 * Red-Black Search Tree
 ------------------------------------------------------*/

template <class KeyType, class ValueType>
class RedBlackTree : public BinarySearchTree<KeyType, ValueType>
{
	public:
		void add (KeyType key, ValueType value);

		void print () const;

		void printRoot (RedBlackNode<KeyType, ValueType> *r) const;

		RedBlackNode<KeyType, ValueType>* grandparent(RedBlackNode<KeyType, ValueType> *n);

		Color uncleColor(RedBlackNode<KeyType, ValueType> *n);
		RedBlackNode<KeyType, ValueType>* uncle(RedBlackNode<KeyType, ValueType> *n);

		void leftRotate(RedBlackNode<KeyType, ValueType> *n);
		void rightRotate(RedBlackNode<KeyType, ValueType> *n);

		void fixTree(RedBlackNode<KeyType, ValueType> *n);

		class iterator { // copied iterator here for convenience (cause of node type problem)
				public:
				  iterator(RedBlackNode<KeyType,ValueType>* root) 
				    {
					    curr = root;
					    if(curr){
							while(curr->getLeft()){
							  curr = curr->getLeft();
							}
					    }
					 }

					  const std::pair<KeyType,ValueType>& operator*() const
					    { return curr->getItem(); }

					  const std::pair<KeyType,ValueType>* operator->() const
					    { return &(curr->getItem()); }

					  bool operator==(const iterator& rhs) const
					  {
					    return curr == rhs.curr; 
					  }

					  bool operator!=(const iterator& rhs) const
					  {
					    return curr != rhs.curr;
					  }
				//*******************************++ implemented here
					  iterator& operator++(){
					  	if(curr){

					  		if(curr->getRight()){ // if right child exist
					  			curr= curr->getRight();
					  			while(curr->getLeft()){
					  				curr = curr->getLeft();
					  			}
					  			return *this;
					  		}

					  		if((!curr->getRight())){// if no right descendent
					  			RedBlackNode<KeyType, ValueType>* parent = curr->getParent();

					  			while(parent != NULL && curr == parent->getRight()){
					  				curr = parent;
					  				parent = parent->getParent();
					  			}

					  			curr = parent;
					  			return *this;
					  		}
					  	}
					  	throw;
					  }
					  
					protected:
					  RedBlackNode<KeyType, ValueType>* curr;
			};
			iterator begin() { iterator it (root); return it; }
			iterator end()   { iterator it (NULL); return it; }

	private:
		RedBlackNode<KeyType, ValueType> *root;
};


template <class KeyType, class ValueType>
void RedBlackTree<KeyType, ValueType>::add (KeyType key, ValueType value){
		RedBlackNode<KeyType, ValueType>* temp;

	 	if(!root){ // if tree is empty
	 			//std::cout<<"adding root"<<std::endl;
		  		temp = new RedBlackNode<KeyType, ValueType>(key,value,NULL);
		  		root = temp;
		  	}

		else{ // tree not empty
			//std::cout<<"In recurse"<<std::endl;
			RedBlackNode<KeyType, ValueType>* next = root;
			RedBlackNode<KeyType, ValueType>* current = root;
			
			while(1){

				if(key > next->getKey()){ // move right and stop beyond the correct leaf node
					current = next;
					next = next->getRight();
					if(!next){
						temp = new RedBlackNode<KeyType, ValueType>(key,value,current);
						current->setRight(temp);
						break;
					}
				}

				if(key < next->getKey()){ // move right, same as above
					current = next;
					next = next->getLeft();
					if(!next){
						temp = new RedBlackNode<KeyType, ValueType>(key,value,current);
						current->setLeft(temp);
						break;
					}
				}

				if(key == next->getKey()) {
					std::cout<<"invalid insert: duplicate key"<<std::endl; throw;
				}
			}
		}
		// if(root != temp){
		// 	std::cout<<"I'm here"<<std::endl;
		// 	fixTree(temp);
		// }


}

template <class KeyType, class ValueType>
void RedBlackTree<KeyType, ValueType>::print () const{
	printRoot (root);
	std::cout << "\n";

}


template <class KeyType, class ValueType>
void RedBlackTree<KeyType, ValueType>::printRoot (RedBlackNode<KeyType, ValueType> *r) const{
	if (r != NULL){
	    std::cout << "[";
	    printRoot (r->getLeft());
	    std::cout << " (" << r->getKey() << ", " << r->getValue() << ") ";
    	printRoot (r->getRight());
    	std::cout << "]";
	 }
}

template <class KeyType, class ValueType>
Color RedBlackTree<KeyType, ValueType>::uncleColor(RedBlackNode<KeyType, ValueType> *n){
	if(uncleExists(n)){
		if (  ((n->getParent())->getParent())->getLeft() == n->getParent() ){
			return (((n->getParent())->getParent())->getRight())->getColor();
		}

		if (  ((n->getParent())->getParent())->getRight() == n->getParent() ){
			return (((n->getParent())->getParent())->getLeft())->getColor();
		}
	}
	throw;
}
template <class KeyType, class ValueType>
RedBlackNode<KeyType, ValueType>* RedBlackTree<KeyType, ValueType>::grandparent(RedBlackNode<KeyType, ValueType> *n){
	if(n!=NULL && n->getParent() != NULL) return n->getParent()->getParent();
	else return NULL;
}



template <class KeyType, class ValueType>
RedBlackNode<KeyType, ValueType>* RedBlackTree<KeyType, ValueType>::uncle(RedBlackNode<KeyType, ValueType> *n){
	RedBlackNode<KeyType, ValueType> *g = grandparent(n);
	if(!g) return NULL;
	if(n->getParent() == g->getLeft()) return g->getRight();
	else return g->getLeft();

	// if (  !((n->getParent())->getParent()) ) return false;
	// if (  ((n->getParent())->getParent())->getLeft() == n->getParent() ) return  ((n->getParent())->getParent())->getRight();
	// if (  ((n->getParent())->getParent())->getRight() == n->getParent() ) return  ((n->getParent())->getParent())->getLeft();
	// throw;
}


template <class KeyType, class ValueType>
void RedBlackTree<KeyType, ValueType>::leftRotate(RedBlackNode<KeyType, ValueType> *n){
	RedBlackNode<KeyType, ValueType> *parent = n->getParent(); // rotate from the parent
	RedBlackNode<KeyType, ValueType> *gParent = (n->getParent())->getParent(); // get grandparent
	RedBlackNode<KeyType, ValueType> *ggParent;
	if(((n->getParent())->getParent())->getParent()){
	 ggParent= ((n->getParent())->getParent())->getParent(); // get grand grand parent
	}
	ggParent = NULL;

	gParent->setRight(parent->getLeft()); // break parent's left child link and rechain it to grandparent's right link
	if(parent->getLeft()) (parent->getLeft())->setParent(gParent);

	gParent->setParent(parent); // start left rotation	
	parent->setLeft(gParent);

	parent->setParent(ggParent); // fix link between ggparent and parent
	if(ggParent){
		if(ggParent->getLeft() == gParent) ggParent->setLeft(parent);
		if(ggParent->getRight() == gParent) ggParent->setRight(parent);
	}

}

template <class KeyType, class ValueType>
void RedBlackTree<KeyType, ValueType>::rightRotate(RedBlackNode<KeyType, ValueType> *n){
	RedBlackNode<KeyType, ValueType> *parent = n->getParent(); // rotate from the parent
	RedBlackNode<KeyType, ValueType> *gParent = (n->getParent())->getParent(); // get grandparent
	RedBlackNode<KeyType, ValueType> *ggParent;
	if(((n->getParent())->getParent())->getParent()){
	 ggParent= ((n->getParent())->getParent())->getParent(); // get grand grand parent
	} 

	gParent->setLeft(parent->getRight()); // break parent's right child link and rechain it to grandparent's left link
	if(parent->getRight()) (parent->getRight())->setParent(gParent);

	gParent->setParent(parent); // start right rotation	
	parent->setRight(gParent);

	parent->setParent(ggParent); // fix link between ggparent and new grand parent
	if(ggParent){
		if(ggParent->getLeft() == gParent) ggParent->setLeft(parent);
		if(ggParent->getRight() == gParent) ggParent->setRight(parent);
	}


}



template <class KeyType, class ValueType>
void RedBlackTree<KeyType, ValueType>::fixTree(RedBlackNode<KeyType, ValueType> *n){

	if(!n->getParent() && n== root) return;
	if(root->getColor() == red) root->setColor(black); // first case root red

	else if(n->getColor() == red && uncle(n) && uncle(n) == red){
		if(n== ((n->getParent())->getLeft()) ) { // first case red uncle
			RedBlackNode<KeyType, ValueType> *gParent = (n->getParent())->getParent();
			(gParent->getLeft())->setColor(black);
			(gParent->getRight())->setColor(black);
			gParent->setColor(red);
			fixTree(gParent);
		}

		if(n== ((n->getParent())->getRight()) ) { // second case red uncle
			RedBlackNode<KeyType, ValueType> *gParent = (n->getParent())->getParent();
			(gParent->getLeft())->setColor(black);
			(gParent->getRight())->setColor(black);
			gParent->setColor(red);
			fixTree(gParent);
			
		}
	}

	else if(!uncle(n) || uncle(n) == black){ //first case black uncle
		if(n == (n->getParent())->getLeft()  ){
			RedBlackNode<KeyType, ValueType> *gParent = (n->getParent())->getParent();
			(n->getParent())->setColor(black);
			gParent->setColor(red);
			rightRotate(n);
			fixTree(n->getParent());

		}
		if(n == (n->getParent())->getRight()  ){
			leftRotate(n);
			n->setColor(black);
			(n->getParent())->setColor(red);
			rightRotate(n);
			fixTree(n->getParent());


		}

	}

	



}