#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <string>
#include <sstream>
#include <vector>
/*
Programming Objective: Generate 4 color map
*/

using namespace std;   

bool checkValid(int** color, int** map, int rows, int cols){ 


    // country relationships

    int cells=0;

    for(int i = 0; i<rows;i++){ 
	   	for(int j = 0; j<cols; j++){
	   		bool north = true, south = true, west= true, east= true;
	   		if(color[i][j]!=0){
		   		if(i-1>0 && color[i-1][j]!=0){// north
		   			if(map[i][j]!= map[i-1][j]){
							north =color[i][j]!= color[i-1][j];
		   			}
		   		}

		   		if(j+1<cols && color[i][j+1]!=0){// east
		   			   	if(map[i][j]!=map[i][j+1]){
		   					east = color[i][j]!=color[i][j+1];
		   			}
		   		}

		   		   if(i+1<rows && color[i+1][j]!=0){// south
		   			   if(map[i][j]!= map[i+1][j]){
		   					south = color[i][j]!= color[i+1][j];
		   			}
		   		}

		   			if(j-1>0 && color[i][j-1]!=0){// west
		   			   if(map[i][j]!= map[i][j-1]){
		   					west = color[i][j]!= color[i][j-1];
		   				}
		   			}
	   		}

				if (north==true && east==true && west==true && south==true)
			   	{	
			   		++cells;
			   		//cout<<"cells: "<< cells<< endl;

			   	}

	   	}
   	}

   	if(cells== rows* cols){
   		return true;
   	}

   	else {
   		return false; 
   	}

}


bool search(int** color, int**map, int r, int c, int currentNum, int countryNum) // backtrack search
{
	//cout<<"currentNum: "<<currentNum<<endl;
	if( currentNum == countryNum ) // every cell colored
		return true;

// iterate through all options
	for(int option=1; option <= 4; option++){
		for(int i=0;i<r;i++){
	       	    for(int j=0;j<c;j++){
	                if(map[i][j]==currentNum){ //color all
	                    color[i][j]=option;
	                }
	            }
	         }

			if(checkValid(color,map, r, c)){	
				bool status = search(color, map, r,c, ++currentNum, countryNum);
				if (status) return status;
			}

			//else if not correct, reverse the effect
			for(int i=0;i<r;i++){
	             for(int j=0;j<c;j++){
	                if(map[i][j]==currentNum){
	                         color[i][j]=0;
	                }
	            }
	        }
	}        
return false;
}




int main(int argc, char const *argv[])
{
	if (argc!=2)
	{
		cout<<"please enter only one file name"<< endl;
		return 0;
	}

	ifstream ifile(argv[1]);
	int numCountries, rows, cols;
	ifile >> numCountries >> rows >> cols;


	int** map=new int *[rows]; // make separate 2-D array of map (country and color)

    for(int i=0;i<rows;i++){ // add colums to 1 dimensional
        map[i]=new int[cols];
    }

    for(int i = 0; i<rows;i++){ //write countries
    	for(int j = 0; j<cols; j++){
    		char charToNum;
    		ifile>>charToNum;
    		map[i][j]=charToNum-0x41; // 
    	}
    }

   /*for(int i = 0; i<rows;i++){ //cout countries for testing
    	for(int j = 0; j<cols; j++){
    		cout<<map[i][j];
    	}
    	cout<<endl;
    }
    cout<<endl;cout<<endl;*/


    //initializing colors
    int **color = new int*[rows];
    for(int i =0; i<rows; i++){
    	color[i]= new int[cols];
    }


    for(int i = 0; i<rows;i++){ //initialize relationships to 0
   		for(int j = 0; j<cols; j++){
   		color[i][j] =0;
   		}
    }


    	// haha..
   		char bank[26] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};


   	search(color, map,rows,cols,0, numCountries);// search
    
    for(int q=0;q<numCountries;q++){//print result
        int foundColor;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(map[i][j]==q){
                    foundColor=color[i][j];
                }
            }
        }
                
        cout<<bank[q]<<" "<<foundColor<<endl;
    }

    for(int i = 0; i<rows;i++){ //deallocate memory
		delete [] map[i];
		delete [] color[i];
	}

	return 0;
	//*/
}