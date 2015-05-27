
CC=g++

CFLAGS=-c -Wall

all: mapTest

main: mapTest.o
	$(CC) mapTest.o -o mapTest

main.o: main.cpp
	$(CC) $(CFLAGS) mapTest.cpp

clean: 
	rm -rf *o mapTest
