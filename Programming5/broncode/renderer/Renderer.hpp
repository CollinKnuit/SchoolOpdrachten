//============================================================================
// Name        		: Collin Knuit
// Version     		: 1.0
// Description 		: Ray Tracing in C++, Ansi-style
//============================================================================

#ifndef RENDERER_RENDERER_HPP_
#define RENDERER_RENDERER_HPP_
#pragma once

#include <vector>
#include "../lib/vector.hpp"
#include "../raytracer/RayTracer.hpp"

namespace display {

const char palette[] = " .:;~=#OB8%&";

//widht and height of each character in pixels
const short dW = 4, dH = 8;

class Console {

	friend class Renderer;
private:
	short width { 600 }; //standard screen dimensions width 800/2
	short height { 450 }; //standard screen dimensions height 600/2

public:
	Console(short width, short height);
	Console();
	~Console();
	void gotoxy(short x, short y); //set cursor at start to avoid flickering
	void setupInitialDisplay();
	void drawDisplay(std::vector<std::vector<char>> &display);

};

//also the camera
class Renderer {

	friend class Console;
private:
	Vect3D cam;
	std::vector<std::vector<char>> display;
	Console *co;
	tracer::RayTracer *rayt;
	double altitude = { 1 };
	double worldToCam[4][4] = { { 0 } };
	void transformVector(Vect3D &ray);

public:
	Renderer(Console &co, tracer::RayTracer &ray);
	Renderer(double r, double theta, double phi, Console &co,tracer::RayTracer &ray);
	void drawPicture();
	void setXYZ(double r, double theta, double phi);
	~Renderer();

};

}

#endif /* RENDERER_RENDERER_HPP_ */
