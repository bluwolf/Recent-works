CC=g++

CFLAGS=-c -g -Wall -std=c++11

all: main

main: main.o
	$(CC) main.o -o bin/main


main.o: main.cpp
	$(CC) $(CFLAGS) main.cpp


clean: 
	rm -rf *o bin/main