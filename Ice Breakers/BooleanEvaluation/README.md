
+ Type "make" in main menu to activate makefile for Stack.cpp
+ Implements a "Stack" by inheriting from a self-wrote linked list

+ Purpose of Stack.cpp
	+ Evaluates a parse through a boolean expression and evaluate value and validity
	+ first command line argument: text file that contains the boolean expression
	+ second command line argument: text file that contains the key to the expressions in the boolean expressions
		+ sample boolean expressions: (14 &(2|3 ), ~~(2 & 7& ( ~5000000000 |~0))
		+ sample key: 0 F; 5000000000 T; 14 T