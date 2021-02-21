#include <stdio.h>
#include <stdbool.h> 
#include <math.h>
#include "lenaArray.h"

//for row use LEN(array) and for cols use LEN(array[0])
#define LEN(arr) ((int) (sizeof (arr) / sizeof (arr)[0]))

// symbols for ascii conversing
char symbols[10] = " .:-=+*#&@";

// To set the direction of the createSubSet functie
short int direction = 1;

// conversning quility parmeters
int smallRow = 10;
int smallCol = 5;
 
// functies
void instructionList();
void averageGreyScale();
void converse();
void printImg();
void printImg();
void createSubSet();
void reverse();

//the main
int main(void){
    int answer;
    printf("Welcome to the glories ascii example converse program made by Collin Knuit.\n");
    printf("Unfortunately the program has only 1 example picture at the moment: lena\n");
    printf("To see the instruction list for this program type: 1.\n");
    printf("\n");
    while(true){
        printf("What do you want to choose: ");
	    scanf("%d",  &answer);
        switch(answer){
            case 1: // instruction list
                printf("Instruction list for this program.\n");
                printf("1: The instruction list of this program.\n");
                printf("2: The average Greyscale of lena.\n");
                printf("3: Printing out lena.\n");
                printf("4: Printing out a subset of lena.\n");
                printf("5: Calculating the average GreyScale of the subset.\n");
                printf("6: To reverse and print out lena.\n");
                printf("7: To set the conversing standard high.\n");
                printf("8: To set the conversing standard low(normal standard).\n");
                printf("0: To quit the program.\n");
                break;

            case 2:// calculate the average greyscale of lena
                averageGreyScale(&lena,LEN(lena), LEN(lena[0]));
                break;

            case 3:// prints the lenaArray into ascii
                converse(&lena,LEN(lena), LEN(lena[0]));
                break;
                
            case 4:// prints the subset into ascii
                direction = 1;
                createSubSet(&lena, LEN(lena), LEN(lena[0]), 100, 400, 100, 400) ;
                break;

            case 5:// calculates the average greyscale from the subset
                direction = 0;
                createSubSet(&lena, LEN(lena), LEN(lena[0]), 100, 400, 100, 400) ;
                break;

            case 6: // reverses the img and prints it
                reverse(&lena, LEN(lena), LEN(lena[0]));
                break;

            case 7:// set conversing quality to high
                printf("High quality conversing selected\n");
                printf("Requires the terminal to be Fullscreen\n");
                smallRow = 6;
                smallCol = 2;
                break;

            case 8:// set conversing quality to low
                printf("Low quality conversing selected\n");
                smallRow = 10;
                smallCol = 5;
                break;

            case 0:// exits program
                return 0;
                break;

            default:
            printf("Error! operator is not correct try again.\n");
            break;
        }
        printf("\n");
        while ((getchar()) != '\n'); // cleans input
    }
    return 0;
}

// converse GreyScale into ascii
void converse(int *ptr, int rows, int cols){

    // create array for the chars dynamicly depending on the quility selected
    int totalRow = (int)ceil(rows/smallRow)+1, totalCol = (int)ceil(cols/smallCol)+1;
    char img[totalRow][totalCol];

    for (int i = 0; i <= rows; i+=smallRow) {
        for (int j = 0; j <= cols; j+=smallCol) {

            //The formula from ptr to var = *(ptr + (i x no_of_cols + j))
            int whichAsci = *(ptr +(i * cols + j));

            int a = ceil(i/smallRow);
            int b = ceil(j/smallCol);
            if(whichAsci <= 25 ){
                img[a][b] = symbols[0];

            }else if(whichAsci > 25 && whichAsci <= 51){
                img[a][b] = symbols[1];

            }else if(whichAsci > 51 && whichAsci <= 76){
                img[a][b] = symbols[2];

            }else if(whichAsci > 76 && whichAsci <= 102){
                img[a][b] = symbols[3];

            }else if(whichAsci > 102 && whichAsci <= 127){
                img[a][b] = symbols[4];

            }else if(whichAsci > 127 && whichAsci <= 153){
                img[a][b] = symbols[5];

            }else if(whichAsci > 153 && whichAsci <= 178 ){
                img[a][b] = symbols[6];
                
            }else if(whichAsci > 178 && whichAsci <= 204){
                img[a][b] = symbols[7];

            }else if(whichAsci > 204 && whichAsci <= 229){
                img[a][b] = symbols[8];

            }else if(whichAsci <= 255){
                img[a][b] = symbols[9];
            }
        }
    }
    printImg(&img, LEN(img), LEN(img[0]));
}

// print the average greyscale of a picture
void averageGreyScale(int *ptr, int rows, int cols){
    double sum = 0,sumColum=0, result = 0;
    
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {

            //The formula from ptr to var = *(ptr + (i x no_of_cols + j))
            sumColum += *(ptr +( i * cols + j));
        }
        sum += (sumColum/cols);
        sumColum = 0;
    }
    result = round(sum/rows);
    
    printf("The average greyscale of the array is: %f \n",result);
}

// prints the img
void printImg(char *ptr, int rows, int cols){
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {

            //The formula from ptr to var = *(ptr + (i x no_of_cols + j))
            printf("%c", *(ptr + (i * cols + j)));               
        }
        printf("\n");
    }
}

// creates the subset and acording to the direction var prints it or calculates the average greyscale
void createSubSet(int *ptr, int rows, int cols, int beginRow, int endRow, int beginCol, int endCol){

    // dynamicly calculates arraysize for the subset
    int sizeRow = endRow - beginRow+1,  sizeCol = endRow - beginRow+1;
    int subset[sizeRow][sizeCol];

    for (int i = beginRow; i <= endRow; i++) {
        for (int j = beginCol; j <= endCol; j++) {
            int a = i -beginRow, b = j-beginCol;

             //The formula from ptr to var = *(ptr + (i x no_of_cols + j))
            subset[a][b] = *(ptr +( i * cols + j));
        }
    }
    if(direction == 1){
        converse(&subset[0][0],LEN(subset), LEN(subset[0]));
    }else{
        averageGreyScale(&subset[0][0],LEN(subset), LEN(subset[0]));
    }
}

// reverses the img
void reverse(int *ptr, int rows, int cols){

    // dynamicly calculates arraysize
    int subset[rows][cols];
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            int a = cols-j;

            //The formula from ptr to var = *(ptr + (i x no_of_cols + j))
            subset[i][a] = *(ptr +( i * cols + j));
        }  
    }
    converse(&subset[0][0],LEN(subset), LEN(subset[0]));
}