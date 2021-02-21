//============================================================================
// Name        		: Collin Knuit
// Version     		: 1.0
// Description 		: Ray Tracing in C++, Ansi-style
//============================================================================


#include "../lib/vector.hpp"
#include "../basic_objects/BasicObjects.hpp"
#include "../raytracer/RayTracer.hpp"
#include "../renderer/Renderer.hpp"
#include <iostream>
#include <windows.h>
#include <math.h>

#define PI 3.14159265358979323846


int main(int argc, char** args) {

	// camera viewing angle
	auto theta = 0.0;
	auto phi=PI/2;
	auto r =2;

	// make objects
	auto con = display::Console();
	auto ray = tracer::RayTracer();
	auto whatever = display::Renderer(r,theta ,phi,  con, ray);

	double contrast = 0.2;
	double coeff = 0.9;

//	// to add a cube
//	Vect3D A(-0.5,0.5,0);
//	Vect3D E(-0.5,0.5,0.5);
//	Vect3D B(0.5,0.5,0);
//	Vect3D F(0.5,0.5,0.5);
//	Vect3D C(-0.5,-0.5,0);
//	Vect3D G(-0.5,-0.5,0.5);
//	Vect3D D(0.5,-0.5,0);
//	Vect3D H(0.5,-0.5,0.5);
//	auto a = new shapes::Cube(A,B,C,D,E,F,G,H, contrast, coeff);
//	ray.addShape( a);

  // to add a sphere
	Vect3D vect1(0,0,0);
	double radius = 0.55;
	auto a = new shapes::Sphere(radius, vect1, contrast, coeff);
	ray.addShape(a);

//	// to add a triangle
//	Vect3D vect1(0,0,0);
//	Vect3D vect2(1,0,0);
//	Vect3D vect3(0,0,1);
//	auto a = new shapes::Triangle(vect1,vect2,vect3, contrast, coeff);
//	ray.addShape(a);

//	// to add a lamp
//	Vect3D vect1(0,0,2);
//	double radius = 1;
//	auto a = new shapes::Sphere(radius, vect1, contrast, coeff);
//	ray.addShape(a);

	// looping around the origin point(there is a bug in there it's more an elipse or it does not loop around the origin)
	while(true){
		whatever.drawPicture();
		theta+=0.03*PI;
		if(phi>PI/20)phi-=0.03*PI;
		whatever.setXYZ(r,theta, phi);
	}

	return 0;

}
