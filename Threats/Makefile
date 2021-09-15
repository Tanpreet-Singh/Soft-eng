INC = ./includes
SRC = ./sources
OBJ = ./objects

SOURCES = main.cpp
OBJECTS = $(wildcard *.o)

CFLAGS = `wx-config --cxxflags --libs`
CXX = g++

main: main.o
	$(CXX) main.o $(CFLAGS) -o main

main.o:
	$(CXX) $(CFLAGS) -c $(SRC)/$(SOURCES)

clean:
	rm -f *.o
