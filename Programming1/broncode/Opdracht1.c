#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h> 

// global vars
float average = 0;// the avarage 
float currentSpeed = 0;// the input

// Methods 
void instructionList();
void startProgram();    
void robotSpeed(float speed);
int repeat();

//the main
int main(){
    bool again = true;
    printf("This is a program to calculate the average speed from a robot, and shows what gear the robot is in.\n");
    instructionList();

    // if agian is true repeat program
    while(again == true){
        startProgram();// Start program
        again = repeat();// Set again to the ouput of the method repeat
        average = 0;// set the average to to 0 in case you want to repeat the program
    } 
    return 0;
}

// Ask if you want to see the insturctions of the program
void instructionList(){
    char answer;   
    while (true){
        printf("Do you want to see instructions for this program (y/n): ");
	    scanf(" %s",  &answer);
        printf("\n");
        if(answer == 'y'){
            printf("Instruction list for this program.\n");
            printf("1: No characters are allowed exccept: q . ,\n");
            printf("2: In the event that the input has a character  that's not allowed the input is set to 0, this means the gear automatically  goes to free.\n");
            printf("3: If there is any q in the input the program stops and calculates  the average.\n");
            printf("4: The min is -100 and the max speed is 100.\n");
            printf("5: In the event that the input exceeds those values its automatically  set to the closest min or max speed.\n");
            printf("6: If the first input is q it counts as 0, this means the gear automatically  goes to free.\n");
            printf("\n");
            break;
        }else if(answer == 'n'){
            break;
        }
    }
}

// starts the program
void startProgram(){
    char buffer[20];// input string buffer
    short int i;// countin int
    printf("Starting the program:\n");

    for (i = 0; i < 10; i++){
        printf("Input speed %d: " , i+1);
        scanf("%s", &buffer);// set the input to buffer

        // check if q is in buffer if it is stops startProgram
        if (strchr(buffer, 'q') != NULL && i != 0) {
            break;
        }
        currentSpeed = (float)atof(buffer);// cast buffer to an float
        robotSpeed(currentSpeed);
        average = average + currentSpeed;// count currentSpeed by average
    }
    printf("The average  is: %.2f \n", average/i);// calculats the average
    printf("\n");
}

// gives gear number
void robotSpeed(float speed){
    if (0 < speed && speed < 10){
        printf("The gear is: 1\n");
    }else if(10 <= speed && speed < 30){
        printf("The gear is: 2\n");
    }else if(30 <= speed && speed < 60){
        printf("The gear is: 3\n");
    }else if(60 <= speed && speed < 80){
        printf("The gear is: 4\n");
    }else if(speed < 0 && speed >= -100){
        printf("The gear is: R\n");
    }else if(speed >= 80 && speed <= 100){
        printf("The gear is: 5\n");
    }else if(speed == 0){
        printf("The gear is: free\n");
        currentSpeed = 0;
    }else if(speed < -100){
        printf("It cant go faster in reverse then -100\n");
        currentSpeed = -100;
        printf("Speed is automaticly set to maxed allowed reverse speed: -100\n");
        printf("The gear is: R\n");
    }else if(speed > 100){
        printf("It cant go faster then 100\n");
        currentSpeed = 100;
        printf("Speed is automaticly set to maxed allowed speed: 100\n");
        printf("The gear is: 5\n");
    }else{
        printf("Not available input\n");
    }
    printf("\n");
}

// asks if you want to repeat the program
int repeat(){
    char answer;
    while(true){ 
        printf("Do you want to repeat the program (y/n): ");
	    scanf(" %s",  &answer);
        if(answer == 'y'){
            printf("\n");
            return true;
        }else if(answer == 'n'){
            return false;
        }
    }
}