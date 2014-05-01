CC=g++

CFLAGS=-c -g -Wall -std=c++11

all: Quicksort Grading MaxHeap

Quicksort: Quicksort.o
	$(CC) Quicksort.o -o Quicksort


Quicksort.o: Quicksort.cpp
	$(CC) $(CFLAGS) Quicksort.cpp

Grading: Grading.o
	$(CC) Grading.o -o Grading


Grading.o: Grading.cpp
	$(CC) $(CFLAGS) Grading.cpp

MaxHeap: MaxHeap.o
	$(CC) MaxHeap.o -o MaxHeap


MaxHeap.o: MaxHeap.cpp
	$(CC) $(CFLAGS) MaxHeap.cpp


clean: 
	rm -rf *o Qucksort Grading MaxHeap