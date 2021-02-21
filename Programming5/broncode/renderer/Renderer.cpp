//============================================================================
// Name        		: Collin Knuit
// Version     		: 1.0
// Description 		: Ray Tracing in C++, Ansi-style
//============================================================================


#include "Renderer.hpp"
#include <windows.h>
#include <iostream>
#include <cmath>

namespace display{

// constructors/destructors
Renderer::Renderer( Console &co,tracer::RayTracer &ray): co(&co), rayt(&ray){

	display.resize( (co.height/dH), std::vector<char>((co.width/dW)));
}

Renderer::Renderer( double r,double theta ,double phi, Console &co, tracer::RayTracer &ray) {
	*this = Renderer(co, ray);
	setXYZ(r, theta, phi);
}

Renderer::~Renderer() {
	// TODO Auto-generated destructor stub
}

Console::Console() {
}

Console::Console (short width, short height): width(width), height(height) {
}

Console::~Console() {
	// TODO Auto-generated destructor stub
}



// It fills the vector with pixels and then draws them
void Renderer::drawPicture(){

	auto width = co->width;
	auto height = co->height;
	rayt->setLimit(10);
	for(int i=0;i<height/dH;i++){
		for(int j=0;j<width/dW;j++){
			Vect3D dir(
					-((double)(j-width/dW/2)+0.5)/(double)(width/dW/2),
					((double)(i-height/dH/2)+0.5)/(double)(height/dH/2),
					-1
				);
			transformVector(dir);
			dir -= cam;
			auto norm = dir.normalize();
			double luminance= rayt->computeRay(altitude, cam, norm);
			rayt->setLimit(10);
			int color=(int)((strlen(palette)-1)*luminance);
			display[i][j]= palette[color];
		}
	}
	co->drawDisplay(display);

}

/**
 * This function is too get the point local coordinates
 */
void Renderer::transformVector(Vect3D &ray){

	double 	tx=ray.getX()*worldToCam[0][0]+ray.getY()*worldToCam[0][1]+ray.getZ()*worldToCam[0][2]+worldToCam[0][2],
			ty=ray.getX()*worldToCam[1][0]+ray.getY()*worldToCam[1][1]+ray.getZ()*worldToCam[1][2]+worldToCam[1][3],
			tz=ray.getX()*worldToCam[2][0]+ray.getY()*worldToCam[2][1]+ray.getZ()*worldToCam[2][2]+worldToCam[2][3];
	ray.changePos(tx, ty, tz);
}

/**
 * In place of going in a circle it goes in a elipse around the origin point
 * theta is camera's angle along the xy plane.
 * phi is camera's angle along z axis
 * r is the distance from the camera to the origin
 */
void Renderer::setXYZ(double r,double theta ,double phi){
	double a=sin(theta),b=cos(theta),c=sin(phi),d=cos(phi);
	cam.changePos(	r*b*d,
					r*a*d,
					r*c);

	worldToCam[3][3]=1;

	//x
	worldToCam[0][0]=-a;
	worldToCam[1][0]=b;
	worldToCam[2][0]=0;

	//y
	worldToCam[0][1]=b*c;
	worldToCam[1][1]=a*c;
	worldToCam[2][1]=-d;

	//z
	worldToCam[0][2]=b*d;
	worldToCam[1][2]=a*d;
	worldToCam[2][2]=c;

	worldToCam[0][3]=cam.getX();
	worldToCam[1][3]=cam.getY();
	worldToCam[2][3]=cam.getZ();
}


/*
 * Set cursor at start to avoid flickering
 * https://www.daniweb.com/programming/software-development/threads/360984/need-help-understanding-gotoxy
 */
void Console::gotoxy ( short x, short y ) {
	COORD coord = {x, y};
	SetConsoleCursorPosition ( GetStdHandle ( STD_OUTPUT_HANDLE ), coord );
}


// to be used to test if the vector had the right length
void Console::setupInitialDisplay(){

	for(int i=0;i<height/dH;i++){
		for(int j=0;j<width/dW;j++){
			std::printf("@");
		}
		std::printf("\n");
	}
	std::getchar();
	gotoxy(0,0);
}


// it draws the display and waits unitil the enter key is pressed
void Console::drawDisplay(std::vector<std::vector<char>> &display){

	for(int i=0;i<height/dH;i++){
		for(int j=0;j<width/dW;j++){
			std::cout << display[i][j];
		}
		std::cout << std::endl;
	}

	std::cout << '\n' << "Press the Enter key to continue.";
	do {} while (std::cin.get() != '\n');

	gotoxy(0,0);
}

}
