//============================================================================
// Name        		: Collin Knuit
// Version     		: 1.0
// Description 		: Ray Tracing in C++, Ansi-style
//============================================================================

#include "RayTracer.hpp"
#include <algorithm>

namespace tracer{

RayTracer::RayTracer() {
	// TODO Auto-generated constructor stub

}

RayTracer::~RayTracer() {
	// TODO Auto-generated destructor stub
}

// computes the value of a single point
double RayTracer::computeRay(double &altitude, Vect3D &origin, Vect3D &dir){

	auto distance = 0.0;
	Vect3D origin2 = {0};
	Vect3D dir2 = {0};
	// for spheres
	for(short i=0;i< (int)shapes.size();i++){
		if(shapes[i]->isItThisShape(dir, origin, origin2, dir2, distance)){

			//============================================================================
			// This is used to prevent unneccesary calculations
			auto distanceToGround= -(origin.getZ() +altitude)/dir.getZ();
			if(origin.getZ()<0 && distance > distanceToGround) {

				Vect3D t =dir * (origin +distanceToGround);
				return (double)((int)(floor(t.getX())+floor(t.getY()))%2);
			}
			//============================================================================

			if(limit<=0){
				limit = resetLimit;
				return shapes[i]->getContrast();
			}
			limit -= 1;
			return  (1-shapes[i]->getCoeff())
					*shapes[i]->getContrast()
					+shapes[i]->getCoeff()
					*computeRay(altitude, origin2, dir2);

		}
	}
	return hitSkyOrGround(dir, origin, distance, altitude);
}

// it calculates if it hits the sky or the ground
double RayTracer::hitSkyOrGround(const Vect3D &ray,const Vect3D &origin, double &distance, double &altitude){
	if(ray.getZ()>0) return 0;

	auto distanceToGround= -(origin.getZ() +altitude)/ray.getZ();
	Vect3D t =ray * (origin +distanceToGround);
	int ground = ((int)(floor(t.getX())+floor(t.getY()))%2);

	if(ground == 0){
		auto contrast = clamp(1/(1+distanceToGround/10),0.,1.);
		auto origin2 =  (origin * distance) + ray;
		Vect3D dir2 = ray.minZ();
		limit -= 1;
		return (1-gCoeff)
				*contrast
				+gCoeff
				*computeRay(altitude, origin2, dir2);
	}
	return 0;
}


/**
 * cause I can't get clamp to work in algorithm
 * From: https://stackoverflow.com/questions/9323903/most-efficient-elegant-way-to-clip-a-number
 */
template<typename T> T RayTracer::clamp(T value, T lower, T upper){
	return value <= lower ? lower : value <= upper ? value : upper;
}

void RayTracer::addShape(shapes::Shape* shape){
	shapes.push_back(shape);
}

void RayTracer::resetThisLimit(){
	limit = resetLimit;
}

void RayTracer::setLimit(double limit){
	resetLimit = limit;
}

}
