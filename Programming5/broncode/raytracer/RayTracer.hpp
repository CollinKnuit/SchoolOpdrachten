//============================================================================
// Name        		: Collin Knuit
// Version     		: 1.0
// Description 		: Ray Tracing in C++, Ansi-style
//============================================================================

#ifndef RAYTRACER_RAYTRACER_HPP_
#define RAYTRACER_RAYTRACER_HPP_
#pragma once

#include "../basic_objects/BasicObjects.hpp"
#include <vector>

namespace tracer {

class RayTracer {

private:
	std::vector<shapes::Shape*> shapes;
	double gCoeff = { 0.2 };
	double limit = 10;
	double resetLimit = 10;
	template<typename T> T clamp(T value, T lower, T upper);
	double hitSkyOrGround(const Vect3D &ray, const Vect3D &origin,double &distance, double &altitude);

public:
	RayTracer();
	~RayTracer();
	double computeRay(double &altitude, Vect3D &orgin, Vect3D &dir);
	void addShape(shapes::Shape *shape);
	void resetThisLimit();
	void setLimit(double limit);
};

}
#endif /* RAYTRACER_RAYTRACER_HPP_ */
