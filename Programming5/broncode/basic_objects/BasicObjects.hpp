//============================================================================
// Name        		: Collin Knuit
// Version     		: 1.0
// Description 		: Ray Tracing in C++, Ansi-style
//============================================================================

#ifndef BASIC_OBJECTS_BASICOBJECTS_HPP_
#define BASIC_OBJECTS_BASICOBJECTS_HPP_
#pragma once

#include "../lib/vector.hpp"

namespace shapes {

class Shape {

protected:
	double contrast = { 1 };		//on the interval from 0 (dark) to 1 (light)
	double coeff = { 1 };//on the scale from 0 to 1 how much does the ball reflect light (1 means it's the perfect mirror)

public:
	Shape(double contrast, double coeff);
	virtual ~Shape();
	virtual bool isItThisShape(const Vect3D &dir, const Vect3D &origin, Vect3D &origin2, Vect3D &dir2, double &distance);
	virtual double getContrast() const;
	virtual double getCoeff() const;

};

class Triangle: public Shape {

private:
	Vect3D A;
	Vect3D B;
	Vect3D C;

public:
	Triangle(const Vect3D &A, const Vect3D &B, const Vect3D &C, double contrast,
	double coeff);
	~Triangle();
	bool isItThisShape(const Vect3D &dir, const Vect3D &origin, Vect3D &origin2,
	Vect3D &dir2, double &distance) override;
	Vect3D getA() const;
	Vect3D getB() const;
	Vect3D getC() const;
};

class Cube: public Shape {

protected:
	std::vector<Triangle*> triangels;

public:
	Cube(Vect3D origin, short size, double &contrast, double &coeff);
	Cube(Vect3D &A, Vect3D &B, Vect3D &C, Vect3D &D, Vect3D &E, Vect3D &F,
	Vect3D &G, Vect3D &H, double &contrast, double &coeff);
	~Cube();
	bool isItThisShape(const Vect3D &dir, const Vect3D &origin, Vect3D &origin2,
	Vect3D &dir2, double &distance) override;
	std::vector<Triangle*> getTriangles();

private:
	void setPoints(Vect3D A, Vect3D B, Vect3D C, Vect3D D, Vect3D E, Vect3D F,Vect3D G, Vect3D H);

};

class Sphere: public Shape {

protected:
	double radius;
	double r2;
	Vect3D origin;

public:
	Sphere(double &radius, Vect3D &origin, double &contrast, double &coeff);
	~Sphere();
	bool isItThisShape(const Vect3D &dir, const Vect3D &origin, Vect3D &origin2, Vect3D &dir2, double &distance) override;
	Vect3D getOrgin() const;
};

class Lamp: public Sphere {
public:
	Lamp(double &radius, Vect3D &origin, double &contrast, double &coeff);
	~Lamp();
};

}

#endif /* BASIC_OBJECTS_BASICOBJECTS_HPP_ */
